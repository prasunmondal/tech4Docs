package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.prasunmondal.tech4docs.CardManager
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.enums.CardType
import com.prasunmondal.tech4docs.enums.CardsProvider
import com.prasunmondal.tech4docs.models.AttributeMetadata
import com.prasunmondal.tech4docs.models.Cards
import com.prasunmondal.tech4docs.models.DataTypeMetadata
import com.prasunmondal.tech4docs.models.DataTypes


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


    var card = DataTypeMetadata()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cards)
        createDataType()
        addAttributes()
    }

    private fun createDataType() {
        var attributeList = mutableListOf<AttributeMetadata>()
        attributeList.add(AttributeMetadata("Card Name"))
        attributeList.add(AttributeMetadata("Card Number"))
        attributeList.add(AttributeMetadata("CVV"))
        attributeList.add(AttributeMetadata("PIN"))
        card.attributes = attributeList
    }

    fun onClickSaveCardButton(view: View) {
        var card = Cards(getCardType(), getcardsProvider(), getcardNumber(), getcardHolderName(), getexpiryDate(), getexpiryMonth(), getCVV(), getpin(), getlinkedBank(), getName())
        CardManager.singleton.instance.cardsList.add(card)
        CardManager.singleton.instance.write(this)
    }

    @SuppressLint("ResourceAsColor")
    private fun addAttributes() {
        var layout = findViewById<LinearLayout>(R.id.ll1)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()
        layout.setBackgroundColor(R.color.white)
        layout.setBackgroundResource(R.color.white)

        this.card.attributes.forEach { c ->
            addBox(layout, c.attributeName, "")
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addBox(layout: LinearLayout, title: String, value: String) {
        var textInputLayout = TextInputLayout(this)

        textInputLayout.hint = title
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        textInputLayout.boxStrokeColor = R.color.black
        textInputLayout.setBoxCornerRadii(5F, 5F, 5F, 5F);
        textInputLayout.setPadding(0, 40,0,10)

        var edittext = TextInputEditText(this)
        edittext.setText(value)
        edittext.setBackgroundColor(R.color.black)
        edittext.setBackgroundResource(R.color.white)
        edittext.setPadding(20, 40,20,10)

        // adding components
        textInputLayout.addView(edittext)
        layout.addView(textInputLayout)
    }

    private fun getCardType(): CardType {
//        return CardType.valueOf(editCardsProvider.text.toString())
        return CardType.CREDIT
    }
    private fun getcardsProvider(): CardsProvider {
//        var provider = editCardsProvider.text.toString()
//        return CardsProvider.valueOf(provider)
        return CardsProvider.VISA
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