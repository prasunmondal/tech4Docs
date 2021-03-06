package com.prasunmondal.tech4docs.models2

import java.io.Serializable

open class Node: Serializable {
    lateinit var name: String
    lateinit var parentContainerNode: ContainerNode

    override fun toString(): String {
        return "name: " + name + " - parentContainerNode: " + parentContainerNode
    }
    open fun isDataNode(): Boolean {
        return false
    }

    open fun isContainerNode(): Boolean {
        return false
    }
}