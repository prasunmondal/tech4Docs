package com.prasunmondal.tech4docs.models2

import android.content.Context
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.operations.DataFile

class DataNode: Node {
    var data: MutableMap<Question, String> = mutableMapOf()

    override fun toString(): String {
        return "data: $data"
    }
    constructor(context: Context, name: String, parentContainerNode: ContainerNode) {
        this.name = name
        this.data = mutableMapOf()
        this.parentContainerNode = parentContainerNode
        parentContainerNode.dataNodes.add(this)
        DataFile.write(context, Vault.password)
    }

    override fun isDataNode(): Boolean {
        return true
    }
}