package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.IOObjectToFile
import com.prasunmondal.tech4docs.StringConstants
import java.io.Serializable

class DataVault: Serializable {
    var datatypes: ArrayList<DataTypeMetadata>

    private constructor(datatypes: ArrayList<DataTypeMetadata>) {
        this.datatypes = datatypes
    }

    private constructor(context: Context) {
        this.datatypes = read(context)
    }

    fun read(context: Context): ArrayList<DataTypeMetadata> {
        datatypes = IOObjectToFile().ReadObjectFromFile(
            context,
            StringConstants.FILENAME_PHONEBOOK
        ) as ArrayList<DataTypeMetadata>
        return datatypes
    }

    fun write(context: Context) {
        var ioObject = IOObjectToFile()
        ioObject.WriteObjectToFile(context, StringConstants.FILENAME_PHONEBOOK, datatypes)
    }

    companion object {
        private lateinit var instance: DataVault
        fun get(context: Context): DataVault {
            // singleton onbject
            if(instance == null)
                instance = DataVault(context)
            if(instance == null)
                instance = DataVault(arrayListOf())
            return instance
        }
    }
}