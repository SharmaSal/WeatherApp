package com.weatherapp.contracts

import android.content.Context
import com.weatherapp.models.CityWeatherDetails

interface CityWeatherDetailsActivityContract {

    interface View {
        fun onGetDataSuccess(weatherDetails:CityWeatherDetails?)
        fun onGetDataFailure(message: String?)
    }

    interface Presenter {
        fun getDataFromURL(context: Context?, value: String?)
        fun attach(view: CityWeatherDetailsActivityContract.View)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context?, value: String?)
    }

    interface onGetDataListener {
        fun onSuccess(weatherDetails: CityWeatherDetails?)
        fun onFailure(message: String?)
    }
}
