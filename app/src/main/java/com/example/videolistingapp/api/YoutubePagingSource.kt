package com.example.videolistingapp.api

import androidx.paging.PagingSource
import com.example.videolistingapp.models.Item
import retrofit2.HttpException
import java.io.IOException

private var TOKEN = ""

class YoutubePagingSource(
    private val youTubeApi: YouTubeApi,
) : PagingSource<String, Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        return try {
            val response = youTubeApi.youtubeVideo(
                pageToken = TOKEN,
                maxResults = params.loadSize)
            TOKEN = response.nextPageToken
            LoadResult.Page(response.items, response.prevPageToken, response.nextPageToken)
        } catch (e: IOException) {
            LoadResult.Error(e);
        } catch (e: HttpException) {
            LoadResult.Error(e);
        }
    }
}