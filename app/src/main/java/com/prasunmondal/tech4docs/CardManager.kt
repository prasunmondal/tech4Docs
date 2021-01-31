package com.prasunmondal.tech4docs

import android.content.Context
import android.util.Log
import com.prasunmondal.tech4docs.models.Cards
import java.io.Serializable
import java.lang.Exception

class CardManager: Serializable {
    object singleton {
        var instance = CardManager()
    }

    var cardsList: ArrayList<Cards> = arrayListOf()

    fun read(context: Context) {
        var ioObject = IOObjectToFile()
        try {
            cardsList = ioObject.ReadObjectFromFile(
                context,
                StringConstants.FILENAME_PHONEBOOK
            ) as ArrayList<Cards>
        } catch (e: Exception) {
            Log.e("AppLog","No Data Read in CardManager.read")
            Log.e("AppLog", e.stackTraceToString())
        }
    }

    fun write(context: Context) {
        try {
            var ioObject = IOObjectToFile()
            ioObject.WriteObjectToFile(context, StringConstants.FILENAME_PHONEBOOK, cardsList)
            Log.e("AppLog", "Data Written Successfully.. in CardManager.read")
            Log.e("AppLog", cardsList.toString())
        } catch (e: Exception) {
            Log.e("AppLog","No Data Written in CardManager.write")
            Log.e("AppLog", e.stackTraceToString())
        }
    }
}