package com.example.appweather.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appweather.R
import com.example.appweather.ui.theme.SMALL_PADDING
import com.example.appweather.ui.theme.SplashScreenTextColor
import com.example.appweather.ui.theme.appBackground

@Composable
fun SplashScreen(onSplashFinished: () -> Unit = {}) {


    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.appBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(R.drawable.weatherpp),
            contentDescription = "Application Logo"
        )

        Spacer(modifier = Modifier.height(SMALL_PADDING))

        Text(
            text = "Weather App",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.SplashScreenTextColor,
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 5.sp

        )

    }


}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashScreenPreview() {
    SplashScreen()
}