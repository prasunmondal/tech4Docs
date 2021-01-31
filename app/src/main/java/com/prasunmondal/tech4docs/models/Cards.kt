package com.prasunmondal.tech4docs.models

import com.prasunmondal.tech4docs.enums.CardType
import com.prasunmondal.tech4docs.enums.CardsProvider

class Cards {
    var cardType: CardType
    var cardsProvider: CardsProvider
    var cardNumber: Int
    var cardHolderName: String
    var expiryDate: Int
    var expiryMonth: Int
    var CVV: Int
    var pin: Int
    var linkedBank: String
    var name: String


    constructor(
        number: Int,
        expiryDate: Int,
        expiryMonth: Int,
        CVV: Int,
        pin: Int,
        name: String,
        cardType: CardType,
        cardsProvider: CardsProvider,
        linkedBank: String,
        cardHolderName: String
    ) {
        this.cardNumber = number
        this.expiryDate = expiryDate
        this.expiryMonth = expiryMonth
        this.CVV = CVV
        this.pin = pin
        this.name = name
        this.cardType = cardType
        this.cardsProvider = cardsProvider
        this.linkedBank = linkedBank
        this.cardHolderName = cardHolderName
    }
}