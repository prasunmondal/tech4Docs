package com.prasunmondal.tech4docs.models2

class ContainerNode {
    lateinit var name: String
    lateinit var containerNodes: ArrayList<ContainerNode>
    lateinit var parentContainerNode: ContainerNode
    lateinit var dataNodes:  ArrayList<DataNode>

    constructor(name: String, parentContainerNode: ContainerNode) {
        this.name = name
        containerNodes = arrayListOf()
        dataNodes = arrayListOf()
        this.parentContainerNode = parentContainerNode
    }

    fun createDataNode(name: String) {
        dataNodes.add(DataNode(name))
    }
}