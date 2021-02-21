package com.prasunmondal.tech4docs.activities2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.IOObjectToFile
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.activities.ListRecordTypes
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.Bytes

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

        var bytesTest: ByteArray = byteArrayOf(1,2,3,4,5,6,7,8)
        Applog.info("bytesTest:", Bytes.printBytes(bytesTest), Throwable())
//        bytesTest[0] = "1".toByte()
//        bytesTest[1] = "2".toByte()
//        bytesTest[2] = "3".toByte()
//        bytesTest[3] = "4".toByte()
//        bytesTest[4] = "5".toByte()
//        bytesTest[5] = "6".toByte()
//        bytesTest[6] = "7".toByte()

        IOObjectToFile().WriteBytesToFile(this, "test", bytesTest)
        var bytesTest2: ByteArray = IOObjectToFile().ReadBytesFromFile(this, "test")

        Applog.info("TestBytesWrite:", bytesTest, Throwable())
        Applog.info("TestBytesRead:", bytesTest2, Throwable())
        Applog.info("bytesTest2:", Bytes.printBytes(bytesTest2), Throwable())


//        val doesVaultExist = Vault.doesAnyVaultExist(this)
//        Applog.info("doesVaultExist","$doesVaultExist", Throwable())

//        actionMode = if (doesVaultExist)
//            ActionMode.OPEN_VAULT
//        else
//            ActionMode.CREATE_VAULT
//
//        Applog.info("actionMode","$actionMode", Throwable())
//        customizeUIOnCreate()

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
            when (actionMode) {
                ActionMode.CREATE_VAULT ->
                    createANewVault()
                ActionMode.OPEN_VAULT ->
                    loadAVault()
            }
    }

    private fun loadAVault() {
        Vault.load(this, getInputPassword())
        goToCreateRecordTypePage()
    }

    private fun createANewVault() {
        try {
            Vault.create(this, getInputPassword())
            Toast.makeText(this, "New Vault Created!", Toast.LENGTH_SHORT).show()
            goToCreateRecordTypePage()
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