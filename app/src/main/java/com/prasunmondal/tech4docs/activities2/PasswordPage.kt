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
import com.prasunmondal.tech4docs.activities.DisplayLevel
import com.prasunmondal.tech4docs.models.InvalidPasswordException
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.operations.DataFile
import com.prasunmondal.tech4docs.operations.PasswordManage
import com.prasunmondal.tech4docs.utils.Applog
import javax.crypto.IllegalBlockSizeException

class PasswordPage : AppCompatActivity() {

    enum class ActionMode {
        CREATE_VAULT,
        OPEN_VAULT
    }

    private lateinit var actionMode: ActionMode

    private lateinit var passwordField: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_page)
        passwordField = findViewById(R.id.configure_recordType_editText_addQues)
        submitButton = findViewById(R.id.password_page_btn_submit)

        val doesVaultExist = DataFile.exists(this)
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
        passwordField.setText("prasun")
        onClickPasswordSubmitButton(submitButton)

    }

    private fun customizeUIOnCreate() {
        when (actionMode) {
            ActionMode.CREATE_VAULT ->
                submitButton.text = "CREATE VAULT"
            ActionMode.OPEN_VAULT ->
                submitButton.text = "OPEN VAULT"
        }
    }

    fun onClickPasswordSubmitButton(view: View) {
        if (getInputPassword().isEmpty()) {
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
        Applog.startMethod(Throwable())
        Applog.info("password", password, Throwable())
        try {
            if(!PasswordManage.verifyPassword(this, password)) {
                handleInvalidPassword()
            } else {
                Vault.load(this, password)
                goToCreateRecordTypePage()
                Vault.password = password
                Applog.info("Loading Vault status", "SUCCESS", Throwable())
            }
        } catch (e: InvalidPasswordException) {
            Applog.info("Loading Vault status", "FAILED", Throwable())
            handleInvalidPassword()
        } catch (e: IllegalBlockSizeException) {
            Applog.info("Vault object outdated wrt recent Vault class.", Throwable())
            Toast.makeText(this, "Vault object outdated w.r.t recent Vault class.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleInvalidPassword() {
        Applog.info("Invalid Password", Throwable())
        Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
    }

    private fun createANewVault() {
        Applog.startMethod(Throwable())
        var password = getInputPassword()
        Applog.info("password", password, Throwable())
        if (PasswordManage.isValidCreationPassword(password)) {
            Vault.password = password
            Vault.create(this)
            Toast.makeText(this, "New Vault Created!", Toast.LENGTH_SHORT).show()
            Applog.info("Creating Vault status", "SUCCESS", Throwable())
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

    private fun goToCreateRecordTypePage() {
        Vault.password = getInputPassword()
        val myIntent = Intent(this, DisplayLevel::class.java)
        this.startActivity(myIntent)
        finish()
    }
}