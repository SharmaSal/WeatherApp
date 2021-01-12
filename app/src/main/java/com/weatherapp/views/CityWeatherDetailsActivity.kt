package com.weatherapp.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cocktails.utility.CheckNetworkConnectivity
import com.google.gson.Gson
import com.weatherapp.R
import com.weatherapp.contracts.CityWeatherDetailsActivityContract
import com.weatherapp.models.CitiesListResponse
import com.weatherapp.models.CityWeatherDetails
import com.weatherapp.presenters.CityWeatherDetailsActivityPresenter
import kotlinx.android.synthetic.main.city_weather_details.*

class CityWeatherDetailsActivity : AppCompatActivity(), CityWeatherDetailsActivityContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_weather_details)

        getSelectedCityDetails()

    }

    private fun getSelectedCityDetails() {
        val gson = Gson()
        val selectedCityDataAsString =
            intent.getStringExtra("selectedCityAsString")
        val selectedCityData: CitiesListResponse = gson.fromJson(
            selectedCityDataAsString,
            CitiesListResponse::class.java
        )
        getWeatherDetailsForSelectedCity(selectedCityData.id)
    }

    private fun getWeatherDetailsForSelectedCity(id: String ){
        if (CheckNetworkConnectivity.checkIfNetworkAvailable(this)) {
            val presenter = CityWeatherDetailsActivityPresenter(this)
            presenter.getDataFromURL(this, id)
        } else {
            showMessage(getString(R.string.no_network_message))
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onGetDataSuccess(weatherDetails : CityWeatherDetails?) {
        val txtTitle: TextView = tv_title_text
        val txtWeather: TextView = tv_weather
        val txtSunriseTime: TextView = tv_sunrise_time
        val txtSunsetTime: TextView = tv_sunset_time
        val txtTemp: TextView = tv_temperature
        val txtHumidity: TextView = tv_humidity
        val txtPressure: TextView = tv_pressure
        val txtMinTemp: TextView = tv_mini_temp
        val txtMaxTemp: TextView = tv_max_temp

        txtTitle.text = getString(R.string.weather_details_view_title) + weatherDetails?.name + "(" + weatherDetails?.sys?.country + ")"
        txtWeather.text = getString(R.string.weather_details_view_weather) + weatherDetails?.weather?.get(0)?.description
        txtSunriseTime.text = getString(R.string.weather_details_view_sunrise) + weatherDetails?.sys?.sunrise
        txtSunsetTime.text = getString(R.string.weather_details_view_sunset) + weatherDetails?.sys?.sunset

        txtTemp.text =  getString(R.string.weather_details_view_temp) + weatherDetails?.main?.temp
        txtHumidity.text =  getString(R.string.weather_details_view_humidity)+ weatherDetails?.main?.humidity
        txtPressure.text =  getString(R.string.weather_details_view_pressure) + weatherDetails?.main?.pressure
        txtMinTemp.text = getString(R.string.weather_details_view_temp_min) + weatherDetails?.main?.temp_min
        txtMaxTemp.text = getString(R.string.weather_details_view_temp_max) + weatherDetails?.main?.temp_max
    }

        override fun onGetDataFailure(message: String?) {
        showMessage(message)
    }

    private fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}