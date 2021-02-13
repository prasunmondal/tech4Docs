package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

        // dev
        datatypeNameInput.setText("datatype1");
        createDataType()

    }

    fun displayLines() {
        var layout = findViewById<LinearLayout>(R.id.create_record_type_recordTypeContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        Vault.get(this).recordTypes.forEach { c ->
            addLineInUI(layout, c)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addLineInUI(layout: LinearLayout, recordType: RecordType) {

        var horizontalLayout = LinearLayout(this)
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL

        var dataTypeName = TextView(this)
        dataTypeName.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dataTypeName.text = recordType.name
        dataTypeName.setPadding(20, 40, 20, 10)
        dataTypeName.setBackgroundColor(R.color.black)
        dataTypeName.setBackgroundResource(R.color.white)
        dataTypeName.setOnClickListener {
            onClickDataTypeName(recordType)
        }

        var editDataType = TextView(this)
        editDataType.text = "Edit2"
        editDataType.setPadding(20, 40, 20, 10)
        editDataType.setBackgroundColor(R.color.black)
        editDataType.setBackgroundResource(R.color.white)
        editDataType.setOnClickListener {
            onClickEditDataType(recordType)
        }

        horizontalLayout.addView(dataTypeName)
        horizontalLayout.addView(editDataType)
        layout.addView(horizontalLayout)
    }

    private fun onClickEditDataType(recordType: RecordType) {
        goToConfigureActivity(recordType)
    }

    private fun onClickDataTypeName(recordType: RecordType) {
        goToViewRecordTypeRecords(recordType)
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

    fun onClickCreateRecordType(view: View) {
        // TODO: Check if string is empty
        createDataType()
    }

    fun createDataType() {
        var name: String = datatypeNameInput.text.toString()
        var dataCollection = Vault.get(this).recordTypes
        var dataTypeCreated = RecordType(name)
        dataTypeCreated.questions = ArrayList()
        dataCollection.add(dataTypeCreated)
        displayLines()
        goToConfigureActivity(dataTypeCreated)
    }

    private fun goToViewRecordTypeRecords(recordType: RecordType) {
        val myIntent = Intent(this, DisplayRecordTypeDetails::class.java)
        val bundle = Bundle()
        bundle.putString("dataTypeToConfigure", recordType.name)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }

    fun onClickConfigureDataType(view: View) {

    }

    private fun goToConfigureActivity(recordType: RecordType) {
        val myIntent = Intent(this, ConfigureDataType::class.java)
        val bundle = Bundle()
        bundle.putString("dataTypeToConfigure", recordType.name)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }
}