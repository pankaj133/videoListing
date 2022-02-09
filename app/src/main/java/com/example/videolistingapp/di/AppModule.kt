package com.example.videolistingapp.di

import android.app.Application
import com.example.videolistingapp.api.YouTubeApi
import com.example.videolistingapp.repos.YoutubeRepository
import com.example.videolistingapp.utils.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun client(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(YouTubeApi.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    @Singleton
    fun provideYoutube(retrofit: Retrofit): YouTubeApi = retrofit.create(YouTubeApi::class.java)


    @Provides
    @Singleton
    fun provideYoutubeRepository(youTubeApi: YouTubeApi): YoutubeRepository {
        return YoutubeRepository(youTubeApi)
    }

    @Provides
    @Singleton
    fun provideNetworkConnect(application: Application): NetworkConnectivity {
        return NetworkConnectivity(application);
    }

}