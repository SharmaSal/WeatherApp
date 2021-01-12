package com.weatherapp.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CityWeatherDetailsApi {
    @GET("data/2.5/weather?appid=b1b15e88fa797225412429c1c50c122a1")
    fun getWeatherDetails(@Query("id") id: String?): Call<CityWeatherDetails?>
}
