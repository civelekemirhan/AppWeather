package com.example.appweather.presentation

data class LocationState(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val locationName: String = "",
    val error: String = "",
    val isPermissionGranted: Boolean = false

)