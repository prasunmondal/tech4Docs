package com.prasunmondal.tech4docs.models2

class DataNode: Node {
    lateinit var data:  Map<Question, String>

    constructor(name: String) {
        this.name = name
        data = mutableMapOf()
    }

    constructor(name: String, data: Map<Question, String>) {
        this.name = name
        this.data = data
    }
}