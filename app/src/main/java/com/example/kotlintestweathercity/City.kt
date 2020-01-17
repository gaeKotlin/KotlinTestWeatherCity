package com.example.kotlintestweathercity

import android.content.Context

class City(val context: Context?, var name:String = "") {
    var type:Int = 0
    var t1:Int = 0
    var t2:Int = 0
    var t3:Int = 0
    var t4:Int = 0
    var t5:Int = 0
    var t6:Int = 0
    var t7:Int = 0
    var t8:Int = 0
    var t9:Int = 0
    var t10:Int = 0
    var t11:Int = 0
    var t12:Int = 0
    private var new = false

    init{
        if (name == ""){                                            //  если город не указан, то
            val listStr = DataController(context).getFirstCity()    //  получаем первый
            name = listStr[0]
            type = listStr[1].toInt()
            t1 = listStr[2].toInt()
            t2 = listStr[3].toInt()
            t3 = listStr[4].toInt()
            t4 = listStr[5].toInt()
            t5 = listStr[6].toInt()
            t6 = listStr[7].toInt()
            t7 = listStr[8].toInt()
            t8 = listStr[9].toInt()
            t9 = listStr[10].toInt()
            t10 = listStr[11].toInt()
            t11 = listStr[12].toInt()
            t12 = listStr[13].toInt()
        }else{                                                      //  если город указан, то
            val flag = DataController(context).getTypeCity(name)
            if(flag > -1){                                          //  если в БД город есть, то
                val listInt = DataController(context).getParamCityList(name)
                type = listInt[0]                                   //  записываем
                t1 = listInt[1]
                t2 = listInt[2]
                t3 = listInt[3]
                t4 = listInt[4]
                t5 = listInt[5]
                t6 = listInt[6]                                     //  в объект
                t7 = listInt[7]
                t8 = listInt[8]
                t9 = listInt[9]
                t10 = listInt[10]
                t11 = listInt[11]
                t12 = listInt[12]                                  //   данные из БД
            }else{                                                 //   если в БД города нет, то
                new = true                                         //   город новый
            }
        }
    }
    fun saveInBD(){                                                //  сохраняет текущий город в БД
        if(new) {                                                  //   если город новый то,
            DataController(context).saveBD(                         //  сохранить его
                name,
                type,
                t1,
                t2,
                t3,
                t4,
                t5,
                t6,
                t7,
                t8,
                t9,
                t10,
                t11,
                t12
            )
            new = false
        }else{                                                     //   если город ранее уже был в БД, то
            DataController(context).updBD(                         //  обновить его
                name,
                type,
                t1,
                t2,
                t3,
                t4,
                t5,
                t6,
                t7,
                t8,
                t9,
                t10,
                t11,
                t12
            )
        }
    }
}