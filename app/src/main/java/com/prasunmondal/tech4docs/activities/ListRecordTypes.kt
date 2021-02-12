package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.content.Intent
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
import com.prasunmondal.tech4docs.models.RecordType
import com.prasunmondal.tech4docs.models.Vault


class ListRecordTypes : AppCompatActivity() {

    lateinit var spinner: AutoCompleteTextView
    lateinit var datatypeNameInput: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_record_type)
        initiallizeUI()
        addDataTypeToSpinner()

        displayLines()
    }

    fun displayLines() {
        var layout = findViewById<LinearLayout>(R.id.create_record_type_recordTypeContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        Vault.get(this).recordTypes.forEach { c ->
            addLineInUI(layout, c.name, "")
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
        edittext.setText(title)
        edittext.setBackgroundColor(R.color.black)
        edittext.setBackgroundResource(R.color.white)
        edittext.setPadding(20, 40, 20, 10)

        // adding components
        textInputLayout.addView(edittext)
        layout.addView(textInputLayout)

        edittext.requestFocus()
    }

    private fun initiallizeUI() {
        spinner = findViewById(R.id.datatype_spinner)
        datatypeNameInput = findViewById(R.id.create_data_type_edittext_datatype_name)
    }

    fun addDataTypeToSpinner() {
        var displayList = arrayListOf<String>()
        for(item in Vault.get(this).recordTypes) {
            displayList.add(item.name)
        }
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, displayList)
        spinner.threshold = 1
        spinner.setAdapter(adapter)
        spinner.setTextColor(Color.RED)
    }

    fun onClickCreateDataType(view: View) {
        var name: String = datatypeNameInput.text.toString()
        var dataCollection = Vault.get(this).recordTypes
        var dataTypeCreated = RecordType(name)
        dataCollection.add(dataTypeCreated)
        displayLines()
//        goToConfigureActivity(dataTypeCreated)
    }

    fun onClickConfigureDataType(view: View) {

    }

    fun onClickCreateRecordType(view: View) {
        // TODO: Check if string is empty

    }

    fun goToConfigureActivity(recordType: RecordType) {
        val myIntent = Intent(this, ConfigureDataType::class.java)
        val bundle = Bundle()
        bundle.putSerializable("dataTypeToConfigure", recordType)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
        finish()
    }
}