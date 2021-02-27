package com.prasunmondal.tech4docs.models

import android.content.Context
import java.io.Serializable

class RecordType : Serializable {
    var id: String
    var name: String
    lateinit var questions: ArrayList<Question>
    var records: ArrayList<QuesAns>

    constructor(name: String) {
        this.id = generateID()
        this.name = name
        this.records = arrayListOf(QuesAns("31", Answer("dkjfbnlsjv")))
    }

    private fun generateID(): String {
        return "skbfkvb"
    }

    //    var attributes = mutableListOf<AttributeMetadata>()
    companion object {
        fun getRecordTypeById(context: Context, id: String): RecordType {
            Vault.get(context).recordTypes.forEach { c ->
                if (id == c.name)
                    return c
            }
            return null!!
        }
    }
}