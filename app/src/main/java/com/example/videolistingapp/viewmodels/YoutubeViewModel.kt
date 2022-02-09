package com.example.videolistingapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.videolistingapp.models.Item
import com.example.videolistingapp.repos.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YoutubeViewModel
@Inject
constructor(private val youtubeRepository: YoutubeRepository) : ViewModel() {

    fun getYoutubeData(): LiveData<PagingData<Item>> {
        return youtubeRepository.getYoutubeVideoResult().cachedIn(viewModelScope)
    }

}