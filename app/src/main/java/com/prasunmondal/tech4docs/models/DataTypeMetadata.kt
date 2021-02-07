package com.prasunmondal.tech4docs.models

import java.io.Serializable

class DataTypeMetadata: Serializable {
    var attributes = mutableListOf<AttributeMetadata>()
}