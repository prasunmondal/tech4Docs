package com.prasunmondal.tech4docs.models2

import android.content.Context
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.operations.DataFile

class DataNode: Node {
    lateinit var data:  Map<Question, String>

    constructor(context: Context, name: String, parentContainerNode: ContainerNode) {
        this.name = name
        this.data = mutableMapOf()
        this.parentContainerNode = parentContainerNode
        DataFile.write(context, Vault.password)
    }

    constructor(name: String, data: Map<Question, String>) {
        this.name = name
        this.data = data
    }
}