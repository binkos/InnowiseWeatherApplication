package com.example.innowiseweatherapplication.presenter

interface IMainPresenterInterface {
    fun getData(cityName:String)
    fun detachView()
    fun getLastLocation()
}