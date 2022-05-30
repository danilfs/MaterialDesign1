package com.example.materialdesign1.di

import com.example.materialdesign1.data.nasa_api.NasaApiService
import com.example.materialdesign1.data.nasa_api.interceptor.NasaApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NasaApiModule {
    @Provides
    @Singleton
    fun provideNasaApiService(okHttpClient: OkHttpClient): NasaApiService = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NasaApiService::class.java)

    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: NasaApiKeyInterceptor,
        loggerInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(loggerInterceptor)
        .build()

    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}