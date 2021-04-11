package com.prasunmondal.tech4docs.models

import java.io.Serializable

class Answer: Serializable {
    var answer: String

    override fun toString(): String {
        return "Answer: " + answer
    }

    constructor(answer: String) {
        this.answer = answer
    }
}