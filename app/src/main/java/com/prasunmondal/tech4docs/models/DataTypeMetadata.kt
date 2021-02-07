package com.prasunmondal.tech4docs.models

import java.io.Serializable

class DataTypeMetadata: Serializable {
    var name: String

    constructor(name: String) {
        this.name = name
    }

    var attributes = mutableListOf<AttributeMetadata>()
}