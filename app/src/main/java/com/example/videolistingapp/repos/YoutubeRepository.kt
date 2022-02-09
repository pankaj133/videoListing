package com.example.videolistingapp.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.videolistingapp.api.YouTubeApi
import com.example.videolistingapp.api.YoutubePagingSource

class YoutubeRepository(private val youTubeApi: YouTubeApi) {

    fun getYoutubeVideoResult() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = {
            YoutubePagingSource(youTubeApi = youTubeApi)
        }
    ).liveData

}