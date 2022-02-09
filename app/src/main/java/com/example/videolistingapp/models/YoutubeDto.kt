package com.example.videolistingapp.models

data class YoutubeDto(
    val etag: String,
    val kind: String,
    val items: List<Item>,
    val nextPageToken: String,
    val prevPageToken:String,
)