package com.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.weatherapp.models.CitiesListResponse
import com.weatherapp.R
import kotlinx.android.synthetic.main.cities_list_data.view.*

class CitiesListAdapter(
    list: List<CitiesListResponse?>?,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var citiesList: List<CitiesListResponse?>? = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CitiesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cities_list_data, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return citiesList!!.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CitiesListViewHolder -> {
                citiesList?.get(position)
                holder.bind(citiesList?.get(position), itemClickListener)
            }
        }
    }

    class CitiesListViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cityName: TextView = itemView.city_name

        fun bind(citiesList: CitiesListResponse?, clickListener: OnItemClickListener) {
            cityName.text = citiesList?.name

            itemView.setOnClickListener {
                if (citiesList != null) {
                    clickListener.onItemClicked(citiesList)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(selectedCity: CitiesListResponse?)
    }
}