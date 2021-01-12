package com.weatherapp.models

class CityWeatherDetails( var weather : List<Weather>, var name : String, var sys : System, var main : Main) {
    inner class Weather(var description : String)
    inner class System(
        var country : String,
        var sunrise : String,
        var sunset : String)

    inner class Main (
        var temp : String,
        var pressure : String,
        var humidity : String,
        var temp_min : String,
        var temp_max : String
    )
}