package com.prasunmondal.tech4docs.models

class QuesAns {
    var questionID: String
    var answer: Answer

    constructor(questionID: String, answer: Answer) {
        this.questionID = questionID
        this.answer = answer
    }
}