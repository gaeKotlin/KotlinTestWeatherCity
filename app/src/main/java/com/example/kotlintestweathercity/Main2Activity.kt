package com.example.kotlintestweathercity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

    var dbHelper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        dbHelper = DBHelper(this)  //  Открываем БД
    }

    override fun onResume() {
        super.onResume()
        val view = findViewById<Spinner>(R.id.spCity).rootView
        readCity(view, this, spCity)
        readBD(view)
        spCity.onItemSelectedListener = object :            //  Слушатель на изменение города
            OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, p: Int, id: Long ) {
                readBD(view!!)
            }
        }
    }

    fun startMain(view: View) {     //  для кнопки Назад
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    fun addNewCity(view: View) {
        val newCity = etNewCity.text.toString()
        val database = dbHelper!!.writableDatabase      //  Получаем экземпляр БД
        val contentValues = ContentValues()             //  Для добавления строки в таблицу

        contentValues.put(DBHelper.KEY_NAME, newCity)  //  начало заполнения записи
        contentValues.put(DBHelper.KEY_TYPE, 1)
        contentValues.put(DBHelper.KEY_TEMP1, 4)
        contentValues.put(DBHelper.KEY_TEMP2, 7)
        contentValues.put(DBHelper.KEY_TEMP3, 13)
        contentValues.put(DBHelper.KEY_TEMP4, 17)
        contentValues.put(DBHelper.KEY_TEMP5, 21)
        contentValues.put(DBHelper.KEY_TEMP6, 23)
        contentValues.put(DBHelper.KEY_TEMP7, 28)
        contentValues.put(DBHelper.KEY_TEMP8, 25)
        contentValues.put(DBHelper.KEY_TEMP9, 21)
        contentValues.put(DBHelper.KEY_TEMP10, 16)
        contentValues.put(DBHelper.KEY_TEMP11, 12)
        contentValues.put(DBHelper.KEY_TEMP12, 8)      //  оКОНЧАНИЕ заполнения записи
        database.insert(DBHelper.TABLE_CITY, null, contentValues)  //  вставляем запись в таблицу

        mess(view, "Город: $newCity успешно записан в БД.")
        dbHelper!!.close()       //  Закрываем соединение с БД
        readCity(view, this, spCity)
    }

    fun readBD(view: View) {                                    //  Читаем все данные по городу из БД
        val cityName = spCity.getSelectedItem().toString()      //  получить наименование города
        val liveData: LiveData<List<Int>> = DataController(this).getParamCity(cityName)
        liveData.observe(this, Observer { value ->
            spType.setSelection(value[0])                       //  Устанавливаем позицию
            etTemp1.setText(value[1].toString())
            etTemp2.setText(value[2].toString())
            etTemp3.setText(value[3].toString())
            etTemp4.setText(value[4].toString())
            etTemp5.setText(value[5].toString())
            etTemp6.setText(value[6].toString())
            etTemp7.setText(value[7].toString())
            etTemp8.setText(value[8].toString())
            etTemp9.setText(value[9].toString())
            etTemp10.setText(value[10].toString())
            etTemp11.setText(value[11].toString())
            etTemp12.setText(value[12].toString())
        })
    }

    fun saveBD(view: View) {
        val updCity = spCity.selectedItem.toString()    //  Название города
        val database = dbHelper!!.writableDatabase      //  Получаем экземпляр БД
        val contentValues = ContentValues()             //  Для изменения строки в таблице

        contentValues.put(DBHelper.KEY_TYPE, spType.selectedItemPosition)
        contentValues.put(DBHelper.KEY_TEMP1, etTemp1.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP2, etTemp2.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP3, etTemp3.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP4, etTemp4.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP5, etTemp5.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP6, etTemp6.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP7, etTemp7.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP8, etTemp8.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP9, etTemp9.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP10, etTemp10.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP11, etTemp11.text.toString().toInt())
        contentValues.put(DBHelper.KEY_TEMP12, etTemp12.text.toString().toInt())
        //  обновляем запись в таблице
        database.update(DBHelper.TABLE_CITY, contentValues, """ ${DBHelper.KEY_NAME} = "$updCity" """, null)
        mess(view, "Данные по городу: $updCity успешно обновлены в БД.")
    }

}
