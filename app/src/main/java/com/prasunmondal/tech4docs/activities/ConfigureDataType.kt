package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.enums.AnswerType
import com.prasunmondal.tech4docs.enums.HideLevel
import com.prasunmondal.tech4docs.models.Question
import com.prasunmondal.tech4docs.xModels.AttributeMetadata
import com.prasunmondal.tech4docs.models.RecordType

class ConfigureDataType : AppCompatActivity() {

    lateinit var recordType: RecordType
    lateinit var questionInput: EditText
    lateinit var answerType: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_data_type)
        initiallizeUIElements()
        fetchReceivedData()
    }

    private fun fetchReceivedData() {
        val bundle = intent.extras
        assert(bundle != null)
        recordType = bundle!!.getSerializable("dataTypeToConfigure") as RecordType
    }

    private fun displayLines() {
        var layout = findViewById<LinearLayout>(R.id.create_data_type_dataRowsContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        this.recordType.questions.forEach { c ->
            addLineInUI(layout, c.question, "")
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addLineInUI(layout: LinearLayout, title: String, value: String) {
        var textInputLayout = TextInputLayout(this)

        textInputLayout.hint = title
        textInputLayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_OUTLINE
        textInputLayout.boxStrokeColor = R.color.black
        textInputLayout.setBoxCornerRadii(5F, 5F, 5F, 5F);
        textInputLayout.setPadding(0, 40, 0, 10)

        var edittext = TextInputEditText(this)
        edittext.setText(value)
        edittext.setBackgroundColor(R.color.black)
        edittext.setBackgroundResource(R.color.white)
        edittext.setPadding(20, 40, 20, 10)

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

        displayLines()
    }

    fun initiallizeUIElements() {
        questionInput = findViewById(R.id.configure_recordType_editText_addQues)
        answerType = findViewById(R.id.configure_recordType_editText_addQuesType)
    }
}