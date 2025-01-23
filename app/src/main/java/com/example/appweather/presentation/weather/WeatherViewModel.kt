package com.example.appweather.presentation.weather

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appweather.model.ApiService
import com.example.appweather.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponseState>(WeatherResponseState())
    val weatherData: StateFlow<WeatherResponseState> = _weatherData
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), WeatherResponseState())

    // Hava durumu verilerini almak için API çağrısı
    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeather(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {

            val now = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val startDate = now.format(formatter)
            val endDate = now.plusDays(7).format(formatter)

            try {
                val response = apiService.getWeatherForSevenDays(latitude, longitude, startDate, endDate, apiKey)
                    _weatherData.update {
                        it.copy(
                            latitude = response.body()?.latitude ?: 0.0,
                            longitude = response.body()?.longitude ?: 0.0,
                            locationName = response.body()?.resolvedAddress ?: "",
                            days = response.body()?.days ?: emptyList()
                        )
                    }

            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Exception: $e")
            }
        }
    }


     fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

}