package com.example.appweather.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.io.IOException
import java.util.Locale

object GeocoderUtil {

    @SuppressLint("SuspiciousIndentation")
    fun getLocationName(activity: Activity, lat:Double, lon:Double):String{
        val geocoder = Geocoder(activity, Locale.getDefault())

            try {
                // Enlem ve boylamı kullanarak adresi alıyoruz
                val addresses: List<Address> = geocoder.getFromLocation(lat, lon, 1)!!

                if (addresses.isNotEmpty()) {
                    val address: Address = addresses[0]
                    val region = address.locality ?: address.adminArea ?: address.countryName
                    val country = address.countryName
                    Log.d("LocationInfo", "Region: $region")
                    return region
                } else {
                    Log.d("LocationInfo", "Adres bulunamadı.")
                    return "Adres bulunamadı."
                }
            } catch (e: IOException) {
                // IOException hatası durumunda
                Log.e("LocationInfo", "Geocoder hatası: ${e.message}")
                return "Geocoder hatası:${e.message}"
            }

    }

}