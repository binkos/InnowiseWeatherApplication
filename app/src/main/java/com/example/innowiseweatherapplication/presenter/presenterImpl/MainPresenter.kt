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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainPresenter(private var view:IMainView?,private val someTypesHelper: SomeTypesHelper):
    IMainPresenterInterface {
    private val model = MainModel()
    lateinit var weather:WeatherClass

    @SuppressLint("CheckResult")
    override fun getData(cityName:String) {
//        view.showProgress()
        val dataObservable: Observable<WeatherClass> = model.getWeather(cityName)
        dataObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {it ->
                weather = it
                val arrayList = ArrayList<RecyclerItemWeatherClass>()
                arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.HEADER_TYPE,day="Today"))
                it.list?.forEach {
                    val array = parseFunction(it.dtTxt!!)
                    val parceledDay = anotherParcelFunction(array[1])
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
                    it.list!![0].main!!.tempKf,
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
                    view!!.showError()
                }
        )
    }

    private fun parseFunction(dateInString:String):IntArray{
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
        formatter.timeZone = TimeZone.getDefault()
        val date: Date = formatter.parse(dateInString) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return intArrayOf(hourOfDay,dayOfWeek)
    }

    private fun anotherParcelFunction(date:Int):String = when(date){
        1->"Sunday"
        2->"Monday"
        3->"Tuesday"
        4->"Wednesday"
        5->"Thursday"
        6->"Friday"
        7->"Saturday"
        else -> "ERROR"
    }

    @SuppressLint("CheckResult")
    fun getLastLocation() {
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
                            getData(someTypesHelper.getCityWhereYouAre())
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
            view!!.showNotConnectionMessage()
            println("Trouble with Internet Connection")
        }
    }

    fun detachView(){
        view = null
    }



}