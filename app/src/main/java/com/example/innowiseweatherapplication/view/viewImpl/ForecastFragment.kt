package com.example.innowiseweatherapplication.view.viewImpl

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.adapter.WeatherRecyclerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass

class ForecastFragment:Fragment() {

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.forecast_weather_fragment_view,container,false)
        println("Forecast Fragment")

        val list = arguments?.getParcelableArrayList<RecyclerItemWeatherClass>("ARRAY") as ArrayList<RecyclerItemWeatherClass>
        val listID = arrayListOf<Int>()

        list.forEach {
            if (it.type==1){
                listID.add(list.indexOf(it))
            }
             }

        val recyclerView=view.findViewById<RecyclerView>(R.id.rec_view)
            recyclerView.adapter = WeatherRecyclerAdapter(this.requireContext(),
                list
            )
        recyclerView.layoutManager = LinearLayoutManager(activity)


        val layMan = recyclerView.layoutManager as LinearLayoutManager
        var firstItemPosition:Int
        var prevItemPos:Int=-1
        val dayTextView = view.findViewById<TextView>(R.id.day_TextView3)

//        if ((list.size-40)==4)

        recyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            @SuppressLint("SetTextI18n")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                firstItemPosition = layMan.findFirstVisibleItemPosition()
                if (prevItemPos!=firstItemPosition){
                    when(firstItemPosition){
                        0->{dayTextView.text = "Today"}
                        (listID[0]-1)->{dayTextView.text = "Today"}
                        (listID[0]+1)->{dayTextView.text = list[listID[0]].day}
                        (listID[1]-1)->{dayTextView.text = list[listID[0]].day}
                        (listID[1]+1)->{dayTextView.text = list[listID[1]].day}
                        (listID[2]-1)->{dayTextView.text = list[listID[1]].day}
                        (listID[2]+1)->{dayTextView.text = list[listID[2]].day}
                        (listID[3]-1)->{dayTextView.text = list[listID[2]].day}
                        (listID[3]+1)->{dayTextView.text = list[listID[3]].day}
                        (listID[4]-1)->{dayTextView.text = list[listID[3]].day}
                        (listID[4]+1)->{dayTextView.text = list[listID[4]].day}

                    }
                }
                prevItemPos = firstItemPosition
            }
        })


        return view
    }

}