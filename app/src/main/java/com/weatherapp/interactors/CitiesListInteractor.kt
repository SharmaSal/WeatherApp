package com.weatherapp.interactors

import android.content.Context
import com.weatherapp.contracts.CitiesListActivityContract
import com.weatherapp.models.CitiesList
import com.weatherapp.models.CitiesListApi
import com.weatherapp.models.CitiesListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CitiesListInteractor(private var mOnGetDatalistener: CitiesListActivityContract.onGetDataListener?) :
    CitiesListActivityContract.Interactor {

    private val baseUrl = "https://samples.openweathermap.org/"
    var citiesList: List<CitiesListResponse?>? = ArrayList()

    override fun initRetrofitCall(context: Context?) {

        val service: CitiesListApi
        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(CitiesListApi::class.java)
        val call: Call<CitiesList?> = service.getData()
        call.enqueue(object : Callback<CitiesList?> {
            override fun onResponse(
                call: Call<CitiesList?>,
                response: Response<CitiesList?>
            ) {
                val jsonResponse = response.body()
                if (jsonResponse != null) {
                    citiesList = jsonResponse.list
                }
                mOnGetDatalistener?.onSuccess(citiesList)
            }

            override fun onFailure(call: Call<CitiesList?>, t: Throwable) {
                mOnGetDatalistener?.onFailure("service failed")
            }


        })
    }
}


