package com.example.appweather.presentation.weather

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appweather.ui.theme.Blue3
import com.example.appweather.ui.theme.appBackground
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.log

@Composable
fun WeatherScreen(viewModel: LocationViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val activity = context as Activity
    val systemUiController = rememberSystemUiController()


    viewModel.getCurrentLocation(activity)


    val location = viewModel.location.collectAsState().value



    systemUiController.setStatusBarColor(Blue3, darkIcons = false)


    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.appBackground)
        ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Blue3)) {

                if(location != null){

                    Text(text = GeocoderUtil.getLocationName(activity,location.latitude,location.longitude))
                    Log.d("location",location.toString())
                }


            }

        }
    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewWeatherScreen() {
    WeatherScreen()
}