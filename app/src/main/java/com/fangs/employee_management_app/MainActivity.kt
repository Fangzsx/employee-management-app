package com.fangs.employee_management_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddToRecord = findViewById<Button>(R.id.btn_add_to_record)

        btnAddToRecord.setOnClickListener {

            Toast.makeText(applicationContext, "btn clicked", Toast.LENGTH_SHORT).show()

        }
    }






}