package com.prasunmondal.tech4docs.models

import com.prasunmondal.tech4docs.enums.CardType
import com.prasunmondal.tech4docs.enums.CardsProvider

class Cards {
    var cardType: CardType
    var cardsProvider: CardsProvider
    var cardNumber: String
    var cardHolderName: String
    var expiryDate: Int
    var expiryMonth: Int
    var CVV: Int
    var pin: Int
    var linkedBank: String
    var name: String

    constructor(
        cardType: CardType,
        cardsProvider: CardsProvider,
        cardNumber: String,
        cardHolderName: String,
        expiryDate: Int,
        expiryMonth: Int,
        CVV: Int,
        pin: Int,
        linkedBank: String,
        name: String
    ) {
        this.cardType = cardType
        this.cardsProvider = cardsProvider
        this.cardNumber = cardNumber
        this.cardHolderName = cardHolderName
        this.expiryDate = expiryDate
        this.expiryMonth = expiryMonth
        this.CVV = CVV
        this.pin = pin
        this.linkedBank = linkedBank
        this.name = name
    }
}