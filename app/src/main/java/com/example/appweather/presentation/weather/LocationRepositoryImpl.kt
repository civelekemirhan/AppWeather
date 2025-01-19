package com.example.appweather.presentation.weather

import android.Manifest
import android.app.Activity
import android.content.Context
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import javax.inject.Inject


class LocationRepositoryImpl @Inject constructor() : LocationRepository {


    override suspend fun getCurrentLocation(
        activity: Activity,
        onLocationReceived: (Location?) -> Unit
    ) {
        val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener(OnSuccessListener<Location> { location ->
                onLocationReceived(location)
            })
        } else {
            onLocationReceived(null)
        }
    }

    override fun getLocationPermission(activity: Activity) {

        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )

    }

    override fun isLocationPermissionGranted(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            return true
        } else return false
    }

}