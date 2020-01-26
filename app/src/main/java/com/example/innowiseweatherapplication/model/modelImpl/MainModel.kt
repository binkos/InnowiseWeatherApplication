package com.example.innowiseweatherapplication.model.modelImpl

import com.example.innowiseweatherapplication.api.WeatherRetrofitApi
import com.example.innowiseweatherapplication.model.IModel
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.Observable


class MainModel:IModel {
    override fun getWeather(cityName:String): Observable<WeatherClass> {
        return WeatherRetrofitApi.provideWeather(cityName)
    }

}