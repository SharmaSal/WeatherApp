package com.weatherapp.interactors

import android.content.Context
import com.weatherapp.contracts.CityWeatherDetailsActivityContract
import com.weatherapp.models.CityWeatherDetails
import com.weatherapp.models.CityWeatherDetailsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityWeatherDetailsInteractor(private var mOnGetDatalistener: CityWeatherDetailsActivityContract.onGetDataListener?) :
    CityWeatherDetailsActivityContract.Interactor {

    private val baseUrl = "https://samples.openweathermap.org/"

    var cityWeatherDetails: CityWeatherDetails? = null

    override fun initRetrofitCall(context: Context?, value: String?) {

        val service: CityWeatherDetailsApi
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(CityWeatherDetailsApi::class.java)
        val call: Call<CityWeatherDetails?> = service.getWeatherDetails(value)

        call.enqueue(object : Callback<CityWeatherDetails?> {
            override fun onResponse(
                call: Call<CityWeatherDetails?>,
                response: Response<CityWeatherDetails?>
            ) {

                val jsonResponse = response.body()
                if (jsonResponse != null) {
                    cityWeatherDetails = jsonResponse
                }
                println(cityWeatherDetails)
                mOnGetDatalistener?.onSuccess(cityWeatherDetails)
            }

            override fun onFailure(call: Call<CityWeatherDetails?>, t: Throwable) {
                mOnGetDatalistener?.onFailure("service failed"+ t.message)
            }
        })
    }
}


