package com.bytebandits.sanjeevani.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(@ApplicationContext private val context: Context) : ViewModel() {

    @SuppressLint("MissingPermission")
    fun onPermissionGranted() {
        println("Permission Granted")
        val client = LocationServices.getFusedLocationProviderClient(context)
        val request = LocationRequest.Builder(20000).setMinUpdateIntervalMillis(10000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY).build()

        client.requestLocationUpdates(request, object : LocationCallback() {
            @SuppressLint("SuspiciousIndentation")
            override fun onLocationResult(currentLocation: LocationResult) {
                val location = currentLocation.locations
                val lat = location[0].latitude
                val lon = location[0].longitude
                println("Latitude: $lat, Longitude: $lon")
            }

        }, Looper.getMainLooper())


    }


    fun searchResults(){
        
    }

}