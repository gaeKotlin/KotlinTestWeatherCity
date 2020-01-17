package com.example.kotlintestweathercity

import android.content.Context

class CityFactory(val context:Context){
    fun getType(name: String):CityTypes{	                    //	Вернёт тип города
        var rez:CityTypes
        val r:Int = DataController(context).getTypeCity(name)
        rez = when(r){
            0 -> CityTypes.small
            1 -> CityTypes.middle
            2 -> CityTypes.big
            else -> CityTypes.small
        }
        return rez
    }
}

enum class CityTypes{
    small,
    middle,
    big;
}