package com.example.innowiseweatherapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.model.entity.WeatherClass

class WeatherRecyclerAdapter(val context:Context,val list:ArrayList<WeatherClass.InfoWeatherClass.Weather>): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(parent.inflate(R.layout.weather_item_list))
    }

    override fun getItemCount()= list.size



    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
    }

    class WeatherViewHolder(view:View):RecyclerView.ViewHolder(view){
            val r = view.findViewById(R.id.weather_icon_ImageView) as ImageView
        public fun bind(str:String){

        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind("")
    }

}