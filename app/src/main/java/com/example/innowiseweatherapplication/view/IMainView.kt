package com.example.innowiseweatherapplication.view

import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass

interface IMainView {
fun showError(errorType:String)
fun showProgress()
fun hideProgress()
fun requestPermission()
fun openTodayWeather(todayWeatherClass: TodayWeatherClass,arrayList: ArrayList<RecyclerItemWeatherClass>)
fun init()
}