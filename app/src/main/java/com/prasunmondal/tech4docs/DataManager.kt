package com.prasunmondal.tech4docs

import java.io.Serializable

class DataManager: Serializable {
    object singleton {
        var instance = DataManager()
    }
    var cards: CardManager

    constructor() {
        cards = CardManager.singleton.instance
    }
}