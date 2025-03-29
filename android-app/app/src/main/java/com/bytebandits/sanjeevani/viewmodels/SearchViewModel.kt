package com.bytebandits.sanjeevani.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
<<<<<<< HEAD
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bytebandits.sanjeevani.dto.HospitalListItem
import com.bytebandits.sanjeevani.dto.HospitalsList
import com.bytebandits.sanjeevani.dto.ownerLocation
import com.bytebandits.sanjeevani.interfaces.NearbyHospitalsService
=======
import androidx.lifecycle.ViewModel
>>>>>>> 4e42eac (Added ICP and Frontend)
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
<<<<<<< HEAD
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.truncate

@HiltViewModel
class SearchViewModel @Inject constructor(@ApplicationContext private val context: Context, private val nearbyHospitalsService: NearbyHospitalsService) : ViewModel() {

    private val _hospitalList = MutableLiveData<List<HospitalListItem>>()
    val hospitalList: LiveData<List<HospitalListItem>> = _hospitalList


    private val _lat = MutableLiveData<Double?>()
    val lat: LiveData<Double?> = _lat

    private val _long = MutableLiveData<Double?>()
    val long: LiveData<Double?> = _long


    @SuppressLint("MissingPermission")
    fun onPermissionGranted(radius: Int) {
=======
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(@ApplicationContext private val context: Context) : ViewModel() {

    @SuppressLint("MissingPermission")
    fun onPermissionGranted() {
>>>>>>> 4e42eac (Added ICP and Frontend)
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
<<<<<<< HEAD

                println("Latitude: $lat, Longitude: $lon")
                val ownerLocation = ownerLocation(lat.toString(), lon.toString(), radius)
                searchResults(ownerLocation)
=======
                println("Latitude: $lat, Longitude: $lon")
>>>>>>> 4e42eac (Added ICP and Frontend)
            }

        }, Looper.getMainLooper())


    }


<<<<<<< HEAD
    fun searchResults(ownerLocation : ownerLocation){
        viewModelScope.launch {
            try {
                val response = nearbyHospitalsService.getNearbyHospitals(ownerLocation)
                if (response.isSuccessful){
                    response.body()?.let { hospitalListResponse ->
                        _hospitalList.postValue(hospitalListResponse.hospitals)
                        println(hospitalListResponse.hospitals[2].name)
                        println(hospitalListResponse.hospitals[3].name)

                    }
                } else {
                    println( response.code() )
                }
            } catch (e : Exception){
                println(e.message)
            }
        }
    }
=======
    fun searchResults(){
        
    }

>>>>>>> 4e42eac (Added ICP and Frontend)
}