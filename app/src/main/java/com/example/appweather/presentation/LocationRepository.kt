package com.example.appweather.presentation

import android.app.Activity
import android.content.Context
import android.location.Location

interface LocationRepository {

    suspend fun getCurrentLocation(activity: Activity, onLocationReceived: (Location?,isPermissionGranted: Boolean) -> Unit)
    fun isLocationPermissionGranted(context: Context): Boolean

}