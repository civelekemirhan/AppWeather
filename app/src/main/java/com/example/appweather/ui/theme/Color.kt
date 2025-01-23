package com.example.appweather.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val mainWeatherBack=Color(0xFF344E5A)
val secondaryWeatherBack=Color(0xFF648898)






val ColorScheme.appBackground
    @Composable
    get()=if(isSystemInDarkTheme()) Color.Black else Color.White

val ColorScheme.TextColor
    @Composable
    get()=if(isSystemInDarkTheme()) Color.White else Color.Black


val ColorScheme.bottomBarBackground
    @Composable
    get()=if(isSystemInDarkTheme()) Color.White else Color.White

val ColorScheme.bottomBarIconColor
    @Composable
    get()=if(isSystemInDarkTheme()) Color.Black else Color.Black



