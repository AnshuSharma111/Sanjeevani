package com.bytebandits.sanjeevani.interfaces

import com.bytebandits.sanjeevani.dto.HospitalListItem
import com.bytebandits.sanjeevani.dto.HospitalsList
import com.bytebandits.sanjeevani.dto.ownerLocation
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NearbyHospitalsService {
    @POST("location/hospitals")
    suspend fun getNearbyHospitals(@Body ownerLocation: ownerLocation) : Response<HospitalsList>
}
