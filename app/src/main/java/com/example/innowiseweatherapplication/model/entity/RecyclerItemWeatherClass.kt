package com.example.innowiseweatherapplication.model.entity

import android.os.Parcel
import android.os.Parcelable

class RecyclerItemWeatherClass(
    private val icon : String = "",
    private val temp:Float = 0f,
    val name: String="",
    private val time:String="") :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readFloat(),
        parcel.readString() as String,
        parcel.toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(icon)
        parcel.writeFloat(temp)
        parcel.writeString(name)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecyclerItemWeatherClass> {
        override fun createFromParcel(parcel: Parcel): RecyclerItemWeatherClass {
            return RecyclerItemWeatherClass(parcel)
        }

        override fun newArray(size: Int): Array<RecyclerItemWeatherClass?> {
            return arrayOfNulls(size)
        }
    }
}