package com.prasunmondal.tech4docs.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.prasunmondal.tech4docs.R

class MoneyMinded : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_minded)
    }

    fun goToBankDetails(view: View) {
        val myIntent = Intent(this, MoneyMinded::class.java)
        this.startActivity(myIntent)
    }

    fun goToCards(view: View) {
        val myIntent = Intent(this, ViewCards::class.java)
        this.startActivity(myIntent)
    }
}