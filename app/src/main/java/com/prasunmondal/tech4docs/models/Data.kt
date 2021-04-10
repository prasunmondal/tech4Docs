package com.prasunmondal.tech4docs.models

import java.io.Serializable

class Data: Serializable {
    var questionID: String
    var answer: Answer

    constructor(questionID: String, answer: Answer) {
        this.questionID = questionID
        this.answer = answer
    }
}