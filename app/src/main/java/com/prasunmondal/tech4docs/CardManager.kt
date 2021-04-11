package com.prasunmondal.tech4docs

import android.content.Context
import android.util.Log
import com.prasunmondal.tech4docs.utils.FileIO
import com.prasunmondal.tech4docs.xModels.Cards
import java.io.Serializable

class CardManager : Serializable {
    object singleton {
        var instance = CardManager()
    }

    var cardsList: ArrayList<Cards> = arrayListOf()

    fun read(context: Context) {
        var ioObject = FileIO()
        try {
            cardsList = ioObject.ReadObjectFromFile(
                    context,
                    Constants.VAULT_DATA_STORE
            ) as ArrayList<Cards>
        } catch (e: Exception) {
            Log.e("AppLog", "No Data Read in CardManager.read")
            Log.e("AppLog", e.stackTraceToString())
        }
    }

    fun write(context: Context) {
        try {
            var ioObject = FileIO()
            ioObject.WriteObjectToFile(context, Constants.VAULT_DATA_STORE, cardsList)
            Log.e("AppLog", "Data Written Successfully.. in CardManager.read")
            Log.e("AppLog", cardsList.toString())
        } catch (e: Exception) {
            Log.e("AppLog", "No Data Written in CardManager.write")
            Log.e("AppLog", e.stackTraceToString())
        }
    }
}