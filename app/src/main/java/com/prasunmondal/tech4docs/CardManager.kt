package com.prasunmondal.tech4docs

import com.prasunmondal.tech4docs.Models.Cards

class CardManager {

    object singleton {
        var instance = CardManager()
    }

    var cardsList: ArrayList<Cards> = arrayListOf()
}