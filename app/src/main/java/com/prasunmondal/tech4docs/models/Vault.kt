package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.NoVaultException
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.Exceptions.VaultNotLoaded
import com.prasunmondal.tech4docs.Exceptions.VaultVerificationError
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.DEncryption
import com.prasunmondal.tech4docs.utils.FileIO
import java.io.FileNotFoundException
import java.io.Serializable
import javax.crypto.BadPaddingException

class Vault : Serializable {
    var recordTypes: ArrayList<RecordType>


    private constructor(context: Context, password: String) {
        this.recordTypes = mutableListOf<RecordType>() as ArrayList<RecordType>
    }

    companion object {
        var password: String = ""
        private var instance: Vault? = null
        fun get(context: Context): Vault {
            if (instance == null)
                throw VaultNotLoaded()
            return instance!!
        }

        fun create(context: Context): Vault {
            var isCreationPasswordValid = isValidCreationPassword(password)
            Applog.info("isCreationPasswordValid", isCreationPasswordValid, Throwable())
            if (isCreationPasswordValid) {
                if (instance == null)
                    instance = Vault(context, password)
                write(context, password)
                Applog.info("Vault creation: Successful", Throwable())
                return instance!!
            }
            Applog.info("Vault creation: Failed", Throwable())
            throw PasswordComplexityNotMet()
        }

        fun load(context: Context, password: String): Vault {
            if (instance == null)
                instance = readFromFile(context, password)

            if (instance == null)
                throw NoVaultException()
            else { // Vault Exist
                if (verifyPassword(context, password)) {
                    return instance!!
                } else {
                    throw VaultVerificationError()
                }
            }
        }

        private fun verifyPassword(context: Context, password: String): Boolean {
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
            return try {
                FileIO().ReadBytesFromFile(context, Constants.FILENAME_PHONEBOOK)
                Applog.info("return", true, Throwable())
                true
            } catch (e: FileNotFoundException) {
                Applog.info("Vault Not Found", Throwable())
                Applog.info("return", false, Throwable())
                false
            }
        }

        private fun readFromFile(context: Context, key: String): Vault? {
            try {
                var beforeDecoding = FileIO().ReadBytesFromFile(context, Constants.FILENAME_PHONEBOOK)
                var removePadding = DEncryption.removePadding(beforeDecoding)
                var afterDecoding = DEncryption.decodeFile(removePadding, key)!!
                var vault = DEncryption.byteArrayToObject(afterDecoding) as Vault
                return vault
            } catch (e: BadPaddingException) {
                throw InvalidPasswordException()
            }
        }

        fun write(context: Context, key: String) {
            var beforeEncoding: ByteArray = DEncryption.objectToByteArray(instance)
            var afterEncoding: ByteArray = DEncryption.encodeFile(beforeEncoding, key)!!
            var padded = DEncryption.addPadding(afterEncoding)
            FileIO().WriteBytesToFile(context, Constants.FILENAME_PHONEBOOK, padded)
        }

        fun isValidCreationPassword(password: String): Boolean {
            Applog.info("password", password, Throwable())
            var result = password.length >= Constants.passwordLength
            Applog.info("result", result, Throwable())
            return result
        }
    }
}

class InvalidPasswordException : Throwable() {}
