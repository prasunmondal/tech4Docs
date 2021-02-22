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
import com.prasunmondal.tech4docs.IOToFile
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.activities.ListRecordTypes
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.Bytes
import com.prasunmondal.tech4docs.utils.DEncryption

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


//        Applog.info("bytesTest:", Bytes.printBytes(beforeEncoding), Throwable())
//        bytesTest[0] = "1".toByte()
//        bytesTest[1] = "2".toByte()
//        bytesTest[2] = "3".toByte()
//        bytesTest[3] = "4".toByte()
//        bytesTest[4] = "5".toByte()
//        bytesTest[5] = "6".toByte()
//        bytesTest[6] = "7".toByte()

//        String key =
//        -84,-19,0,5,119,16
//        -84,-19,0,5,119,16
//        -84,-19,0,5,119,16
//        -84,-19,0,5,119,16

//        beforeEncoding =








        val key = "passwordpasswordpassword"
        val filename = "testetst"

        var beforeEncoding: ByteArray = byteArrayOf(1,1,1,1,1,1,1)
        Applog.info("Before Encoding (${beforeEncoding.size})", Bytes.printBytes(beforeEncoding), Throwable())
        var afterEncoding: ByteArray = DEncryption.encodeFile(key, beforeEncoding)!!
        Applog.info("After Encoding (${afterEncoding.size})", Bytes.printBytes(afterEncoding), Throwable())
        var padded = byteArrayOf(7,7,2,7,8,6,1,6,6,6,3,2,5) + afterEncoding
        Applog.info("After padding (${padded.size})", Bytes.printBytes(padded), Throwable())

        IOToFile().WriteBytesToFile(this, filename, padded)

        var beforeDecoding = IOToFile().ReadBytesFromFile(this, filename)
        Applog.info("Before Decoding (${beforeDecoding.size})", Bytes.printBytes(beforeDecoding), Throwable())
        var removePadding = DEncryption.removePaddingBytes(beforeDecoding)
        Applog.info("Remove Padding (${removePadding.size})", Bytes.printBytes(removePadding), Throwable())
        var afterDecoding = DEncryption.decodeFile(key, removePadding)!!
        Applog.info("After Decoding (${afterDecoding.size})", Bytes.printBytes(afterDecoding), Throwable())


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