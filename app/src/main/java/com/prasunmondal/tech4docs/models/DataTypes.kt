package com.prasunmondal.tech4docs.models

import java.io.Serializable

class DataTypes: Serializable {

    object singleton {
        var instance = DataTypes()
    }

    lateinit var datatypes: ArrayList<DataTypeMetadata>
}