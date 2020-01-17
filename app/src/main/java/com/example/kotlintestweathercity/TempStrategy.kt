package com.example.kotlintestweathercity

import android.content.Context
import android.util.Log

//  паттерн Стратегия
interface TempStrategy {
    fun getTemp(city:City, sT:SeasonsTypes):Double             //  возвращает ср. температуру за сезон
}

class CelsiusStrategy:TempStrategy{                            //  в цельсиях
    override fun getTemp(city:City, sT:SeasonsTypes):Double{
        var temp: Int = 0
        var rez: Double
        when(sT){                                              //   это надо вынести в лямбду в переменную в Common...
            SeasonsTypes.winter -> temp = city.t12 + city.t1 + city.t2
            SeasonsTypes.spring -> temp = city.t3 + city.t4 + city.t5
            SeasonsTypes.summer -> temp = city.t6 + city.t7 + city.t8
            SeasonsTypes.autumn -> temp = city.t9 + city.t10 + city.t11
        }
        rez = temp.toDouble()/3
        return rez
    }
}

class FahrenheitStrategy:TempStrategy{                         //  в фаренгейтах
    override fun getTemp(city:City, sT:SeasonsTypes):Double{
        var temp: Int = 0
        var rez: Double
        when(sT){                                              //   это надо вынести в лямбду в переменную в Common...
            SeasonsTypes.winter -> temp = city.t12 + city.t1 + city.t2
            SeasonsTypes.spring -> temp = city.t3 + city.t4 + city.t5
            SeasonsTypes.summer -> temp = city.t6 + city.t7 + city.t8
            SeasonsTypes.autumn -> temp = city.t9 + city.t10 + city.t11
        }
        rez = temp.toDouble()/3
        return (9/5 ) * rez + 32
    }
}

class KelvinStrategy:TempStrategy{                             //  в кельвинах
    override fun getTemp(city:City, sT:SeasonsTypes):Double{
        var temp: Int = 0
        var rez: Double
        when(sT){                                              //   это надо вынести в лямбду в переменную в Common...
            SeasonsTypes.winter -> temp = city.t12 + city.t1 + city.t2
            SeasonsTypes.spring -> temp = city.t3 + city.t4 + city.t5
            SeasonsTypes.summer -> temp = city.t6 + city.t7 + city.t8
            SeasonsTypes.autumn -> temp = city.t9 + city.t10 + city.t11
        }
        rez = temp.toDouble()/3
        return rez + 273.15
    }
}

enum class SeasonsTypes{
    winter,
    autumn,
    summer,
    spring;
}

interface TempSeasonInterface{
    fun geTempSeason():String
}

class TempSeason(val context: Context, var izmTemp:Int, var vremGoda:Int, val nameCity:String, val temp_len:Int, val temp_s:String):TempSeasonInterface{
    var averTemp=0.0
    override fun geTempSeason():String{
        val city = City(context, nameCity)
        var tempSeason:TempStrategy = CelsiusStrategy()
        var gr = " цельсия"
        var rez:String
        when(izmTemp){
            1 -> {tempSeason = FahrenheitStrategy(); gr = " фаренгейта"}
            2 -> {tempSeason = KelvinStrategy(); gr = " кельвина"}
        }

        var zn: Double = 0.0
        when(vremGoda){
            0 -> zn = tempSeason.getTemp(city,SeasonsTypes.winter)
            1 -> zn = tempSeason.getTemp(city,SeasonsTypes.spring)
            2 -> zn = tempSeason.getTemp(city,SeasonsTypes.summer)
            3 -> zn = tempSeason.getTemp(city,SeasonsTypes.autumn)
        }
        averTemp = zn
        rez = "$zn"
        if(rez.length > temp_len){
            rez = rez.substring(0,temp_len)
        }
        return "$temp_s $rez град.$gr"
    }
}

class TempSeasonDecorator(val component:TempSeasonInterface, val tag:String):TempSeasonInterface{   //  паттерн "Decorator"
    override fun geTempSeason(): String {                                           //  добавляется логгирование
        var rez = component.geTempSeason()
        AverageTemp.setAverageTemp((component as TempSeason).averTemp)              //  Сохраняем среднюю температуру
        Log.d(tag,"rez = $rez. Добавлен в объект AverageTemp")
        return rez
    }
}

