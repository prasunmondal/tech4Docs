package com.prasunmondal.tech4docs.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prasunmondal.tech4docs.R

class PasswordPage : AppCompatActivity() {

    enum class ActionMode {
        SET_PASSWORD,
        ASK_PASSWORD
    }
    lateinit var actionMode: ActionMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_page)

        actionMode = if (Vault.doesAnyVaultExist())
            ActionMode.ASK_PASSWORD
        else
            ActionMode.SET_PASSWORD
    }

    fun askForPassword() {

    }

    fun setPassword() {

    }


}