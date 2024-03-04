package com.example.multitool.todoApp.database

import android.provider.BaseColumns

class TasksModel {

    // CONSTANTS
    // If you change the database schema, you must increment the database version.
    companion object{

        const val DATABASE_NAME = "ToDoApp.db"
        const val DATABASE_VERSION = 4
        const val COLUMN_NAME_ID = "_id"
        const val TABLE_NAME = "Task"
        const val COLUMN_DATE = "Date"
        const val COLUMN_TASK = "Task"
        const val COLUMN_CATEGORY = "Category"
        const val COLUMN_DONE = "Done"
        val COLUMN_NAMES = arrayOf(
            COLUMN_NAME_ID,
            COLUMN_DATE,
            COLUMN_TASK,
            COLUMN_CATEGORY,
            COLUMN_DONE
        )

        val SORT_ORDER = "$COLUMN_NAME_ID DESC"

        const val SQL_CREATE_TABLE_TASK =
            "CREATE TABLE $TABLE_NAME (" +
            "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$COLUMN_DATE INTEGER," +
            "$COLUMN_TASK TEXT," +
            "$COLUMN_CATEGORY TEXT," +
            "$COLUMN_DONE BOOLEAN)"

        const val SQL_DELETE_TABLE_TASK = "DROP TABLE IF EXISTS $TABLE_NAME"

    }



}