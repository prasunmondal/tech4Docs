package com.prasunmondal.tech4docs.models

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.activities.ListRecordTypes

class PasswordPage : AppCompatActivity() {

    enum class ActionMode {
        CREATE_VAULT,
        OPEN_VAULT
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
            ActionMode.OPEN_VAULT
        else
            ActionMode.CREATE_VAULT
        Toast.makeText(this, "Page Mode: $actionMode", Toast.LENGTH_SHORT).show()

        customizeUIOnCreate()
    }

    fun customizeUIOnCreate() {
        when (actionMode) {
            ActionMode.CREATE_VAULT ->
                submitButton.text = "CREATE VAULT"
            ActionMode.OPEN_VAULT ->
                submitButton.text = "OPEN VAULT"
        }

    }

    fun onClickPasswordSubmitButton(view: View) {
        when (actionMode) {
            ActionMode.CREATE_VAULT ->
                createANewVault()
            ActionMode.OPEN_VAULT ->
                loadAVault()
        }
        goToCreateRecordTypePage()
    }

    private fun loadAVault() {
        Vault.load(this, getInputPassword())
    }

    private fun createANewVault() {
        try {
            Vault.create(this, getInputPassword())
            Toast.makeText(this, "New Vault Created!", Toast.LENGTH_SHORT).show()
        } catch (e: PasswordComplexityNotMet) {
            Toast.makeText(
                this,
                "Vault Not Created!\n" +
                        "Password should be more than ${Constants.passwordLength} characters",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getInputPassword(): String {
        return passwordField.text.toString()
    }

    fun goToCreateRecordTypePage() {
        val myIntent = Intent(this, ListRecordTypes::class.java)
        this.startActivity(myIntent)
        finish()
    }
}