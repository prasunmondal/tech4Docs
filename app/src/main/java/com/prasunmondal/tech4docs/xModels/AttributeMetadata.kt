package com.prasunmondal.tech4docs.xModels

import java.io.Serializable

class AttributeMetadata : Serializable {
    lateinit var attributeName: String
    lateinit var attributeValue: String

    constructor(attributeName: String) {
        this.attributeName = attributeName
    }

    fun setValue(value: String) {
        this.attributeValue = value
    }
}