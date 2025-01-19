package com.example.appweather.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.appweather.Constants.HOME_GRAPH_ROUTE
import com.example.appweather.Constants.MAP_SCREEN
import com.example.appweather.Constants.SEARCH_SCREEN
import com.example.appweather.Constants.WEATHER_SCREEN
import com.example.appweather.presentation.map.MapScreen
import com.example.appweather.presentation.search.SearchScreen
import com.example.appweather.presentation.weather.WeatherScreen

fun NavGraphBuilder.HomeNavGraph(navController: NavHostController){

    navigation(startDestination = WEATHER_SCREEN, route = HOME_GRAPH_ROUTE){

        composable(route = WEATHER_SCREEN){
            WeatherScreen()
        }
        composable(route = SEARCH_SCREEN){
            SearchScreen()
        }
        composable(route = MAP_SCREEN){
            MapScreen()
        }



    }

}