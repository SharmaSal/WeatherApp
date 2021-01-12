package com.weatherapp.views


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.weatherapp.adapters.CitiesListAdapter
import com.weatherapp.contracts.CitiesListActivityContract
import com.weatherapp.models.CitiesListResponse
import com.weatherapp.presenters.CitiesListActivityPresenter
import com.cocktails.utility.CheckNetworkConnectivity
import com.cocktails.utility.TopSpacing
import com.google.gson.Gson
import com.weatherapp.R
import kotlinx.android.synthetic.main.cities_list.*


class CitiesListActivity : AppCompatActivity(), CitiesListActivityContract.View,
    CitiesListAdapter.OnItemClickListener {
    private lateinit var myadapter: CitiesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cities_list)

        getDataFromServer()
        initRecyclerView()
        recycler_view.callOnClick()
    }

    private fun getDataFromServer() {
        if (CheckNetworkConnectivity.checkIfNetworkAvailable(this)) {
            val presenter = CitiesListActivityPresenter(this)
            presenter.getDataFromURL(this)
        } else {
            showMessage(getString(R.string.no_network_message))
        }
    }

    private fun initRecyclerView() {
        recycler_view
            .apply {
                layoutManager = LinearLayoutManager(this@CitiesListActivity)
                val topSpacingDecorator = TopSpacing(30)
                addItemDecoration(topSpacingDecorator)
            }
    }

    override fun onGetDataSuccess(list: List<CitiesListResponse?>?) {
        progressBar.visibility = View.GONE
        if (list == null) {
            showMessage(getString(R.string.no_data_available))
        } else {
            myadapter = CitiesListAdapter(list, this)
            recycler_view.adapter = myadapter
        }
    }

    override fun onGetDataFailure(message: String?) {
        showMessage(message)
    }

    override fun onItemClicked(selectedCityItem: CitiesListResponse?) {
        val intent = Intent(this, CityWeatherDetailsActivity::class.java)
        val gson = Gson()
        val selectedCity = gson.toJson(selectedCityItem)
        intent.putExtra("selectedCityAsString", selectedCity)
        startActivity(intent)
    }

    private fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}





