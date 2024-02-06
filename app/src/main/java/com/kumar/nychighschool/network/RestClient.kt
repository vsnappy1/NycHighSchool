package com.kumar.nychighschool.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    fun getCityOfNewYorkApi(): CityOfNewYorkApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CityOfNewYorkApi::class.java)
    }
}