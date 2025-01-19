package com.example.appweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appweather.Constants.ROOT_GRAPH_ROUTE
import com.example.appweather.Constants.SPLASH_SCREEN
import com.example.appweather.presentation.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = ROOT_GRAPH_ROUTE) {

        RootNavGraph(navController = navController)

        HomeNavGraph(navController = navController)

    }


}