package com.example.videolistingapp.api

import com.example.videolistingapp.models.YoutubeDto
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    companion object {
        const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
        const val YOUTUBE_API = "AIzaSyD02Bt90JVubZShEPexeOdl_LO_mbPTrws"
    }

    @GET("videos")
    suspend fun youtubeVideo(
        @Query("part") part: String = "snippet,contentDetails,statistics",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "US",
        @Query("key") key: String = YOUTUBE_API,
        @Query("pageToken") pageToken: String,
        @Query("maxResults") maxResults: Int,
        ): YoutubeDto

}