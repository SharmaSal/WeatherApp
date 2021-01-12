package com.weatherapp.presenters

import android.content.Context
import com.weatherapp.contracts.CityWeatherDetailsActivityContract
import com.weatherapp.interactors.CityWeatherDetailsInteractor
import com.weatherapp.models.CityWeatherDetails

class CityWeatherDetailsActivityPresenter(private var mGetDataView: CityWeatherDetailsActivityContract.View?) :
    CityWeatherDetailsActivityContract.Presenter,
    CityWeatherDetailsActivityContract.onGetDataListener {
    private var mIntractor: CityWeatherDetailsInteractor = CityWeatherDetailsInteractor(this)
    private lateinit var view : CityWeatherDetailsActivityContract.View

    override fun getDataFromURL(context: Context?, value: String?) {
        mIntractor.initRetrofitCall(context, value)
    }

    override fun attach(view: CityWeatherDetailsActivityContract.View) {
        this.view = view
    }

    override fun onSuccess(weatherDetails: CityWeatherDetails?) {
        mGetDataView?.onGetDataSuccess(weatherDetails)
    }

    override fun onFailure(message: String?) {
        mGetDataView?.onGetDataFailure(message)
    }

}

