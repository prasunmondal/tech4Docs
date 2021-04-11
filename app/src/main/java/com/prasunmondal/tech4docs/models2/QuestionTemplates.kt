package com.prasunmondal.tech4docs.models2

import java.io.Serializable

class QuestionTemplates: Serializable {
    var questionTemplates:  MutableMap<String, MutableList<Question>> = mutableMapOf()

    constructor(name: String) {
        questionTemplates[name] = mutableListOf()
    }

    fun addQuestionToTemplate(name:String, question: Question) {
        this.questionTemplates[name]!!.add(question)
    }
}