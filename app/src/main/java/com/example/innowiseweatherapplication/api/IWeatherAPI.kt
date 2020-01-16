package com.example.innowiseweatherapplication.api

import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.rxjava3.core.Observable

interface IWeatherAPI {

    fun getWeather():Observable<WeatherClass>
}