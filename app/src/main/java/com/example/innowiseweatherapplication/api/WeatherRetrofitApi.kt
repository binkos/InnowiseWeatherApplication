package com.example.innowiseweatherapplication.api

import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

object WeatherRetrofitApi {
    const val API_KEY = "bd4dc296a14bbd46eb088fb9125aca73"
    private val retrofit = Retrofit.Builder().baseUrl("http://api.openweathermap.org/data/2.5/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(IWeatherService::class.java)

    fun provideWeather(lat:Double,lon:Double): Observable<WeatherClass> {
        return api.getWeather(lat, lon)

//        val date: Date? = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).parse("")
//        val calendar:Calendar = Calendar.getInstance()
//        calendar.time = date as Date
//        calendar.get(Calendar.YEAR)
    }

}