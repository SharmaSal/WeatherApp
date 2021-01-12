package com.weatherapp.models

import retrofit2.Call
import retrofit2.http.GET

interface CitiesListApi {
    @GET("data/2.5/box/city?bbox=12,32,15,37,10&appid=b1b15e88fa797225412429c1c50c122a1")
    fun getData(): Call<CitiesList?>
}
