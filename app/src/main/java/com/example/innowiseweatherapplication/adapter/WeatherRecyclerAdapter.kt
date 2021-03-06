package com.example.innowiseweatherapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class WeatherRecyclerAdapter(val context:Context, private val list:ArrayList<RecyclerItemWeatherClass>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            RecyclerItemWeatherClass.HEADER_TYPE-> HeaderViewHolder(parent.inflate(R.layout.header_list_item))
            RecyclerItemWeatherClass.WEATHER_TYPE-> WeatherViewHolder(parent.inflate(R.layout.weather_item_list))
            RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS-> WeatherViewHolder(parent.inflate(R.layout.weather_item_list_without_dividers))
            else -> ErrorViewHolder(parent.inflate(R.layout.weather_item_list))
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list[position]
        when(item.type){
            RecyclerItemWeatherClass.HEADER_TYPE->(holder as HeaderViewHolder).tv.text = item.day

            RecyclerItemWeatherClass.WEATHER_TYPE->{

               val icon =when(item.icon){
                   "04d"->{R.drawable.ic_04_g}
                   "04n"->{R.drawable.ic_04_g}
                   "01d"->{R.drawable.ic_01d_g}
                   "01n"->{R.drawable.ic_01n_g}
                   "02d"->{R.drawable.ic_01d_g}
                   "02n"->{R.drawable.ic_01n_g}
                   "10d"->{R.drawable.ic_10_g}
                   "10n"->{R.drawable.ic_10_g}
                   "13d"->{R.drawable.ic_13_g}
                   "13n"->{R.drawable.ic_13_g}
                   "03d"->{R.drawable.ic_03d_g}
                   "03n"->{R.drawable.ic_03n_g}

                    else->{2}
                }
                (holder as WeatherViewHolder).bind(icon,item.temp,item.name,item.hour)



            }
            RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS->{
                val icon =when(item.icon){
                    "04d"->{R.drawable.ic_04_g}
                    "04n"->{R.drawable.ic_04_g}
                    "01d"->{R.drawable.ic_01d_g}
                    "01n"->{R.drawable.ic_01n_g}
                    "02d"->{R.drawable.ic_01d_g}
                    "02n"->{R.drawable.ic_01n_g}
                    "10d"->{R.drawable.ic_10_g}
                    "10n"->{R.drawable.ic_10_g}
                    "13d"->{R.drawable.ic_13_g}
                    "13n"->{R.drawable.ic_13_g}
                    "03d"->{R.drawable.ic_03d_g}
                    "03n"->{R.drawable.ic_03n_g}

                    else->{2}
                }

                (holder as WeatherViewHolder).bind(icon,item.temp,item.name,item.hour)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(list[position].type){
            RecyclerItemWeatherClass.HEADER_TYPE-> RecyclerItemWeatherClass.HEADER_TYPE
            RecyclerItemWeatherClass.WEATHER_TYPE-> {
                if(position!=list.size-1) RecyclerItemWeatherClass.WEATHER_TYPE
                else RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS
            }
            RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS-> RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS
            else-> 0
        }
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false)=
        LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)

    class WeatherViewHolder(view:View):RecyclerView.ViewHolder(view){
            val weathIcon = view.findViewById(R.id.weather_icon_ImageView) as ImageView
            private val timeTv = view.findViewById(R.id.time_TextView) as TextView
            private val weatherInfoTv = view.findViewById(R.id.weather_info_TextView) as TextView
            private var tempInfoTV = view.findViewById(R.id.temp_TextView) as TextView

        @SuppressLint("SetTextI18n")
        fun bind(icon:Int, temp:Float, name:String, hour:Int){

            Picasso.get().load(icon).error(R.drawable.ic_01d).into(weathIcon)
            var hourTime = "${hour}.00"
            if (hourTime.length==4) hourTime = "0$hourTime"
            timeTv.text = hourTime
            tempInfoTV.text = "${temp.roundToInt()}°"
            weatherInfoTv.text = name
        }
    }

    class HeaderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tv = itemView.findViewById(R.id.day_TextView) as TextView
    }

    class ErrorViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


}