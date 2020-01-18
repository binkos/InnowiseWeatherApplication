package com.example.innowiseweatherapplication.api

import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.Observable
import retrofit2.Call

interface IWeatherAPI {

    fun getWeather(): Observable<WeatherClass>
//    fun getWeather(): Call<WeatherClass>

}