package com.example.innowiseweatherapplication.api

import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {

    @GET("forecast?appid=${WeatherRetrofitApi.API_KEY}")
    fun getWeather(@Query("lat") lat: Long,@Query("lon") lon: Long):Observable<WeatherClass>

}