package com.prasunmondal.tech4docs.models2

import com.prasunmondal.tech4docs.enums.AnswerType
import com.prasunmondal.tech4docs.enums.HideLevel
import java.io.Serializable

class Question: Serializable {
    var id: String
    var question: String
    var answerType: AnswerType
    var hideLevel: HideLevel
    var isActive: Boolean
    var sequenceNo: Int

    constructor(id: String, question: String, answerType: AnswerType, hideLevel: HideLevel, isActive: Boolean, sequenceNo: Int) {
        this.id = id
        this.question = question
        this.answerType = answerType
        this.hideLevel = hideLevel
        this.isActive = isActive
        this.sequenceNo = sequenceNo
    }
}