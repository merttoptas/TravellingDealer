package com.merttoptas.travellingdealer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.merttoptas.travellingdealer.databinding.ActivityMainBinding
import com.merttoptas.travellingdealer.node.Node

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var node: Node? = null
    private var listDeleteNode = ArrayList<Node>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            addCustomerToListBtn.setOnClickListener {
                addCustomerToList()
            }

            sumOfNodesBtn.setOnClickListener {
                sumOfNodes()
            }

            printListBtn.setOnClickListener {
                printList()
            }

            printLengthOfListBtn.setOnClickListener {
                printLengthOfList()
            }

            deleteNodeBtn.setOnClickListener {
                listDeleteNode.shuffle()
                deleteNode(listDeleteNode[0].x, listDeleteNode[0].y)
            }

            clearListBtn.setOnClickListener {
                clearList()
            }

            clearHistoryBtn.setOnClickListener {
                clearHistory()
            }

            node = Node(3, 7)
            node?.path = 0.0

            listDeleteNode.add(Node(3, 7))
            listDeleteNode.add(Node(1, 4))
            listDeleteNode.add(Node(6, 5))
            listDeleteNode.add(Node(9, 2))

        }
    }

    @SuppressLint("SetTextI18n")
    private fun addCustomerToList() {
        node?.appendToEnd(1, 10)
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}\nAdd Customer To List\n\nCustomer(1, 10) added\n"

        node?.appendToEnd(1, 4)
        binding.historyOfListTxt.text = "${binding.historyOfListTxt.text}Customer(1, 4) added\n"

        node?.appendToEnd(2, 1)
        binding.historyOfListTxt.text = "${binding.historyOfListTxt.text}Customer(2, 1) added\n"

        node?.appendToEnd(5, 2)
        binding.historyOfListTxt.text = "${binding.historyOfListTxt.text}Customer(5, 2) added\n"

        node?.appendToEnd(6, 5)
        binding.historyOfListTxt.text = "${binding.historyOfListTxt.text}Customer(6, 5) added\n"

        node?.appendToEnd(8, 4)
        binding.historyOfListTxt.text = "${binding.historyOfListTxt.text}Customer(8, 4) added\n"

        node?.appendToEnd(8, 9)
        binding.historyOfListTxt.text = "${binding.historyOfListTxt.text}Customer(8 ,9) added\n"

        node?.appendToEnd(9, 2)
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}Customer(9, 2) added\n\n----------\n"
    }

    private fun sumOfNodes() {
        val sumOfNodes = node?.sumOfNodes()
        val stringSumOfNodes = String.format("%.5f", sumOfNodes)
        val stringTotalDistance = String.format("%.5f", sumOfNodes?.times(2))
        showAlertDialog("The Total Distance: $stringTotalDistance", "Sum Of Nodes")
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}\nSum Of Nodes\n\n$stringSumOfNodes\n\nThe Total Distance\n\n$stringTotalDistance\n\n----------\n"

    }

    private fun printList() {
        val printListString = node?.printNodes()
        showAlertDialog(printListString, "Print List")
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}\nPrint List\n\n$printListString\n\n----------\n"
    }


    private fun clearHistory() {
        binding.historyOfListTxt.text = ""
    }

    private fun clearList() {
        node?.following = null
        showAlertDialog("All node deleted.", "Clear List")
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}\nClear List\n\nAll node deleted\n\n----------\n"
    }

    private fun deleteNode(x: Int, y: Int) {
        val deleteResultMessage = node?.deleteNode(x, y)
        showAlertDialog(deleteResultMessage, "Delete Node")
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}\nDelete Node\n\n$deleteResultMessage\n\n----------\n"
    }

    private fun printLengthOfList() {
        val lengthOfList = node?.length()
        showAlertDialog(lengthOfList?.toString(), "Length Of List")
        binding.historyOfListTxt.text =
            "${binding.historyOfListTxt.text}\nPrint Length Of List\n\n$lengthOfList\n\n----------\n"
    }

    private fun showAlertDialog(message: String?, title: String?) {
        AlertDialog.Builder(this).setMessage(message).setTitle(title)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


}