package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.models.AttributeMetadata
import com.prasunmondal.tech4docs.models.DataTypeMetadata

class ConfigureDataType : AppCompatActivity() {

    lateinit var card: DataTypeMetadata
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure_data_type)
        fetchReceivedData()
    }

    private fun fetchReceivedData() {
        val bundle = intent.extras
        assert(bundle != null)
        card = bundle!!.getSerializable("dataTypeToConfigure") as DataTypeMetadata
    }

    fun displayLines() {
        var layout = findViewById<LinearLayout>(R.id.create_data_type_dataRowsContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        this.card.attributes.forEach { c ->
            addLineInUI(layout, c.attributeName, "")
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
        card.attributes.add(AttributeMetadata("Name Of Data Line"))
        displayLines()
    }
}