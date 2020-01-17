package com.example.kotlintestweathercity

import android.R
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun readCity(view: View, context: Context, spCity: Spinner) {                                //  Читаем города из БД
    var adapter: ArrayAdapter<String>? = null
    val liveData: LiveData<List<String>> = DataController(context).getCities()
    liveData.observe(context as LifecycleOwner, Observer { value ->
        adapter = ArrayAdapter<String>(context, R.layout.simple_spinner_item, value)
        spCity.setAdapter(adapter)
    })
}

fun mess(view: View, s: String){
    Snackbar.make(view, s, Snackbar.LENGTH_LONG).show()
}