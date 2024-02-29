package com.example.multitool.todoApp.database

import android.provider.BaseColumns

object Tasks {
    // Table contents are grouped together in an anonymous object.
    object TaskEntry : BaseColumns {
        const val TABLE_NAME = "Task"
        const val COLUMN_TASK = "Task"
        const val COLUMN_CATEGORY = "Category"
        const val COLUMN_DONE = "Done"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${TaskEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TaskEntry.COLUMN_TASK} TEXT," +
                "${TaskEntry.COLUMN_CATEGORY} TEXT," +
                "${TaskEntry.COLUMN_DONE} BOOLEAN)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TaskEntry.TABLE_NAME}"
}