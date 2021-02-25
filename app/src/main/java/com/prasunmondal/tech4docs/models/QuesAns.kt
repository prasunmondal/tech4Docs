package com.prasunmondal.tech4docs.models

import java.io.Serializable

class QuesAns: Serializable {
    var questionID: String
    var answer: Answer

    constructor(questionID: String, answer: Answer) {
        this.questionID = questionID
        this.answer = answer
    }
}