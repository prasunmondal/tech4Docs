package com.prasunmondal.tech4docs.models2

import android.content.Context
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.operations.DataFile

class ContainerNode: Node {
    var containerNodes: ArrayList<ContainerNode>
    var dataNodes:  ArrayList<DataNode>
//    var t: Int = 0

    override fun toString(): String {
        return "Name: $name containerNodes: $containerNodes, dataNodes: $dataNodes\n"
    }

    constructor(context: Context, name: String, parentContainerNode: ContainerNode?) {
        this.name = name
        containerNodes = arrayListOf()
        dataNodes = arrayListOf()
        if (parentContainerNode != null) {
            this.parentContainerNode = parentContainerNode
            this.parentContainerNode.containerNodes.add(this)
        } else {
            this.parentContainerNode = this
        }

        DataFile.write(context, Vault.password)
    }

//    fun createDataNode(name: String) {
//        dataNodes.add(DataNode(name))
//    }

    override fun isContainerNode(): Boolean {
        return true
    }
}