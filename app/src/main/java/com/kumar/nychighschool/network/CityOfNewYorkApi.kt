package com.kumar.nychighschool.network

import com.kumar.nychighschool.data.HighSchool
import retrofit2.http.GET

interface CityOfNewYorkApi {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getListOfHighSchools(): List<HighSchool>
}