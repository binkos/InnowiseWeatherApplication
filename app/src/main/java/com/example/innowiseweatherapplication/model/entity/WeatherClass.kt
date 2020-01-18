package com.example.innowiseweatherapplication.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherClass (
    @SerializedName("cod")
    @Expose
    val cod: String = "",
    @SerializedName("message")
    @Expose
    var main: Int = 0,
    @SerializedName("cnt")
    @Expose
    var cnt: Int = 0,
    @SerializedName("list")
    @Expose
    var list: List<InfoWeatherClass>? = null,
    @SerializedName("city")
    @Expose
    var city: City? = null
){
    data class InfoWeatherClass(
        @SerializedName("dt")
        @Expose
        var dt: Int = 0,

        @SerializedName("main")
        @Expose
        var main: MainWeatherClass? = null,

        @SerializedName("weather")
        @Expose
        var weather: List<Weather> = ArrayList(),

        @SerializedName("clouds")
        @Expose
        var clouds: Cloud? = null,

        @SerializedName("wind")
        @Expose
        var wind: Wind? = null,

        @SerializedName("sys")
        @Expose
        var sys: Sys? = null,

        @SerializedName("dt_txt")
        @Expose
        var dtTxt: String? = null) {

        class Weather {
            @SerializedName("id")
            @Expose
            var id = 0
            @SerializedName("main")
            @Expose
            var main = ""
            @SerializedName("description")
            @Expose
            var description = ""
            @SerializedName("icon")
            @Expose
            var icon = ""
        }

        class MainWeatherClass(
            @SerializedName("temp")
            @Expose
            var temp: Float = 0f,
            @SerializedName("feels_like")
            @Expose
            var feelsLike: Float = 0f,
            @SerializedName("temp_min")
            @Expose
            var tempMin: Float = 0f,
            @SerializedName("temp_max")
            @Expose
            var tempMax: Float = 0f,
            @SerializedName("pressure")
            @Expose
            var pressure: Int = 0,
            @SerializedName("sea_level")
            @Expose
            var seaLevel: Int = 0,
            @SerializedName("grnd_level")
            @Expose
            var grndLevel: Int = 0,
            @SerializedName("humidity")
            @Expose
            var humidity: Int = 0,
            @SerializedName("temp_kf")
            @Expose
            var tempKf: Float = 0f):Parcelable {

            constructor(parcel: Parcel):this(
                parcel.readFloat(),
                parcel.readFloat(),
                parcel.readFloat(),
                parcel.readFloat(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readInt(),
                parcel.readFloat()
            )

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeFloat(temp)
                parcel.writeFloat(feelsLike)
                parcel.writeFloat(tempMin)
                parcel.writeFloat(tempMax)
                parcel.writeInt(pressure)
                parcel.writeInt(seaLevel)
                parcel.writeInt(grndLevel)
                parcel.writeInt(humidity)
                parcel.writeFloat(tempKf)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<MainWeatherClass> {
                override fun createFromParcel(parcel: Parcel): MainWeatherClass {
                    return MainWeatherClass(parcel)
                }

                override fun newArray(size: Int): Array<MainWeatherClass?> {
                    return arrayOfNulls(size)
                }
            }
        }

        class Cloud {
            @SerializedName("all")
            @Expose
            var all = 0
        }

        class Wind {
            @SerializedName("speed")
            @Expose
            var speed = 0f
            @SerializedName("deg")
            @Expose
            var deg = 0f

        }

        class Sys {
            @SerializedName("pod")
            @Expose
            var pod = ""
        }

    }

    class City {
    }

}