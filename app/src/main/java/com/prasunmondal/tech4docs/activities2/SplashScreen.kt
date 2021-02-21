package com.prasunmondal.tech4docs.activities2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.prasunmondal.tech4docs.R

class SplashScreen : AppCompatActivity() {

    private val splashScreeDelayInMs: Long = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            goToPasswordPage()
        },  splashScreeDelayInMs)
    }

    private fun goToPasswordPage() {
        val myIntent = Intent(this, PasswordPage::class.java)
        this.startActivity(myIntent)
        finish()
    }
}