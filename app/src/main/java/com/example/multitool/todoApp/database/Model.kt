package com.example.multitool.todoApp.database

import android.provider.BaseColumns

class TasksModel {

    // CONSTANTS
    // If you change the database schema, you must increment the database version.
    companion object{
        const val DATABASE_NAME = "ToDoApp.db"
        const val DATABASE_VERSION = 3
        const val COLUMN_NAME_ID = "_id"

        const val SQL_CREATE_TABLE =
            "CREATE TABLE ${TaskEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${TaskEntry.COLUMN_DATE} TEXT," +
                    "${TaskEntry.COLUMN_TASK} TEXT," +
                    "${TaskEntry.COLUMN_CATEGORY} TEXT," +
                    "${TaskEntry.COLUMN_DONE} BOOLEAN)"

        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TaskEntry.TABLE_NAME}"

        const val TABLE_NAME = "Task"
    }

    // Table contents are grouped together in an anonymous object.
    object TaskEntry : BaseColumns {
        const val TABLE_NAME = "Task"
        const val COLUMN_DATE = "Date"
        const val COLUMN_TASK = "Task"
        const val COLUMN_CATEGORY = "Category"
        const val COLUMN_DONE = "Done"
    }


}