package com.example.innowiseweatherapplication.view

import androidx.fragment.app.Fragment
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
fun openTodayWeather(fragment: Fragment)
fun openForecastWeather(fragment: Fragment)
}