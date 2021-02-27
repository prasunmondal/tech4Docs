package com.prasunmondal.tech4docs.models

import java.io.Serializable

class Answer: Serializable {
    var answer: String

    constructor(answer: String) {
        this.answer = answer
    }
}