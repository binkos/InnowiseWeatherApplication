package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.innowiseweatherapplication.adapter.WeatherRecyclerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.model.modelImpl.MainModel
import com.example.innowiseweatherapplication.presenter.IMainPresenterInterface
import com.example.innowiseweatherapplication.view.IMainView
import com.example.innowiseweatherapplication.view.viewImpl.TodayFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view:IMainView):
    IMainPresenterInterface {
    private val model = MainModel()
    lateinit var weather:WeatherClass

    @SuppressLint("CheckResult")
    override fun getData(lat:Double, lon:Double) {
        view.showProgress()
        val dataObservable: Observable<WeatherClass> = model.getWeather(lat, lon)

        dataObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { e->
                run {
                    println("we got ${e.cause}")
                }
            }
            .subscribe{ it ->
                println("HERE")
                weather = it
                val arrayList = ArrayList<RecyclerItemWeatherClass>()
                it.list?.forEach {
                    arrayList.add(RecyclerItemWeatherClass(it.weather[0].icon,it.main!!.temp-273,it.weather[0].main,it.dtTxt as String))
                }
                view.hideProgress()
                view.openTodayWeather(weather,arrayList)
            }
    }



}