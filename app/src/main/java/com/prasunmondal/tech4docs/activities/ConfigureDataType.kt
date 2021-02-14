package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.enums.AnswerType
import com.prasunmondal.tech4docs.enums.HideLevel
import com.prasunmondal.tech4docs.models.Question
import com.prasunmondal.tech4docs.models.RecordType
import com.prasunmondal.tech4docs.models.Vault

class ConfigureDataType : AppCompatActivity() {

    lateinit var recordType: RecordType
    lateinit var questionInput: EditText
    lateinit var answerType: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_data_type)
        initiallizeUIElements()
        populateAnswerTypes()
        fetchReceivedData()
    }

    fun populateAnswerTypes() {
        var displayList = arrayListOf<String>()
        for(c in AnswerType.values()) {
            displayList.add("kehbksb")
        }
        displayList.add("kehbksb")
        displayList.add("kehbksb")
        displayList.add("kehbksb")
        displayList.add("kehbksb")
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, displayList)
        answerType.threshold = 1
        answerType.setAdapter(adapter)
        answerType.setTextColor(Color.RED)
    }

    private fun fetchReceivedData() {
        val bundle = intent.extras
        assert(bundle != null)
        var dataTypeID = bundle!!.getString("dataTypeToConfigure")!!
        recordType = RecordType.getRecordTypeById(this, dataTypeID)
    }

    private fun displayLines() {
        var layout = findViewById<LinearLayout>(R.id.create_data_type_dataRowsContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        this.recordType.questions.forEach { c ->
            addLineInUI(layout, c.answerType.toString() + " (" + c.sequenceNo + ")", c.question)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addLineInUI(layout: LinearLayout, title: String, value: String) {
        var textInputLayout = TextInputLayout(this)

        textInputLayout.hint = title
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        textInputLayout.boxStrokeColor = R.color.black
        textInputLayout.boxBackgroundColor = R.color.black
        textInputLayout.boxStrokeWidth = 2
        textInputLayout.setBoxBackgroundColorResource(R.color.black)
        textInputLayout.setBoxCornerRadii(5F, 5F, 5F, 5F);
        textInputLayout.setPadding(0, 40, 0, 10)
        textInputLayout.setBackgroundResource(R.drawable.edit_text_layout)

        var edittext = TextInputEditText(this)
        edittext.setText(value)
//        edittext.setBackgroundColor(R.color.black)
//        edittext.setBackgroundResource(R.color.white)
        edittext.setPadding(20, 40, 20, 10)
//        edittext.setBackgroundColor(R.color.black)
        edittext.setBackgroundResource(R.color.black)
        edittext.setTextColor(R.color.white)
        edittext.setHintTextColor(R.color.white)


        // adding components
        textInputLayout.addView(edittext)
        layout.addView(textInputLayout)

        edittext.requestFocus()
    }

    fun createNewDataRow(view: View) {
        if(recordType.questions == null)
            recordType.questions = mutableListOf<Question>() as ArrayList<Question>
        var id = recordType.questions.size.toString()
        recordType.questions.add(Question(id, questionInput.text.toString(), AnswerType.valueOf("STRING"), HideLevel.NO_HIDE, true, id.toInt()))
        Vault.write(this)
        displayLines()
    }

    fun initiallizeUIElements() {
        questionInput = findViewById(R.id.configure_recordType_editText_addQues)
        answerType = findViewById(R.id.configure_recordType_editText_addAnswerType)
    }
}