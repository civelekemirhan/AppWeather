package com.example.appweather.navigation

import com.example.appweather.Constants.MAP_SCREEN
import com.example.appweather.Constants.SEARCH_SCREEN
import com.example.appweather.Constants.SPLASH_SCREEN
import com.example.appweather.Constants.WEATHER_SCREEN

sealed class Screen(route: String) {
    object SplashScreen : Screen(route = SPLASH_SCREEN)
    object WeatherScreen : Screen(route = WEATHER_SCREEN)
    object SearchScreen : Screen(route = SEARCH_SCREEN)
    object MapScreen : Screen(route = MAP_SCREEN)
}