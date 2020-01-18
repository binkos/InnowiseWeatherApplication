package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.annotation.SuppressLint
import android.os.Bundle
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
            .subscribe{
                weather = it
                val fragment =
                    TodayFragment()
                val bundle = Bundle()
                bundle.putInt("SIZE",weather.list!!.size)
                fragment.arguments = bundle
                view.hideProgress()
                view.openTodayWeather(fragment)
            }

    }



}