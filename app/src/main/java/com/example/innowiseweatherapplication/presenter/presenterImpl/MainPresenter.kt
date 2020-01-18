package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.annotation.SuppressLint
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.model.modelImpl.MainModel
import com.example.innowiseweatherapplication.presenter.IMainPresenterInterface
import com.example.innowiseweatherapplication.view.IMainView
import com.example.innowiseweatherapplication.view.viewImpl.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view:IMainView):
    IMainPresenterInterface {
    private val model = MainModel()


    @SuppressLint("CheckResult")
    override fun getData(lat:Double, lon:Double) {
        view.showProgress()
        val dataObservable: Observable<WeatherClass> = model.getWeather(lat, lon)

        dataObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                view.hideProgress()
                view.showLoadedWeather(it)
            }


//        view.showLoadedWeather()
    }



}