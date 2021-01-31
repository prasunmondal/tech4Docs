package com.prasunmondal.tech4docs.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.prasunmondal.tech4docs.CardManager
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.enums.CardType
import com.prasunmondal.tech4docs.enums.CardsProvider
import com.prasunmondal.tech4docs.models.Cards

class ActivityAddCards : AppCompatActivity() {
    lateinit var editCardType: EditText
    lateinit var editCardsProvider: EditText
    lateinit var editCardNumber: EditText
    lateinit var editCardHolderName: EditText
    lateinit var editExpiryDate: EditText
    lateinit var editExpiryMonth: EditText
    lateinit var editCVV: EditText
    lateinit var editPIN: EditText
    lateinit var editLinkedBank: EditText
    lateinit var editName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cards)
        initiallizeUIElements()
    }

    fun onClickSaveCardButton(view: View) {
        var card = Cards(getCardType(), getcardsProvider(), getcardNumber(), getcardHolderName(),getexpiryDate(),getexpiryMonth(),getCVV(),getpin(),getlinkedBank(),getName())
        CardManager.singleton.instance.cardsList.add(card)
        CardManager.singleton.instance.write(this)
    }

    private fun initiallizeUIElements() {
        editCardType = findViewById(R.id.addCards_edit_cardType)
        editCardsProvider = findViewById(R.id.addCards_edit_cardsProvider)
        editCardNumber = findViewById(R.id.addCards_edit_cardNumber)
        editCardHolderName = findViewById(R.id.addCards_edit_cardHolderName)
        editExpiryDate = findViewById(R.id.addCards_edit_expiryDate)
        editExpiryMonth = findViewById(R.id.addCards_edit_expiryMonth)
        editCVV = findViewById(R.id.addCards_edit_CVV)
        editPIN = findViewById(R.id.addCards_edit_pin)
        editLinkedBank = findViewById(R.id.addCards_edit_linkedBank)
        editName = findViewById(R.id.addCards_edit_name)
    }

    private fun getCardType(): CardType {
        return CardType.valueOf(editCardsProvider.text.toString())
    }
    private fun getcardsProvider(): CardsProvider {
        var provider = editCardsProvider.text.toString()
        return CardsProvider.valueOf(provider)
    }
    private fun getcardNumber(): String {
        return editCardNumber.text.toString()
    }
    private fun getcardHolderName(): String {
        return editCardHolderName.text.toString()
    }
    private fun getexpiryDate(): Int {
        return editExpiryDate.text.toString().toInt()
    }
    private fun getexpiryMonth(): Int {
        return editExpiryMonth.text.toString().toInt()
    }
    private fun getCVV(): Int {
        return editCVV.text.toString().toInt()
    }
    private fun getpin(): Int {
        return editPIN.text.toString().toInt()
    }
    private fun getlinkedBank(): String {
        return editLinkedBank.text.toString()
    }
    private fun getName(): String {
        return editName.text.toString()
    }


}