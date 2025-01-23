package com.example.appweather.presentation

import android.app.Activity
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: LocationRepository) :
    ViewModel() {

    private var _location = MutableStateFlow<LocationState>(LocationState())
    var location: StateFlow<LocationState> = _location
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LocationState())

    fun getCurrentLocation(activity: Activity) {
        viewModelScope.launch {
            repository.getCurrentLocation(activity) { locationValue, isLocationPermissionGranted ->
                _location.update {
                    it.copy(
                        latitude = locationValue?.latitude ?: 0.0,
                        longitude = locationValue?.longitude ?: 0.0,
                        isPermissionGranted = isLocationPermissionGranted
                    )
                }
                Log.d("locationVM", location.value.toString())
            }
        }
    }


    fun isLocationPermissionGranted(context: Context): Boolean {

        return repository.isLocationPermissionGranted(context)

    }


}




