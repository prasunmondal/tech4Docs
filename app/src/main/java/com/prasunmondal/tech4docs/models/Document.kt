package com.prasunmondal.tech4docs.models

import android.content.Context
import com.prasunmondal.tech4docs.models2.Question
import java.io.Serializable

class Document : Serializable {
    var id: String
    var name: String
    lateinit var questions: ArrayList<Question>
    var records: ArrayList<Data>

    constructor(name: String) {
        this.id = generateID()
        this.name = name
        this.records = arrayListOf(Data("31", Answer("dkjfbnlsjv")))
    }

    private fun generateID(): String {
        return "skbfkvb"
    }

    //    var attributes = mutableListOf<AttributeMetadata>()
    companion object {
        fun getRecordTypeById(context: Context, id: String): Document {
            Vault.get(context).documents.forEach { c ->
                if (id == c.name)
                    return c
            }
            return null!!
        }
    }
}