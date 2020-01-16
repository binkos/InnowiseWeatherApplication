package com.example.innowiseweatherapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitApi {
    const val API_KEY = "bd4dc296a14bbd46eb088fb9125aca73"
    private val retrofit = Retrofit.Builder().baseUrl("https://samples.openweathermap.org/data/2.5").addConverterFactory(GsonConverterFactory.create()).build()
    private val api = retrofit.create(IWeatherService::class.java)

    fun provideWeather(lat:Long,lon:Long){
        api.getWeather(lat,lon)
    }

}