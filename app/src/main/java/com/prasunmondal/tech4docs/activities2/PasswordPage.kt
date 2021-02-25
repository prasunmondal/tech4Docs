package com.prasunmondal.tech4docs.activities2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.activities.ListRecordTypes
import com.prasunmondal.tech4docs.models.InvalidPasswordException
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.utils.Applog
import java.io.Serializable

class Test2 : Serializable {
    var l = 90;

    override fun toString(): String {
        return "l: $l"
    }
}

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
        passwordField = findViewById(R.id.configure_recordType_editText_addQues)
        submitButton = findViewById(R.id.password_page_btn_submit)

        val doesVaultExist = Vault.doesAnyVaultExist(this)
        Applog.info("doesVaultExist", "$doesVaultExist", Throwable())

        actionMode = if (doesVaultExist)
            ActionMode.OPEN_VAULT
        else
            ActionMode.CREATE_VAULT
        Applog.info("actionMode", "$actionMode", Throwable())

        customizeUIOnCreate()
//        Constants.PASSWORD = getInputPassword()

        /*
             -- dev
         */
//        passwordField.setText("Vault1")
//        createANewVault()

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
        if(getInputPassword().isEmpty()) {
            Toast.makeText(this, "Please Enter a Password!", Toast.LENGTH_SHORT).show()
            return
        }
        when (actionMode) {
            ActionMode.CREATE_VAULT ->
                createANewVault()
            ActionMode.OPEN_VAULT ->
                loadAVault(getInputPassword())
        }
    }

    private fun loadAVault(password: String) {
        try {
            Vault.load(this, password)
            goToCreateRecordTypePage()
        } catch (e: InvalidPasswordException) {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createANewVault() {
        var password = getInputPassword()
        Applog.info("password", password, Throwable())
        if (Vault.isValidCreationPassword(password)) {
            Vault.password = password
            Vault.create(this)
            Toast.makeText(this, "New Vault Created!", Toast.LENGTH_SHORT).show()
            goToCreateRecordTypePage()
        } else {
            Toast.makeText(
                    this,
                    "Vault Not Created!\n" +
                            "Password should be more than ${Constants.passwordLength} characters",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getInputPassword(): String {
        return passwordField.text.toString()
    }

    fun goToCreateRecordTypePage() {
        Vault.password = getInputPassword()
        val myIntent = Intent(this, ListRecordTypes::class.java)
        this.startActivity(myIntent)
        finish()
    }
}