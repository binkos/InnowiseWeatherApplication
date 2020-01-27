package com.example.innowiseweatherapplication.view

import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass

interface IMainView {
fun showError()
fun showProgress()
fun hideProgress()
fun showNotConnectionMessage()
fun requestPermission()
fun openTodayWeather(todayWeatherClass: TodayWeatherClass,arrayList: ArrayList<RecyclerItemWeatherClass>)
}