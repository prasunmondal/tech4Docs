package com.prasunmondal.tech4docs.models

import android.content.Context
import android.widget.Toast
import com.prasunmondal.tech4docs.Exceptions.NoVaultException
import com.prasunmondal.tech4docs.Exceptions.VaultNotLoaded
import com.prasunmondal.tech4docs.Exceptions.VaultVerificationError
import com.prasunmondal.tech4docs.IOObjectToFile
import com.prasunmondal.tech4docs.Constants
import com.prasunmondal.tech4docs.Exceptions.PasswordComplexityNotMet
import java.io.Serializable

class Vault: Serializable {
    var recordTypes: ArrayList<RecordType>
    private var password: String = ""

    private constructor(context: Context, password: String) {
        this.password = password
        this.recordTypes = mutableListOf<RecordType>() as ArrayList<RecordType>

        write(context)
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
                return instance!!
            }
            throw PasswordComplexityNotMet()
        }

        fun load(context: Context, password: String): Vault {
            if(instance == null)
                instance = read(context)

            if(instance == null)
                throw NoVaultException()
            else { // Vault Exist
                if(verifyVault(password))
                    return instance!!
                else
                    throw VaultVerificationError()
            }
        }

        private fun verifyVault(password: String): Boolean {
            // TODO: implement logic to verify the correctness of password
            return true
        }

        fun doesAnyVaultExist(): Boolean {
            // TODO: implement logic to check if any vault exist in memory
            return false
        }

        private fun read(context: Context): Vault? {
            instance = IOObjectToFile().
            ReadObjectFromFile(context, Constants.FILENAME_PHONEBOOK) as Vault
            return instance
        }

        private fun write(context: Context) {
            IOObjectToFile().WriteObjectToFile(context, Constants.FILENAME_PHONEBOOK, instance)
        }

        private fun isValidCreationPassword(password: String): Boolean {
            return password.length >= Constants.passwordLength
        }
    }
}