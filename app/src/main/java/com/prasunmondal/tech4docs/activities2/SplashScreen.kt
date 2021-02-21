package com.prasunmondal.tech4docs.activities2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.activities.DisplayRecordTypeDetails
import com.prasunmondal.tech4docs.activities.PasswordPage

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            goToPasswordPage()
        },  R.string.splash_show_time_ms.toLong())
    }

    private fun goToPasswordPage() {
        val myIntent = Intent(this, PasswordPage::class.java)
        this.startActivity(myIntent)
        finish()
    }
}