package com.example.kotlintestweathercity

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataController(val context: Context?) {
    private val liveData = MutableLiveData<List<String>>()
    private val liveData2 = MutableLiveData<List<Int>>()
    var dbHelper: DBHelper? = null
    val listStr = arrayListOf<String>()
    val listInt = arrayListOf<Int>()

    init {
        dbHelper = DBHelper(context)  //  Открываем БД

    }
    fun getCities():LiveData<List<String>>{             //  Получить список городов
        val database = dbHelper!!.writableDatabase      //  Получаем экземпляр БД
        val cursor = database.query(DBHelper.TABLE_CITY, null, null, null, null, null, null);
        if (cursor.moveToFirst()) { //  Перемещает курсор на первую строку в результате запроса
            val nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME)
            do {
                listStr.add(cursor.getString(nameIndex).toString())         //  Название города в список
            } while (cursor.moveToNext())
        }
        cursor.close()
        liveData.value = listStr
        return liveData
    }

    fun getTypeCity(cityName: String):Int{                          //  Вернёт тип города
        var rez: Int = -1                                           //  если -1, то такого города нет!
        val database = dbHelper!!.writableDatabase                  //  Получаем экземпляр БД
        val cursor = database.query(DBHelper.TABLE_CITY, null, """ ${DBHelper.KEY_NAME} = "$cityName" """, null, null, null, null);
        if (cursor.moveToFirst()) {                                 //  Перемещает курсор на первую строку в результате запроса
            val typeIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE)
            rez = cursor.getString(typeIndex).toInt()
        }
        cursor.close()
        return rez
    }

    fun getFirstCity():List<String>{                                //  получить 1й город со всеми параметрами
        val database = dbHelper!!.writableDatabase                  //  Получаем экземпляр БД
        val cursor = database.query(DBHelper.TABLE_CITY, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {                                 //  Перемещает курсор на первую строку в результате запроса
            val nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME)
            val typeIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE)
            val temp1Index = cursor.getColumnIndex(DBHelper.KEY_TEMP1)
            val temp2Index = cursor.getColumnIndex(DBHelper.KEY_TEMP2)
            val temp3Index = cursor.getColumnIndex(DBHelper.KEY_TEMP3)
            val temp4Index = cursor.getColumnIndex(DBHelper.KEY_TEMP4)
            val temp5Index = cursor.getColumnIndex(DBHelper.KEY_TEMP5)
            val temp6Index = cursor.getColumnIndex(DBHelper.KEY_TEMP6)
            val temp7Index = cursor.getColumnIndex(DBHelper.KEY_TEMP7)
            val temp8Index = cursor.getColumnIndex(DBHelper.KEY_TEMP8)
            val temp9Index = cursor.getColumnIndex(DBHelper.KEY_TEMP9)
            val temp10Index = cursor.getColumnIndex(DBHelper.KEY_TEMP10)
            val temp11Index = cursor.getColumnIndex(DBHelper.KEY_TEMP11)
            val temp12Index = cursor.getColumnIndex(DBHelper.KEY_TEMP12)

            listStr.add(cursor.getString(nameIndex).toString())         //  имя в список
            listStr.add(cursor.getString(typeIndex).toString())         //  тип в список
            listStr.add(cursor.getString(temp1Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp2Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp3Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp4Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp5Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp6Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp7Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp8Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp9Index).toString())        //  температуру в список
            listStr.add(cursor.getString(temp10Index).toString())       //  температуру в список
            listStr.add(cursor.getString(temp11Index).toString())       //  температуру в список
            listStr.add(cursor.getString(temp12Index).toString())       //  температуру в список
        }
        cursor.close()
        return listStr                                                  //   0й - имя, 1й - тип, 2й - температура января и т.д.
    }

    fun getParamCityList(cityName: String):List<Int>{
        val database = dbHelper!!.writableDatabase                  //  Получаем экземпляр БД
        val cursor = database.query(DBHelper.TABLE_CITY, null, """ ${DBHelper.KEY_NAME} = "$cityName" """, null, null, null, null);
        if (cursor.moveToFirst()) {                                 //  Перемещает курсор на первую строку в результате запроса
            val typeIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE)
            val temp1Index = cursor.getColumnIndex(DBHelper.KEY_TEMP1)
            val temp2Index = cursor.getColumnIndex(DBHelper.KEY_TEMP2)
            val temp3Index = cursor.getColumnIndex(DBHelper.KEY_TEMP3)
            val temp4Index = cursor.getColumnIndex(DBHelper.KEY_TEMP4)
            val temp5Index = cursor.getColumnIndex(DBHelper.KEY_TEMP5)
            val temp6Index = cursor.getColumnIndex(DBHelper.KEY_TEMP6)
            val temp7Index = cursor.getColumnIndex(DBHelper.KEY_TEMP7)
            val temp8Index = cursor.getColumnIndex(DBHelper.KEY_TEMP8)
            val temp9Index = cursor.getColumnIndex(DBHelper.KEY_TEMP9)
            val temp10Index = cursor.getColumnIndex(DBHelper.KEY_TEMP10)
            val temp11Index = cursor.getColumnIndex(DBHelper.KEY_TEMP11)
            val temp12Index = cursor.getColumnIndex(DBHelper.KEY_TEMP12)

            listInt.add(cursor.getString(typeIndex).toInt())         //  тип в список
            listInt.add(cursor.getString(temp1Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp2Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp3Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp4Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp5Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp6Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp7Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp8Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp9Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp10Index).toInt())       //  температуру в список
            listInt.add(cursor.getString(temp11Index).toInt())       //  температуру в список
            listInt.add(cursor.getString(temp12Index).toInt())       //  температуру в список
        }
        cursor.close()
        return listInt                                             //   0й - тип, 1й - температура января и т.д.
    }

    fun getParamCity(cityName: String):LiveData<List<Int>>{
        val database = dbHelper!!.writableDatabase                  //  Получаем экземпляр БД
        val cursor = database.query(DBHelper.TABLE_CITY, null, """ ${DBHelper.KEY_NAME} = "$cityName" """, null, null, null, null);
        if (cursor.moveToFirst()) {                                 //  Перемещает курсор на первую строку в результате запроса
            val typeIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE)
            val temp1Index = cursor.getColumnIndex(DBHelper.KEY_TEMP1)
            val temp2Index = cursor.getColumnIndex(DBHelper.KEY_TEMP2)
            val temp3Index = cursor.getColumnIndex(DBHelper.KEY_TEMP3)
            val temp4Index = cursor.getColumnIndex(DBHelper.KEY_TEMP4)
            val temp5Index = cursor.getColumnIndex(DBHelper.KEY_TEMP5)
            val temp6Index = cursor.getColumnIndex(DBHelper.KEY_TEMP6)
            val temp7Index = cursor.getColumnIndex(DBHelper.KEY_TEMP7)
            val temp8Index = cursor.getColumnIndex(DBHelper.KEY_TEMP8)
            val temp9Index = cursor.getColumnIndex(DBHelper.KEY_TEMP9)
            val temp10Index = cursor.getColumnIndex(DBHelper.KEY_TEMP10)
            val temp11Index = cursor.getColumnIndex(DBHelper.KEY_TEMP11)
            val temp12Index = cursor.getColumnIndex(DBHelper.KEY_TEMP12)

            listInt.add(cursor.getString(typeIndex).toInt())         //  тип в список
            listInt.add(cursor.getString(temp1Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp2Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp3Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp4Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp5Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp6Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp7Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp8Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp9Index).toInt())        //  температуру в список
            listInt.add(cursor.getString(temp10Index).toInt())       //  температуру в список
            listInt.add(cursor.getString(temp11Index).toInt())       //  температуру в список
            listInt.add(cursor.getString(temp12Index).toInt())       //  температуру в список
        }
        cursor.close()
        liveData2.value = listInt
        return liveData2                                             //   0й - тип, 1й - температура января и т.д.
    }
    fun saveBD(newCity:String, type: Int, t1: Int, t2: Int, t3: Int, t4: Int, t5: Int, t6: Int, t7: Int, t8: Int, t9: Int, t10: Int, t11: Int, t12: Int) {
        val database = dbHelper!!.writableDatabase      //  Получаем экземпляр БД
        val contentValues = ContentValues()             //  Для добавления строки в таблицу

        contentValues.put(DBHelper.KEY_NAME, newCity)  //  начало заполнения записи
        contentValues.put(DBHelper.KEY_TYPE, type)
        contentValues.put(DBHelper.KEY_TEMP1, t1)
        contentValues.put(DBHelper.KEY_TEMP2, t2)
        contentValues.put(DBHelper.KEY_TEMP3, t3)
        contentValues.put(DBHelper.KEY_TEMP4, t4)
        contentValues.put(DBHelper.KEY_TEMP5, t5)
        contentValues.put(DBHelper.KEY_TEMP6, t6)
        contentValues.put(DBHelper.KEY_TEMP7, t7)
        contentValues.put(DBHelper.KEY_TEMP8, t8)
        contentValues.put(DBHelper.KEY_TEMP9, t9)
        contentValues.put(DBHelper.KEY_TEMP10, t10)
        contentValues.put(DBHelper.KEY_TEMP11, t11)
        contentValues.put(DBHelper.KEY_TEMP12, t12)      //  оКОНЧАНИЕ заполнения записи
        database.insert(DBHelper.TABLE_CITY, null, contentValues)  //  вставляем запись в таблицу
        dbHelper!!.close()       //  Закрываем соединение с БД
    }

    fun updBD(updCity:String, type: Int, t1: Int, t2: Int, t3: Int, t4: Int, t5: Int, t6: Int, t7: Int, t8: Int, t9: Int, t10: Int, t11: Int, t12: Int) {
        val database = dbHelper!!.writableDatabase      //  Получаем экземпляр БД
        val contentValues = ContentValues()             //  Для изменения строки в таблице

        contentValues.put(DBHelper.KEY_TYPE, type)
        contentValues.put(DBHelper.KEY_TEMP1, t1)
        contentValues.put(DBHelper.KEY_TEMP2, t2)
        contentValues.put(DBHelper.KEY_TEMP3, t3)
        contentValues.put(DBHelper.KEY_TEMP4, t4)
        contentValues.put(DBHelper.KEY_TEMP5, t5)
        contentValues.put(DBHelper.KEY_TEMP6, t6)
        contentValues.put(DBHelper.KEY_TEMP7, t7)
        contentValues.put(DBHelper.KEY_TEMP8, t8)
        contentValues.put(DBHelper.KEY_TEMP9, t9)
        contentValues.put(DBHelper.KEY_TEMP10, t10)
        contentValues.put(DBHelper.KEY_TEMP11, t11)
        contentValues.put(DBHelper.KEY_TEMP12, t12)
        //  обновляем запись в таблице
        database.update(DBHelper.TABLE_CITY, contentValues, """ ${DBHelper.KEY_NAME} = "$updCity" """, null)
    }
/*    companion object {
        val instance = DataController(context)
        fun get(): Any {
            return instance
        }
    }*/
}