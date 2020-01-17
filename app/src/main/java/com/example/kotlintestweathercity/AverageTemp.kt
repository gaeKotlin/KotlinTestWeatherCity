package com.example.kotlintestweathercity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object AverageTemp {
    val liveData = MutableLiveData<Double>()

    fun getAverageTemp(): LiveData<Double> {
        return liveData
    }

    fun setAverageTemp(temp:Double){
        liveData.value = temp
    }
}