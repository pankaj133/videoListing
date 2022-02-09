package com.example.videolistingapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.videolistingapp.databinding.ActivityMainBinding
import com.example.videolistingapp.ui.adapters.RefreshStateAdapter
import com.example.videolistingapp.ui.adapters.YouTubeVideoAdapter
import com.example.videolistingapp.utils.NetworkConnectivity
import com.example.videolistingapp.viewmodels.YoutubeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkConnectivity: NetworkConnectivity

    @Inject
    lateinit var youTubeVideoAdapter: YouTubeVideoAdapter

    private val viewModel: YoutubeViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyeclerview.apply {
            setHasFixedSize(true)
            adapter =
                youTubeVideoAdapter.withLoadStateFooter(RefreshStateAdapter { youTubeVideoAdapter.retry() })
        }

        makeSnackBar()
        networkConnectivity.observe(this) {
            if (it) {
                snackbar.dismiss()
                viewModel.getYoutubeData().observe(this) {
                    youTubeVideoAdapter.submitData(lifecycle, it)
                    binding.recyeclerview.visibility = View.VISIBLE
                    binding.progesslayout.visibility = View.GONE
                }
            } else {
                snackbar.show()
            }
        }
    }

    private fun makeSnackBar() {
        snackbar = Snackbar.make(binding.coordinator, "No Internet", Snackbar.LENGTH_INDEFINITE)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}