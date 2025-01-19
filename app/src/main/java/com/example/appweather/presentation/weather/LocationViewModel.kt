package com.example.appweather.presentation.weather

import android.app.Activity
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: LocationRepository) :
    ViewModel() {


    private val _location = MutableStateFlow<Location?>(null)
    var location: StateFlow<Location?> = _location
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


     fun getCurrentLocation(activity: Activity) {
        viewModelScope.launch {
            if (isLocationPermissionGranted(activity)){
               repository.getCurrentLocation(activity){ locationValue ->
                   _location.value=locationValue
               }
            }else{
                getLocationPermission(activity)
            }
        }

    }

   private fun getLocationPermission(activity: Activity) {
        if (!isLocationPermissionGranted(activity)) {
            repository.getLocationPermission(activity)
        }
    }

    private fun isLocationPermissionGranted(context: Context): Boolean {
        return repository.isLocationPermissionGranted(context)
    }



}
