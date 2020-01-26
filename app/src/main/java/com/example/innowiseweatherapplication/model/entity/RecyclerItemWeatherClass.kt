package com.example.innowiseweatherapplication.model.entity

import android.os.Parcel
import android.os.Parcelable

class RecyclerItemWeatherClass(
    val type : Int = 0,
    val icon : String = "",
    val temp:Float = 0f,
    val name: String="",
    val day:String = "",
    val hour:Int=0
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() as String,
        parcel.readFloat(),
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(type)
        parcel.writeString(icon)
        parcel.writeFloat(temp)
        parcel.writeString(name)
        parcel.writeString(day)
        parcel.writeInt(hour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecyclerItemWeatherClass> {
        const val HEADER_TYPE = 1
        const val WEATHER_TYPE = 2
        const val WEATHER_TYPE_WITHOUT_DIVIDERS = 3
        override fun createFromParcel(parcel: Parcel): RecyclerItemWeatherClass {
            return RecyclerItemWeatherClass(parcel)


        }

        override fun newArray(size: Int): Array<RecyclerItemWeatherClass?> {
            return arrayOfNulls(size)
        }
    }
}