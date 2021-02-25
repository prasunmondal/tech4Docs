package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.models.RecordType
import com.prasunmondal.tech4docs.models.Vault


class ListRecordTypes : AppCompatActivity() {
    private lateinit var datatypeNameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_record_type)
        initiallizeUI()
        displayLines()

        // dev
        datatypeNameInput.setText("datatype1");
        createDataType()

    }

    private fun displayLines() {
        val layout = findViewById<LinearLayout>(R.id.create_record_type_recordTypeContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        Vault.get(this).recordTypes.forEach { c ->
            addLineInUI(layout, c)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addLineInUI(layout: LinearLayout, recordType: RecordType) {

        val horizontalLayout = LinearLayout(this)
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL

        val dataTypeName = TextView(this)
        dataTypeName.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dataTypeName.text = recordType.name
        dataTypeName.setPadding(20, 40, 20, 10)
        dataTypeName.setBackgroundColor(R.color.black)
        dataTypeName.setBackgroundResource(R.color.white)
        dataTypeName.setOnClickListener {
            onClickDataTypeName(recordType)
        }

        val editDataType = TextView(this)
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
        datatypeNameInput = findViewById(R.id.create_data_type_edittext_datatype_name)
    }

    fun onClickCreateRecordType(view: View) {
        // TODO: Check if string is empty
        createDataType()
    }

    private fun createDataType() {
        val name: String = datatypeNameInput.text.toString()
        val dataCollection = Vault.get(this).recordTypes
        val dataTypeCreated = RecordType(name)
        dataTypeCreated.questions = ArrayList()
        dataCollection.add(dataTypeCreated)
        Vault.write(this, Vault.password)
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
        val myIntent = Intent(this, CustomizeRecordTypes::class.java)
        val bundle = Bundle()
        bundle.putString("dataTypeToConfigure", recordType.name)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }
}