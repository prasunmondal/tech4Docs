package com.prasunmondal.tech4docs.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.R

class ViewCards : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_cards)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            goToAddCards()
        }
    }

    private fun goToAddCards() {
        val myIntent = Intent(this, ActivityAddCards::class.java)
        this.startActivity(myIntent)
    }
}