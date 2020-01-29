package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.annotation.SuppressLint
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.model.modelImpl.MainModel
import com.example.innowiseweatherapplication.model.modelImpl.SomeTypesHelper
import com.example.innowiseweatherapplication.presenter.IMainPresenterInterface
import com.example.innowiseweatherapplication.view.IMainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private var view:IMainView?,private val someTypesHelper: SomeTypesHelper):
    IMainPresenterInterface {
    private val model = MainModel()
    lateinit var weather:WeatherClass



    @SuppressLint("CheckResult")
    override fun getData(cityName:String) {

        val dataObservable: Observable<WeatherClass> = model.getWeather(cityName)
        dataObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {it ->
                weather = it
                val arrayList = ArrayList<RecyclerItemWeatherClass>()
                it.list?.forEach {
                    val array = someTypesHelper.parseFunction(it.dtTxt!!)
                    val parceledDay = someTypesHelper.anotherParcelFunction(array[1])
                    if (array[0]==0&&arrayList.size!=1) arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.HEADER_TYPE, day = parceledDay))
                    if (array[0]==21){
                    arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS,it.weather[0].icon,
                        it.main!!.temp-273,it.weather[0].main,parceledDay,array[0]))
                    }
                    else{
                    arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.WEATHER_TYPE,it.weather[0].icon,
                        it.main!!.temp-273,it.weather[0].main,parceledDay,array[0]))
                    }
                }

                val todayWeatherClass = TodayWeatherClass(
                    it.list!![0].main!!.humidity,
                    it.list!![0].wind!!.speed,
                    it.list!![0].wind!!.deg,
                    it.list!![0].main!!.temp-273,
                    it.list!![0].main!!.seaLevel,
                    it.list!![0].weather[0].main,
                    it.list!![0].weather[0].icon,
                    it.city!!.country,
                    it.list!![0].main!!.pressure,
                    it.city!!.name
                )
                view!!.hideProgress()
                view!!.openTodayWeather(todayWeatherClass,arrayList)
            },
                {t ->
                    println(t.message)
                    view!!.hideProgress()
                    view!!.showError("Some trouble with loading info")
                }
        )
    }



    @SuppressLint("CheckResult")
    override fun getLastLocation() {
        view!!.showProgress()
        if (someTypesHelper.isConnection()){
            if (someTypesHelper.checkPermission()){
                if (someTypesHelper.isLocationEnabled()){
                    someTypesHelper.mFusedLocationClient.lastLocation.addOnCompleteListener {task ->
                        val isLocation:Boolean = someTypesHelper.isLocationNull(task.result)
                        if (isLocation) {
                            someTypesHelper.requestNewLocationData()
                            getLastLocation()
                        } else {
                            if (someTypesHelper.getCityWhereYouAre() != "Error")
                                getData(someTypesHelper.getCityWhereYouAre())
                            else{
                                view!!.hideProgress()
                                view!!.showError("Trouble with your location, try to check your emulator is fine")
                            }
                        }
                    }
                }else{
                    someTypesHelper.createNewIntent()
                }
            }else{
                view!!.hideProgress()
                view!!.requestPermission()
            }
        }
        else{
            view!!.hideProgress()
            view!!.showError("You don't have Internet Connection")
            println("Trouble with Internet Connection")
        }
    }

    override fun detachView(){
        view = null
    }



}