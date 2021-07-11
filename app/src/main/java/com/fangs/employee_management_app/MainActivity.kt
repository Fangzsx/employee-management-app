package com.fangs.employee_management_app

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddToRecord = findViewById<Button>(R.id.btn_add_to_record)

        btnAddToRecord.setOnClickListener {

            addRecord()

        }

        showListInRecyclerView()
    }

    private fun addRecord(){

        val name = findViewById<EditText>(R.id.et_main_name).text.toString()
        val email = findViewById<EditText>(R.id.et_main_email).text.toString()

        //open db
        val databaseHandler = DatabaseHandler(this)

        //check if et fields are empty, if not, add to db
        if(name.isNotEmpty() && email.isNotEmpty()){

            val status = databaseHandler.addEmployee(EmployeeModel(0,name,email))
            if(status > -1){
                Toast.makeText(applicationContext, "Employee added", Toast.LENGTH_SHORT).show()
                findViewById<EditText>(R.id.et_main_name).text.clear()
                findViewById<EditText>(R.id.et_main_email).text.clear()

            }
        } else{
            Toast.makeText(applicationContext, "Name and Email cannot be empty", Toast.LENGTH_LONG).show()
        }
    }

    //updating item info
    fun editRecord(employee : EmployeeModel){
        //TODO: update record
        val editDialog = Dialog(this, R.style.Theme_dialog)
        editDialog.setCancelable(false)
        editDialog.setContentView(R.layout.dialog_update)

        val tvCancel = editDialog.findViewById<TextView>(R.id.tv_cancel)
        val tvUpdate = editDialog.findViewById<TextView>(R.id.tv_update)

        val etName = editDialog.findViewById<EditText>(R.id.et_edit_name)
        val etEmail = editDialog.findViewById<EditText>(R.id.et_edit_email)

        etName.setText(employee.name)
        etEmail.setText(employee.email)

        tvUpdate.setOnClickListener {



        }


        tvCancel.setOnClickListener {
            editDialog.dismiss()
        }

        editDialog.show()

    }

    fun deleteRecord(employee : EmployeeModel){

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete Record")
        alertDialog.setMessage("Are you sure you want to delete this record?")
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert)

        alertDialog.setNegativeButton("NO") {dialogInterface, which ->
            dialogInterface.dismiss()
        }

        alertDialog.setPositiveButton("YES") {dialogInterface, which ->
            Toast.makeText(applicationContext, "deleted", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()

        }
        alertDialog.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

    }



    private fun showListInRecyclerView(){

        val rvRecords = findViewById<RecyclerView>(R.id.rv_records)
        val tvListIsEmpty = findViewById<TextView>(R.id.tv_list_is_empty)

        //check if list is empty
        if(getEmployeeList().size > 0){


            tvListIsEmpty.visibility = View.GONE



            rvRecords.layoutManager = LinearLayoutManager(this)
            val itemAdapter = EmployeeAdapter(this,getEmployeeList())
            rvRecords.adapter = itemAdapter

        } else {
            rvRecords.visibility = View.GONE
            tvListIsEmpty.visibility = View.VISIBLE
        }

    }

    private fun getEmployeeList() : ArrayList<EmployeeModel> = DatabaseHandler(this).viewEmployees()

}