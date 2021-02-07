package com.prasunmondal.tech4docs.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.R


class CreateDataType : AppCompatActivity() {

    lateinit var spinner: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_data_type)
        initiallizeUI()
        addDataTypeToSpinner()
    }

    private fun initiallizeUI() {
        spinner = findViewById(R.id.datatype_spinner)
    }

    fun onClickCreateDataType() {

    }

    fun addDataTypeToSpinner() {
//        val fruits = arrayOf("Apple", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear")
        val fruits = emptyArray<String>()
        val adapter = ArrayAdapter(this, android.R.layout.select_dialog_item, fruits)
        spinner.threshold = 1
        spinner.setAdapter(adapter)
        spinner.setTextColor(Color.RED)
    }
}