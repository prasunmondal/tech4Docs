package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.models.Question
import com.prasunmondal.tech4docs.models.RecordType

class DisplayRecordTypeDetails : AppCompatActivity() {

    private lateinit var recordType: RecordType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_record_type)

        fetchReceivedData()
        displayRecords()
    }

    private fun fetchReceivedData() {
        val bundle = intent.extras
        assert(bundle != null)
        var dataTypeID = bundle!!.getString("dataTypeToConfigure")!!
        recordType = RecordType.getRecordTypeById(this, dataTypeID)
    }

    @SuppressLint("ResourceAsColor")
    private fun addLineInUI(layout: LinearLayout, quesAns: Question) {
        var horizontalLayout = LinearLayout(this)
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL

        var dataTypeName = TextView(this)
        dataTypeName.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dataTypeName.text = quesAns.answerType.toString()
        dataTypeName.setPadding(20, 40, 20, 10)
        dataTypeName.setBackgroundColor(R.color.black)
        dataTypeName.setBackgroundResource(R.color.white)
        dataTypeName.setOnClickListener {
//            onClickDataTypeName(recordType)
        }

        var editDataType = TextView(this)
        editDataType.text = quesAns.question
        editDataType.setPadding(20, 40, 20, 10)
        editDataType.setBackgroundColor(R.color.black)
        editDataType.setBackgroundResource(R.color.white)
        editDataType.setOnClickListener {
//            onClickEditDataType(recordType)
        }

        horizontalLayout.addView(dataTypeName)
        horizontalLayout.addView(editDataType)
        layout.addView(horizontalLayout)
    }

    private fun displayRecords() {
        var layout = findViewById<LinearLayout>(R.id.displayRecordTypeRecords_container)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        this.recordType.questions.forEach { c ->
            println("tttttttttttttttt")
            println(c);
            addLineInUI(layout, c)
        }
    }
}