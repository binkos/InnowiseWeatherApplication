package com.example.innowiseweatherapplication.view

import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass

interface IMainView {
fun showError()
fun showProgress()
fun hideProgress()
fun showLoadedWeather(provideWeather: WeatherClass?)
fun showNotConnectionMessage()
fun checkPermission(): Boolean
fun requestPermission()
fun isLocationEnabled():Boolean
fun getLastLocation()
fun openTodayWeather(todayWeatherClass: TodayWeatherClass,arrayList: ArrayList<RecyclerItemWeatherClass>)
}