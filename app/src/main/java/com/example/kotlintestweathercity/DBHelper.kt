package com.example.kotlintestweathercity

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context?): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) { //  Вызывается при первом создании БД
        db.execSQL("create table $TABLE_CITY ($KEY_NAME text primary key, $KEY_TYPE integer, $KEY_TEMP1 integer, $KEY_TEMP2 integer, $KEY_TEMP3 integer, $KEY_TEMP4 integer, $KEY_TEMP5 integer, $KEY_TEMP6 integer, $KEY_TEMP7 integer, $KEY_TEMP8 integer, $KEY_TEMP9 integer, $KEY_TEMP10 integer, $KEY_TEMP11 integer, $KEY_TEMP12 integer)")
    }

    //  Реализуется проверка номера версии
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { //  Вызывается при изменении БД
        if (oldVersion != newVersion) { //  Удаляем таблицу если изменилась версия БД
            db.execSQL("drop table if exists $TABLE_CITY")
            onCreate(db)
        } else Log.d(TAG, "Версия БД не изменилась")
    }

    companion object {
        const val TAG = "mLog"
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Weather"     //  Имя БД
        const val TABLE_CITY = "city"
        const val KEY_NAME = "name"             //  название города
        const val KEY_TYPE = "type"             //  тип города: малый = 0, средний = 1, большой = 2.
        const val KEY_TEMP1 = "temp1"           //  температура января
        const val KEY_TEMP2 = "temp2"           //  температура февраля
        const val KEY_TEMP3 = "temp3"           //  температура марта
        const val KEY_TEMP4 = "temp4"           //  температура апреля
        const val KEY_TEMP5 = "temp5"           //  температура мая
        const val KEY_TEMP6 = "temp6"           //  температура июня
        const val KEY_TEMP7 = "temp7"           //  температура июля
        const val KEY_TEMP8 = "temp8"           //  температура август
        const val KEY_TEMP9 = "temp9"           //  температура сентября
        const val KEY_TEMP10 = "temp10"           //  температура октября
        const val KEY_TEMP11 = "temp11"           //  температура ноября
        const val KEY_TEMP12 = "temp12"           //  температура декабря
    }
}