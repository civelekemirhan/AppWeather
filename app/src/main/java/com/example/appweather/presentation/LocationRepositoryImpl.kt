package com.example.appweather.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import javax.inject.Inject


class LocationRepositoryImpl @Inject constructor() : LocationRepository {


    override suspend fun getCurrentLocation(
        activity: Activity,
        onLocationReceived: (Location?,isPermissionGranted: Boolean) -> Unit
    ) {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity)

        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                onLocationReceived(location,true) // Konumu alıp callback fonksiyonunu çağırıyoruz
            }
        } else {
            // Eğer izin verilmemişse, null döner
            onLocationReceived(null,false)
        }
    }

    override fun isLocationPermissionGranted(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

}