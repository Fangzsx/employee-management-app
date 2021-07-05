package com.fangs.employee_management_app

import android.opengl.Visibility
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
    fun updateRecord(employee : EmployeeModel){
        //TODO
        val databaseHandler = DatabaseHandler(this)
        databaseHandler.updateEmployee(employee)


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