package com.prasunmondal.tech4docs.models2

import android.content.Context
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.operations.DataFile

class ContainerNode: Node {
    lateinit var containerNodes: ArrayList<ContainerNode>
    lateinit var dataNodes:  ArrayList<DataNode>

    constructor(context: Context, name: String, parentContainerNode: ContainerNode?) {
        this.name = name
        containerNodes = arrayListOf()
        dataNodes = arrayListOf()
        if (parentContainerNode != null) {
            this.parentContainerNode = parentContainerNode
        } else {
            this.parentContainerNode = this
        }
        this.parentContainerNode.containerNodes.add(this)
        DataFile.write(context, Vault.password)
    }

    fun createDataNode(name: String) {
        dataNodes.add(DataNode(name))
    }
}