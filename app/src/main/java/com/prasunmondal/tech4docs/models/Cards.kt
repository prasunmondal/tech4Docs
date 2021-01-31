package com.prasunmondal.tech4docs.models

import com.prasunmondal.tech4docs.enums.CardType
import com.prasunmondal.tech4docs.enums.CardsProvider

class Cards {
    var name: String
    var number: Int
    var expiryDate: Int
    var expiryMonth: Int
    var CVV: Int
    var pin: Int
    var cardsProvider: CardsProvider
    var cardType: CardType
    var linkedBank: String

    constructor(
            number: Int,
            expiryDate: Int,
            expiryMonth: Int,
            CVV: Int,
            pin: Int,
            name: String,
            cardType: CardType,
            cardsProvider: CardsProvider,
            linkedBank: String
    ) {
        this.number = number
        this.expiryDate = expiryDate
        this.expiryMonth = expiryMonth
        this.CVV = CVV
        this.pin = pin
        this.name = name
        this.cardType = cardType
        this.cardsProvider = cardsProvider
        this.linkedBank = linkedBank
    }
}