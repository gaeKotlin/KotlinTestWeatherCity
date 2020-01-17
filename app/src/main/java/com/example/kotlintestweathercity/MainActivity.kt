package com.example.kotlintestweathercity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.spCity

class MainActivity : AppCompatActivity() {
    val vGoda = arrayOf("Зима", "Весна", "Лето", "Осень")
    val TYPE_CITY = "Тип города: "
    val TEMP_S = "Средняя температура за сезон: "
    val TEMP_LEN = 5                                              //  Длина выводимой температуры
    val TEMP_LOG = "TestWeatherCity"

    fun getTempSeason(){                                          //  Вывести среднюю температуру сезона
        val nameCity = spCity.selectedItem.toString()
        var izmTemp:Int = 0
        if(radioBF.isChecked){
            izmTemp = 1
        }
        if(radioBK.isChecked){
            izmTemp = 2
        }
        val checked = spSYear.selectedItemPosition                  //  получаем № времени года
        val ts = TempSeason(this, izmTemp, checked, nameCity, TEMP_LEN, TEMP_S)
        val tsDecorator = TempSeasonDecorator(ts, TEMP_LOG)
        tvTemp.text = tsDecorator.geTempSeason()
    }

    fun getTypeCity(){                                          //  Вывести тип города
        val nameCity = spCity.selectedItem.toString()
        val type = CityFactory(this).getType(nameCity)  //  "Factory": Получать тип города в зависимости от его названия
        when(type){
            CityTypes.small -> tvTypeCity.text = "${TYPE_CITY} маленький"
            CityTypes.middle -> tvTypeCity.text = "${TYPE_CITY} средненький"
            CityTypes.big -> tvTypeCity.text = "${TYPE_CITY} большой"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        radioBC.isChecked = true
        //  Адаптер для времён года
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vGoda)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSYear.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        val view = findViewById<Spinner>(R.id.spCity).rootView
        readCity(view, this, spCity)
        getTypeCity()
        getTempSeason()

        spCity.onItemSelectedListener = object :            //  Слушатель на изменение города
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p: Int, id: Long ) {
                getTypeCity()
                getTempSeason()
            }
        }
        spSYear.onItemSelectedListener = object :            //  Слушатель на изменение времени года
            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p: Int, id: Long ) {
                getTempSeason()
            }
        }

            rgTemp.setOnCheckedChangeListener(object :      //  Слушатель на изменение переключателя Цельсия...Кельвина
                RadioGroup.OnCheckedChangeListener{
                override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                    getTempSeason()
                }
            })

        //  Для отображения средней температуры во всплывающем сообщении
        val liveData = AverageTemp.getAverageTemp()
            liveData.observe(this, Observer { value ->
            mess(view, "$TEMP_S = $value")
        })
    }

    fun startSettings(view: View) {
        val intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)                                               //  Запускаем настройки
    }
}
