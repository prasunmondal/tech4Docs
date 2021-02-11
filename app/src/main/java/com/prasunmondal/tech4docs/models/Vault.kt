package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.IOObjectToFile
import com.prasunmondal.tech4docs.StringConstants
import java.io.Serializable

class Vault: Serializable {
    var recordTypes: ArrayList<RecordType>
    var password: String = ""

    private constructor(datatypes: ArrayList<RecordType>) {
        this.recordTypes = datatypes
    }

    private constructor(context: Context) {
        this.recordTypes = read(context)
    }

    fun read(context: Context): ArrayList<RecordType> {
        recordTypes = IOObjectToFile().ReadObjectFromFile(
            context,
            StringConstants.FILENAME_PHONEBOOK
        ) as ArrayList<RecordType>
        return recordTypes
    }

    fun write(context: Context) {
        var ioObject = IOObjectToFile()
        ioObject.WriteObjectToFile(context, StringConstants.FILENAME_PHONEBOOK, recordTypes)
    }

    companion object {
        private lateinit var instance: Vault
        fun get(context: Context): Vault {
            // singleton onbject
            if(instance == null)
                instance = Vault(context)
            if(instance == null)
                instance = Vault(arrayListOf())
            return instance
        }

        fun doesAnyVaultExist(): Boolean {
            return false;
        }
    }
}