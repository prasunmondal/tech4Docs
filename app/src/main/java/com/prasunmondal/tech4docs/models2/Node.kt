package com.prasunmondal.tech4docs.models2

import java.io.Serializable

open class Node: Serializable {
    lateinit var name: String
    lateinit var parentContainerNode: ContainerNode
    fun isDataNode(): Boolean {
        return false
    }

    fun isContainerNode(): Boolean {
        return false
    }
}