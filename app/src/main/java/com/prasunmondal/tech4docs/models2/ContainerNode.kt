package com.prasunmondal.tech4docs.models2

class ContainerNode: Node {
    lateinit var containerNodes: ArrayList<ContainerNode>
    lateinit var dataNodes:  ArrayList<DataNode>

    constructor(name: String, parentContainerNode: ContainerNode?) {
        this.name = name
        containerNodes = arrayListOf()
        dataNodes = arrayListOf()
        if (parentContainerNode != null) {
            this.parentContainerNode = parentContainerNode
        } else {
            this.parentContainerNode = this
        }
    }

    fun createDataNode(name: String) {
        dataNodes.add(DataNode(name))
    }
}