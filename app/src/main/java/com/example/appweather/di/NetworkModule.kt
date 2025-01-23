package com.example.appweather.di

import com.example.appweather.Constants.BASE_URL
import com.example.appweather.model.ApiService
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
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return client

    }

    // GsonConverterFactory ile Retrofit yapılandırılıyor
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // API'nizin base URL'si
            .addConverterFactory(GsonConverterFactory.create()) // JSON verisini Gson ile dönüştürme
            .client(okHttpClient) // OkHttpClient'ı Retrofit'e ekliyoruz
            .build()
    }

    // API servis arayüzünü sağlıyoruz
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}