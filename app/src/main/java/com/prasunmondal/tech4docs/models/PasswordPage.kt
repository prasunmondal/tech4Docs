package com.prasunmondal.tech4docs.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.R
import java.lang.Exception

class PasswordPage : AppCompatActivity() {

    enum class ActionMode {
        SET_PASSWORD,
        ASK_PASSWORD
    }
    lateinit var actionMode: ActionMode

    lateinit var passwordField: EditText
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_page)
        passwordField = findViewById(R.id.password_page_edittext_password)
        submitButton = findViewById(R.id.password_page_btn_submit)

        actionMode = if (Vault.doesAnyVaultExist())
            ActionMode.ASK_PASSWORD
        else
            ActionMode.SET_PASSWORD
        Toast.makeText(this, "Enter Password Mode: $actionMode", Toast.LENGTH_SHORT).show()

        customizeUIOnCreate()
    }

    fun customizeUIOnCreate() {
        when (actionMode) {
            ActionMode.SET_PASSWORD ->
                submitButton.text = "CREATE VAULT"
            ActionMode.ASK_PASSWORD ->
                submitButton.text = "OPEN VAULT"
        }

    }

    fun onClickPasswordSubmitButton(view: View) {
        if(actionMode == ActionMode.SET_PASSWORD) {
            createANewVault()
        } else {
            loadAVault()
        }
    }

    private fun loadAVault() {
        Vault.load(this, getInputPassword())
    }

    private fun createANewVault() {
        try {
            Vault.create(this, getInputPassword())
            Toast.makeText(this, "New Vault Created!", Toast.LENGTH_SHORT).show()
        } catch (e: PasswordComplexityNotMet) {
            Toast.makeText(this, "Vault Not Created!\n" +
                    "Password should be more than ${Constants.passwordLength} characters", Toast.LENGTH_SHORT).show()
        }
    }

    fun getInputPassword(): String {
        return passwordField.text.toString()
    }
}