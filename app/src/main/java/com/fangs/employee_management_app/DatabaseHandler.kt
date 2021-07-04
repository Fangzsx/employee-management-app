package com.fangs.employee_management_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        //for sqlite params
        private const val DATABASE_NAME = "EmployeeDatabase"
        private const val DATABASE_VERSION = 1
        private const val DATABASE_TABLE_NAME = "EmployeeTable"

        //table rows
        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //create table
        val CREATE_NEW_TABLE = "CREATE TABLE $DATABASE_TABLE_NAME($KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT, $KEY_EMAIL TEXT)"
        db?.execSQL(CREATE_NEW_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE_NAME")
        onCreate(db)
    }


    fun addEmployee(employee : EmployeeModel) : Long {

        val db = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(KEY_NAME, employee.name)
        contentValue.put(KEY_EMAIL, employee.email)

        //status
        val status = db.insert(DATABASE_TABLE_NAME, null, contentValue)
        //close db
        db.close()
        return status

    }

}