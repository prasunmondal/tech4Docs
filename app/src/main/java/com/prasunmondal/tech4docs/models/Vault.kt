package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.Exceptions.NoVaultException
import com.prasunmondal.tech4docs.Exceptions.VaultNotLoaded
import com.prasunmondal.tech4docs.Exceptions.VaultVerificationError
import com.prasunmondal.tech4docs.utils.FileIO
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.DEncryption
import java.io.*
import java.lang.Exception

class Vault: Serializable {
    var recordTypes: ArrayList<RecordType>
    private var password: String = ""

    private constructor(context: Context, password: String) {
        this.password = password
        this.recordTypes = mutableListOf<RecordType>() as ArrayList<RecordType>
    }

    companion object {
        private  var instance: Vault? = null
        fun get(context: Context): Vault {
            if(instance == null)
                throw VaultNotLoaded()
            return instance!!
        }

        fun create(context: Context, password: String): Vault {
            if(isValidCreationPassword(password)) {
                if(instance == null)
                    instance = Vault(context, password)
                write(context, Constants.PASSWORD)
                return instance!!
            }
            throw PasswordComplexityNotMet()
        }

        fun load(context: Context, password: String): Vault {
            if(instance == null)
                instance = read(context, password)

            if(instance == null)
                throw NoVaultException()
            else { // Vault Exist
                if(verifyVault(context, password))
                    return instance!!
                else
                    throw VaultVerificationError()
            }
        }

        private fun verifyVault(context: Context, password: String): Boolean {
            // TODO: implement logic to verify the correctness of password
            // decrypt the vault and store in a different file
            // read the file and typecast to vault
            // if successful - true
            // else - false

            var vaultString = ""
//            var key = DEncryption.generateKey("testKey")
//            DEncryption.encodeFile(key, DEncryption.serialize(Vault.get(context)))

//            DEncryption.serialize(Vault.get(context))
//            DEncryption.decrypt(vaultString, password)


            return true
        }

        fun doesAnyVaultExist(context: Context): Boolean {
            try {
                FileIO().ReadBytesFromFile(context, Constants.FILENAME_PHONEBOOK)
                Applog.info("return",true, Throwable())
                return true
            } catch (e: Exception) {
            }
            Applog.info("return", false, Throwable())
            return false
        }

        private fun read(context: Context, key: String): Vault? {
            var beforeDecoding = FileIO().ReadBytesFromFile(context, Constants.FILENAME_PHONEBOOK)
            var removePadding = DEncryption.removePadding(beforeDecoding)
            var afterDecoding = DEncryption.decodeFile(removePadding, key)!!
            var vault = DEncryption.byteArrayToObject(afterDecoding) as Vault
            return vault
        }

        fun write(context: Context, key: String) {
            Applog.info("Start file write", Throwable())
            var beforeEncoding: ByteArray = DEncryption.objectToByteArray(instance)
            var afterEncoding: ByteArray = DEncryption.encodeFile(beforeEncoding, key)!!
            var padded = DEncryption.addPadding(afterEncoding)
            FileIO().WriteBytesToFile(context, Constants.FILENAME_PHONEBOOK, padded)
        }

        private fun isValidCreationPassword(password: String): Boolean {
            return password.length >= Constants.passwordLength
        }
    }
}