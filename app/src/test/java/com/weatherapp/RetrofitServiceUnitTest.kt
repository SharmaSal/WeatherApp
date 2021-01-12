package com.weatherapp
import com.weatherapp.models.CityWeatherDetails
import com.weatherapp.models.CityWeatherDetailsApi

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitServiceUnitTest {
    private lateinit var retrofit: Retrofit
    private lateinit var service: CityWeatherDetailsApi
    private lateinit var resp: Response<CityWeatherDetails?>


    @Before
    fun setUp() {
        retrofit = Retrofit.Builder().baseUrl("https://samples.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(CityWeatherDetailsApi::class.java)
    }

    @Test
    fun `Verfiy retrofit service success response for weather details data `() {
        val call: Call<CityWeatherDetails?> = service.getWeatherDetails("21323434")
        resp = call.execute()
        assertEquals(resp.code(), 200)
        assertEquals(resp.body()?.name, "Cairns")
    }
}