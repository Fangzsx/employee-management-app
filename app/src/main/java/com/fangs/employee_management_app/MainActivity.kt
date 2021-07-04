package com.fangs.employee_management_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddToRecord = findViewById<Button>(R.id.btn_add_to_record)

        btnAddToRecord.setOnClickListener {

            addRecord()

        }
    }

    fun addRecord(){

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
}