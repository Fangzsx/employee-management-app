package com.fangs.employee_management_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //bind all view
    private val etName = findViewById<EditText>(R.id.et_main_name)
    private val etEmail = findViewById<EditText>(R.id.et_main_email)
    //button
    private val btnAddToRecord = findViewById<Button>(R.id.btn_add_to_record)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddToRecord.setOnClickListener {

            Toast.makeText(applicationContext, "btn clicked", Toast.LENGTH_SHORT).show()

        }


    }




}