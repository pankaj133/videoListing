package com.example.videolistingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.videolistingapp.databinding.RefreshItemBinding

class RefreshStateAdapter(private val refresh: () -> Unit) :
    LoadStateAdapter<RefreshStateAdapter.RefreshStateHolder>() {

    inner class RefreshStateHolder(private val binding: RefreshItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rety.setOnClickListener {
                refresh.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progress.isVisible = loadState is LoadState.Loading
                rety.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: RefreshStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): RefreshStateHolder {
        val binding = RefreshItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RefreshStateHolder(binding)
    }
}