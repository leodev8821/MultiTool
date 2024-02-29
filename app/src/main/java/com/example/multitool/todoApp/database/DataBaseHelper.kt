package com.example.multitool.todoApp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper (context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    // CONSTANTS
    // If you change the database schema, you must increment the database version.
    companion object{
        const val DATABASE_NAME = "ToDoApp.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Tasks.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Tasks.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun onDelete(db: SQLiteDatabase){
        db.execSQL(Tasks.SQL_DELETE_ENTRIES)
    }

    fun createTask(){
        val db = writableDatabase

        var values = ContentValues().apply {
            put("task", "Comprar leche")
            put("done", true)
        }

        var newRowId = db.insert("Task", null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        values = ContentValues().apply {
            put("task", "Limpiar coche")
            put("done", false)
        }

        newRowId = db.insert("Task", null, values)
        Log.i("DATABASE", "New record id: $newRowId")

    }

    @SuppressLint("Range")
    fun readTask(){

        val db = writableDatabase
        val cursor = db.query(
            "Task",                     // The Table to query
            arrayOf("_id","task", "done"),    // The array of columns to return (pass null to get all)
            null,                    // The columns for the WHERE clause
            null,                // The values for the WHERE clause
            null,                   // don't group the rows
            null,                    // don't filter by row groups
            "done DESC"             // The sort order
        )

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val task = cursor.getString(cursor.getColumnIndex("task"))
            val done = cursor.getInt(cursor.getColumnIndex("done")) == 1
            Log.i("DATABASE", "ID: $id, Task: $task, Done: $done")
        }
        cursor.close()
    }

    fun updateTask(){
        val db = writableDatabase

        var values = ContentValues().apply {
            put("done", false)
        }

        var updatedRowId = db.update("Task", values, "id = 1 AND id = 3", null)
        Log.i("DATABASE", "Updated record id: $updatedRowId")
    }

}