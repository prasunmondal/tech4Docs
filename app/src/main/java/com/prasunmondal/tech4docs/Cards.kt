package com.prasunmondal.tech4docs

import com.prasunmondal.tech4docs.Enums.CardsProvider

class Cards {
    var name: String
    var number: Int
    var expiryDate: Int
    var expiryMonth: Int
    var CVV: Int
    var pin: Int
    var type: CardsProvider

    constructor(
        number: Int,
        expiryDate: Int,
        expiryMonth: Int,
        CVV: Int,
        type: CardsProvider,
        pin: Int,
        name: String
    ) {
        this.number = number
        this.expiryDate = expiryDate
        this.expiryMonth = expiryMonth
        this.CVV = CVV
        this.type = type
        this.pin = pin
        this.name = name
    }
}