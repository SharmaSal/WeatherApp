package com.weatherapp.contracts

import android.content.Context
import com.weatherapp.models.CitiesListResponse

interface CitiesListActivityContract {

    interface View {
        fun onGetDataSuccess(list: List<CitiesListResponse?>?)
        fun onGetDataFailure(message: String?)
    }

    interface Presenter {
        fun getDataFromURL(context: Context?)
    }

    interface Interactor {
        fun initRetrofitCall(context: Context?)
    }

    interface onGetDataListener {
        fun onSuccess(citiesList: List<CitiesListResponse?>?)
        fun onFailure(message: String?)
    }
}
