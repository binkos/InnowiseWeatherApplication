package com.example.innowiseweatherapplication.api

import com.example.innowiseweatherapplication.model.entity.WeatherClass
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

interface IWeatherService {

    @GET("forecast?appid=${WeatherRetrofitApi.API_KEY}")
    fun getWeather(@Query("lat") lat: Double,@Query("lon") lon: Double): Observable<WeatherClass>


}