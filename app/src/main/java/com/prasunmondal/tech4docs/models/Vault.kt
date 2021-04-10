package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.NoVaultExistsException
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import com.prasunmondal.tech4docs.Exceptions.VaultNotLoaded
import com.prasunmondal.tech4docs.utils.Applog
import com.prasunmondal.tech4docs.utils.DEncryption
import com.prasunmondal.tech4docs.utils.FileIO
import java.io.FileNotFoundException
import java.io.Serializable
import javax.crypto.BadPaddingException

class Vault : Serializable {
    var documents: ArrayList<Document>

    private constructor(context: Context, password: String) {
        this.documents = mutableListOf<Document>() as ArrayList<Document>
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
            if(!doesExists(context))
                throw NoVaultExistsException()
            if(!verifyPassword(context, password))
                throw InvalidPasswordException()

            instance = readFromFile(context, password)
            return instance!!
        }

        fun verifyPassword(context: Context, password: String): Boolean {
            Applog.info("password", password, Throwable())
            return try {
                readFromFile(context, password)
                Applog.info("result", true, Throwable())
                true
            } catch (e: InvalidPasswordException) {
                Applog.info("result", false, Throwable())
                false
            }
        }

        fun doesExists(context: Context): Boolean {
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
            // read the vault from the file and decrypt it!
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
            // write the vault to a file after encrypting it!
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
