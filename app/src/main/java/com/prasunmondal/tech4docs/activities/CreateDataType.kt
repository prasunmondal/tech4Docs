package com.prasunmondal.tech4docs.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.models.RecordType
import com.prasunmondal.tech4docs.models.Vault


class CreateDataType : AppCompatActivity() {

    lateinit var spinner: AutoCompleteTextView
    lateinit var datatypeNameInput: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_data_type)
        initiallizeUI()
        addDataTypeToSpinner()
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
        goToConfigureActivity(dataTypeCreated)
    }

    fun onClickConfigureDataType(view: View) {

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