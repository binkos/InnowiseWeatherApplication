package com.example.innowiseweatherapplication.model

import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.Observable


interface IModel {
    fun getWeather(cityName:String): Observable<WeatherClass>
}