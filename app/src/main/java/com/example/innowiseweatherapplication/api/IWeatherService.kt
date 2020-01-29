package com.example.innowiseweatherapplication.api


import com.example.innowiseweatherapplication.model.entity.WeatherClass
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {

    @GET("forecast?appid=${WeatherRetrofitApi.API_KEY}")
    fun getWeather(@Query("q") city: String): Observable<WeatherClass>


}