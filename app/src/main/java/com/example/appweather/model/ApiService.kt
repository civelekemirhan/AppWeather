package com.example.appweather.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("{latitude},{longitude}/{startDate}/{endDate}")
    suspend fun getWeatherForSevenDays(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("startDate") startDate: String,
        @Path("endDate") endDate: String,
        @Query("key") apiKey: String,
        @Query("include") include: String = "days",  // Default olarak 'days' ekler
        @Query("elements") elements: String = "datetime,tempmax,tempmin,temp,description,icon" // Default olarak bu öğeler
    ): Response<WeatherResponse>

}