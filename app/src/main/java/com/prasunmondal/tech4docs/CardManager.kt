package com.prasunmondal.tech4docs

import android.content.Context
import com.prasunmondal.tech4docs.models.Cards

class CardManager {
    object singleton {
        var instance = CardManager()
    }

    var cardsList: ArrayList<Cards> = arrayListOf()

    fun read(context: Context) {
        var ioObject = IOObjectToFile()
        cardsList = ioObject.ReadObjectFromFile(context, StringConstants.FILENAME_PHONEBOOK) as ArrayList<Cards>
    }

    fun write(context: Context) {
        var ioObject = IOObjectToFile()
        ioObject.WriteObjectToFile(context, StringConstants.FILENAME_PHONEBOOK, cardsList)
    }
}