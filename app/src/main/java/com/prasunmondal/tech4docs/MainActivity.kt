package com.prasunmondal.tech4docs

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToMoneyMinded(view: View) {
        val myIntent = Intent(this, MoneyMinded::class.java)
        this.startActivity(myIntent)
    }
}