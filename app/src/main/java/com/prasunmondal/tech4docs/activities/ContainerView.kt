package com.prasunmondal.tech4docs.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.tech4docs.R
import com.prasunmondal.tech4docs.models.Document
import com.prasunmondal.tech4docs.models.Vault
import com.prasunmondal.tech4docs.models2.ContainerNode
import com.prasunmondal.tech4docs.models2.DataNode
import com.prasunmondal.tech4docs.models2.Node
import com.prasunmondal.tech4docs.models2.SessionDataManager
import com.prasunmondal.tech4docs.utils.Applog


class ContainerView : AppCompatActivity() {
    private lateinit var datatypeNameInput: EditText
    private var currentLevel = Vault.instance!!.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_view)
        initiallizeUI()
        displayLines()
    }

    private fun displayLines() {
        val layout = findViewById<LinearLayout>(R.id.create_record_type_recordTypeContainer)
        layout.setPadding(10, 10, 10, 10)
        layout.removeAllViews()

        Applog.info("parentNode: " + currentLevel.name ,Throwable())

        currentLevel.containerNodes.forEach { c ->
            addLineInUI(layout, c)
        }

        currentLevel.dataNodes.forEach { c ->
            addLineInUI(layout, c)
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun addLineInUI(layout: LinearLayout, node: Node) {

        val horizontalLayout = LinearLayout(this)
        horizontalLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        horizontalLayout.orientation = LinearLayout.HORIZONTAL
        horizontalLayout.setBackgroundColor(R.color.black)
        horizontalLayout.setPadding(10,30,10,30)
        horizontalLayout.setOnClickListener {
            onClickNode(node)
        }


        val dataTypeName = TextView(this)
        dataTypeName.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        dataTypeName.text = node.name
//        dataTypeName.setPadding(10, 5, 20, 5)
        dataTypeName.setBackgroundColor(R.color.black)
        dataTypeName.textSize = 20F
//        dataTypeName.setBackgroundResource(R.color.white)
        dataTypeName.setTextColor(resources.getColor(R.color.white))
        horizontalLayout.addView(dataTypeName)

        if(node.isContainerNode()) {
            val editDataType = TextView(this)
            editDataType.text = "➤"
            editDataType.setPadding(20, 0, 0, 0)
            editDataType.setTextColor(resources.getColor(R.color.white))
//        editDataType.setBackgroundColor(R.color.black)
//        editDataType.setBackgroundResource(R.color.white)
//            editDataType.setOnClickListener {
//                onClickEditDataType(node)
//            }
            horizontalLayout.addView(editDataType)
        }
//
//        val addDataTypeRecordsView = TextView(this)
//        addDataTypeRecordsView.text = "create"
//        addDataTypeRecordsView.setPadding(20, 40, 20, 10)
//        addDataTypeRecordsView.setBackgroundColor(R.color.black)
//        addDataTypeRecordsView.setBackgroundResource(R.color.white)
//        addDataTypeRecordsView.setOnClickListener {
//            onClickAddDataTypeRecordsView(recordType)
//        }



//        horizontalLayout.addView(addDataTypeRecordsView)
        layout.addView(horizontalLayout)
    }

//    @SuppressLint("SetTextI18n")
//    private fun addMyCreditTextBox(
//            serialNo: Int,
//            transaction: TransactionRecord,
//            showConstraint: (TransactionRecord) -> Boolean,
//            tabType: String
//    ): Double? {
//        if (!showConstraint.invoke(transaction))
//            return null
//
//        var textColor = R.color.white
//
////        if (tabType == Tabs.Singleton.instance.Tab_MySpent)
////            textColor = R.color.white
//
////        if (!isDebitTransaction(transaction))
////            textColor = R.color.notInvolvedTextColorRow1
//
//        val linearLayout = findViewById<LinearLayout>(R.id.cardContainers)
//
//        val llv0 = LinearLayout(applicationContext)
//        llv0.orientation = LinearLayout.VERTICAL
//        llv0.setPadding(20, 10, 20, 10)
//
//        val llv1 = LinearLayout(applicationContext)
//        llv1.orientation = LinearLayout.VERTICAL
//        llv1.setBackgroundResource(R.drawable.rounded_layout_red)
//
//        llv0.addView(llv1)
//
//
//        val cardView = CardView(this)
//        linearLayout.addView(cardView)
//
//        val serialNoField = TextView(this)
//        serialNoField.textSize = 15F
//        serialNoField.setTextColor(resources.getColor(textColor))
//        serialNoField.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        serialNoField.setPadding(20, 0, 0, 0)
//
//        val price1 = TextView(this)
//        price1.width = 407
//        price1.textSize = 15F
//        price1.gravity = Gravity.END
//        price1.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        price1.setPadding(20, 0, 20, 0)
//
//        val itemNameField = TextView(this)
//        itemNameField.width = (getScreenWidth(AppContext.instance.initialContext) - (15 + 307 + 50))
//        itemNameField.textSize = 15F
//        itemNameField.setTextColor(resources.getColor(textColor))
//        itemNameField.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        itemNameField.setPadding(20, 0, 20, 0)
//
//        val price2 = TextView(this)
//        price2.textSize = 12F
//        price2.width = 300
//        price2.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        price2.gravity = Gravity.END
//        price2.alpha = 0.85F
//        price2.setPadding(0, 0, 0, 0)
//
//        val sharedBy = TextView(this)
//        sharedBy.textSize = 12F
//        sharedBy.width = (getScreenWidth(AppContext.instance.initialContext) - (200 + 50))
//        sharedBy.alpha = 0.6F
//        sharedBy.setTextColor(resources.getColor(R.color.textColorCreator))
//        sharedBy.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        sharedBy.setPadding(20, 10, 50, 0)
//
//        val recordOriginDetailsField = TextView(this)
//        recordOriginDetailsField.textSize = 12F
//        recordOriginDetailsField.setTextColor(resources.getColor(R.color.notInvolvedTextColorRow1))
//        recordOriginDetailsField.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        recordOriginDetailsField.setPadding(20, 10, 0, 20)
//
//
//
//        serialNoField.text = "$serialNo."
//        itemNameField.text = transaction.item
//        showPrices_textNColor(price1, transaction, price1_getText(tabType, transaction))
//        showPrices_textNColor(price2, transaction, price2_getText(tabType, transaction))
//        recordOriginDetailsField.text =
//                "+ " + get1word(transaction.name) + " . " + transaction.createTime.split(" ")[0]
//        if (current_cardType == cardType_minimal)
//            sharedBy.text = ""
//        else if (current_cardType == cardType_relevant)
//            sharedBy.text = get1word(transaction.sharedBy)
//        else
//            sharedBy.text = transaction.time + " . " + get1word(transaction.sharedBy)
//
//
//        val llh1 = LinearLayout(applicationContext)
//        val llh2 = LinearLayout(applicationContext)
//        val llh3 = LinearLayout(applicationContext)
//        llh1.orientation = LinearLayout.HORIZONTAL
//        llh2.orientation = LinearLayout.HORIZONTAL
//        llh3.orientation = LinearLayout.HORIZONTAL
//
//        llh1.addView(serialNoField)
//        llh1.addView(itemNameField)
//        llh1.addView(price1)
//
//        llh2.addView(sharedBy)
//        llh2.addView(price2)
//
//        llh3.addView(recordOriginDetailsField)
//
//        llh3.gravity = Gravity.END
//        llh2.setPadding(20, 0, 20, 0)
//        llh3.setPadding(20, 0, 20, 0)
//
//        llv1.addView(llh1)
//        llv1.addView(llh2)
//        if (current_cardType == cardType_all)
//            llv1.addView(llh3)
//
//        llv0.setOnClickListener {
//            goToViewTransaction(transaction)
//        }
//        linearLayout.addView(llv0)
//
//        serialNoField.setTextColor(getColor_text1(tabType, transaction))
//        itemNameField.setTextColor(getColor_text1(tabType, transaction))
//
//        if (tabType == Tabs.Singleton.instance.Tab_MyTransaction)
//            return transaction.userDebit.toDouble() - transaction.userCredit.toDouble()
//        if (tabType == Tabs.Singleton.instance.Tab_MySpent)
//            return transaction.userCredit.toDouble()
//        if (tabType == Tabs.Singleton.instance.Tab_MyExpenses)
//            return transaction.userDebit.toDouble()
//        if (tabType == Tabs.Singleton.instance.Tab_showAll)
//            return transaction.price.toDouble()
//        return 0.0
//    }

    private fun onClickAddDataTypeRecordsView(document: Document) {
        Toast.makeText(this, "Go to view!", Toast.LENGTH_SHORT).show()
    }

    private fun onClickEditDataType(containerNode: Node) {
        goToConfigureActivity(containerNode)
    }

    private fun onClickNode(node: Node) {
        Applog.startMethod(Throwable())
        if(node.isContainerNode()) {
            currentLevel = node as ContainerNode
            displayLines()
        } else {
            goToDataView(node as DataNode)
        }
    }

    private fun initiallizeUI() {
        datatypeNameInput = findViewById(R.id.create_data_type_edittext_datatype_name)
    }

    fun onClickCreateContainer(view: View) {
        val name: String = datatypeNameInput.text.toString()
        createNewContainer(name)
    }

    fun onClickCreateDataNode(view: View) {
        val name: String = datatypeNameInput.text.toString()
        createNewDataNode(name)
    }

    private fun doesDataTypeExists(name: String): Boolean {
        currentLevel.containerNodes.forEach { t ->
            if(t.name.equals(name, ignoreCase = true)) {
                return true
            }
        }
        currentLevel.dataNodes.forEach { t ->
            if(t.name.equals(name, ignoreCase = true)) {
                return true
            }
        }
        return false
    }

    private fun createNewContainer(name: String) {
            if(!isValidData(name))
                return
            ContainerNode(this, name, currentLevel)
            displayLines()
            datatypeNameInput.setText("")
    }

    private fun createNewDataNode(name: String) {
        if(!isValidData(name))
            return
        DataNode(this, name, currentLevel)
        displayLines()
        datatypeNameInput.setText("")
    }

    private fun isValidData(name: String): Boolean {
        if(name.isEmpty()) {
            Toast.makeText(this, "Please Enter the Record Type Name", Toast.LENGTH_SHORT).show()
            return false
        }
        if(doesDataTypeExists(name)) {
            Toast.makeText(this, "Record Type Exists!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun goToDataView(dataNode: DataNode) {
        val myIntent = Intent(this, DataView::class.java)
        val bundle = Bundle()
        SessionDataManager.dataNode = dataNode
        bundle.putSerializable("dataNode", dataNode)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }

    fun onClickConfigureDataType(view: View) {

    }

    private fun goToConfigureActivity(document: Node) {
        val myIntent = Intent(this, CustomizeRecordTypes::class.java)
        val bundle = Bundle()
        bundle.putString("dataTypeToConfigure", document.name)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }

    override fun onBackPressed() {
        if(currentLevel == Vault.instance!!.root)
            super.onBackPressed()
        currentLevel = currentLevel.parentContainerNode
        displayLines()
    }
}