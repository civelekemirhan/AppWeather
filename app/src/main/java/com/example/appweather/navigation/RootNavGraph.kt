package com.example.appweather.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.appweather.Constants.HOME_GRAPH_ROUTE
import com.example.appweather.Constants.ROOT_GRAPH_ROUTE
import com.example.appweather.Constants.SPLASH_SCREEN
import com.example.appweather.presentation.splash.SplashScreen

fun NavGraphBuilder.RootNavGraph(navController: NavHostController) {

    navigation(startDestination = SPLASH_SCREEN, route = ROOT_GRAPH_ROUTE) {
        composable(route = SPLASH_SCREEN) {
            SplashScreen(){
                navController.navigate(HOME_GRAPH_ROUTE){
                    popUpTo(SPLASH_SCREEN){
                        inclusive=true
                    }
                }
            }
        }

    }

}