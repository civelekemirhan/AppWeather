package com.example.appweather.presentation.weather

import android.Manifest
import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appweather.Constants.API_KEY
import com.example.appweather.presentation.GeocoderUtil
import com.example.appweather.presentation.LocationViewModel
import com.example.appweather.ui.theme.appBackground
import com.example.appweather.ui.theme.mainWeatherBack
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.absoluteValue
import kotlin.time.Duration.Companion.days


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    locationViewModel: LocationViewModel = hiltViewModel(),
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = context as Activity
    val systemUiController = rememberSystemUiController()
    val locationState by locationViewModel.location.collectAsState()
    val weatherState by weatherViewModel.weatherData.collectAsState()


    val pagerState = rememberPagerState(pageCount = {
        if (weatherState.days.isNotEmpty()) {
            weatherState.days.size
        } else {
            2
        }
    })

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                locationViewModel.getCurrentLocation(activity)
            } else {
                Log.d("location", "Permission denied")
            }
        }
    )

    LaunchedEffect(key1 = true) {
        if (!locationViewModel.isLocationPermissionGranted(context)) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            locationViewModel.getCurrentLocation(activity)
        }
    }

    LaunchedEffect(key1 = locationState) {
        if (locationState.latitude != 0.0 && locationState.longitude != 0.0) {
            weatherViewModel.getWeather(locationState.latitude, locationState.longitude, API_KEY)
        }
    }

    systemUiController.setStatusBarColor(mainWeatherBack, darkIcons = false)


    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.appBackground)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(mainWeatherBack)
                    .drawBehind {

                        // Alt kenara sınır çizme
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, size.height), // Başlangıç noktası
                            end = Offset(size.width, size.height), // Bitiş noktası
                            strokeWidth = 10f // Sınırın kalınlığı
                        )
                    }
            ) {


                if (weatherState.days.isNotEmpty()) {
                    WeatherToday(
                        true,
                        weatherViewModel.fahrenheitToCelsius(weatherState.days[0].tempmax),
                        weatherViewModel.fahrenheitToCelsius(weatherState.days[0].tempmin),
                        weatherViewModel.fahrenheitToCelsius(weatherState.days[0].temp),
                        weatherState.days[0].description,
                        weatherState.days[0].datetime,
                        GeocoderUtil.getLocationName(
                            activity,
                            weatherState.latitude,
                            weatherState.longitude
                        ),
                        weatherState.days[0].icon
                    )
                } else {
                    WeatherToday(false)
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (weatherState.days.isNotEmpty()) {

                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(horizontal = 48.dp) // Daha fazla boşluk ekleyin
                    ) { page ->
                            WeatherOtherDays(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f) // Sayfa genişliğini biraz daha daraltın
                                    .fillMaxHeight(0.8f)
                                    .graphicsLayer {
                                        val pageOffset = (
                                                (pagerState.currentPage - page) + pagerState
                                                    .currentPageOffsetFraction
                                                ).absoluteValue

                                        // Animasyonlu alpha geçişi
                                        alpha = lerp(
                                            start = 0.5f,
                                            stop = 1f,
                                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                        )
                                    },
                                true,
                                weatherViewModel.fahrenheitToCelsius(weatherState.days[page].tempmax),
                                weatherViewModel.fahrenheitToCelsius(weatherState.days[page].tempmin),
                                weatherViewModel.fahrenheitToCelsius(weatherState.days[page].temp),
                                weatherState.days[page].description,
                                weatherState.days[page].datetime,
                                weatherState.days[page].icon
                            )
                    }
                } else {
                    WeatherOtherDays(
                        modifier = Modifier
                            .fillMaxWidth(0.8f) // Sayfa genişliğini biraz daha daraltın
                            .fillMaxHeight(0.8f).padding(horizontal = 48.dp), false
                    )
                }
            }

        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewWeatherScreen() {
    WeatherScreen()
}