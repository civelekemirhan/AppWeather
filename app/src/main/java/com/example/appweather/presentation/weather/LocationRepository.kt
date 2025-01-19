package com.example.appweather.presentation.weather

import android.app.Activity
import android.content.Context
import android.location.Location

interface LocationRepository {

    suspend fun getCurrentLocation(activity: Activity, onLocationReceived: (Location?) -> Unit)
    fun getLocationPermission( activity: Activity)
    fun isLocationPermissionGranted(context: Context): Boolean

}