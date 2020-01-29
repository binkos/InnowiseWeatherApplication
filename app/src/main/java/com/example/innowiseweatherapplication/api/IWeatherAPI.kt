package com.example.innowiseweatherapplication.api


import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.Observable

interface IWeatherAPI {

    fun getWeather(): Observable<WeatherClass>

}