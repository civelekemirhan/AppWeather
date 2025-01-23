package com.example.appweather.presentation.weather

import com.example.appweather.model.Day

data class WeatherResponseState(

     val latitude: Double = 0.0,
     val longitude: Double = 0.0,
     val locationName: String = "",
     val days: List<Day> = emptyList()

)