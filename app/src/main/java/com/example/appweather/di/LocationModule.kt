package com.example.appweather.di

import com.example.appweather.presentation.LocationRepository
import com.example.appweather.presentation.LocationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocationRepository(): LocationRepository {
        return LocationRepositoryImpl()
    }


}