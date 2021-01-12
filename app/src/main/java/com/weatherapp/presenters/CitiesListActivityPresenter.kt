package com.weatherapp.presenters

import android.content.Context
import com.weatherapp.contracts.CitiesListActivityContract
import com.weatherapp.interactors.CitiesListInteractor
import com.weatherapp.models.CitiesListResponse

class CitiesListActivityPresenter(private var mGetDataView: CitiesListActivityContract.View?) :
    CitiesListActivityContract.Presenter,
    CitiesListActivityContract.onGetDataListener {
    private var mIntractor: CitiesListInteractor = CitiesListInteractor(this)

    override fun getDataFromURL(context: Context?) {
        mIntractor.initRetrofitCall(context)
    }

    override fun onSuccess(citiesList: List<CitiesListResponse?>?) {
        mGetDataView?.onGetDataSuccess(citiesList)
    }

    override fun onFailure(message: String?) {
        mGetDataView?.onGetDataFailure(message)
    }

}

