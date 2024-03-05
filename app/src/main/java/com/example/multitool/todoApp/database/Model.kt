package com.example.multitool.todoApp.database

class TasksModel {

    // CONSTANTS
    // If you change the database schema, you must increment the database version.
    companion object{

        const val DATABASE_NAME = "ToDoApp.db"
        const val DATABASE_VERSION = 1

    }

    object TaskTable{
        const val COLUMN_NAME_ID = "_id"
        const val TABLE_NAME = "Task"
        const val COLUMN_DATE = "Date"
        const val COLUMN_TASK = "Task"
        const val COLUMN_TASK_CATEGORY = "TaskCategory"
        const val TABLE_CATEGORY = CategoryTable.COLUMN_NAME_ID
        const val COLUMN_DONE = "Done"
        val COLUMN_NAMES = arrayOf(
            COLUMN_NAME_ID,
            COLUMN_DATE,
            COLUMN_TASK,
            COLUMN_TASK_CATEGORY,
            COLUMN_DONE
        )

        val SORT_ORDER = "$COLUMN_NAME_ID DESC"

        const val SQL_CREATE_TABLE_TASK =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_DATE INTEGER, " +
                    "$COLUMN_TASK TEXT, " +
                    "$COLUMN_TASK_CATEGORY INTEGER, " +
                    "FOREIGN KEY ($COLUMN_TASK_CATEGORY) REFERENCES ${CategoryTable.TABLE_NAME} ($TABLE_CATEGORY), " +
                    "$COLUMN_DONE BOOLEAN);"

        const val SQL_DELETE_TABLE_TASK = "DROP TABLE IF EXISTS $TABLE_NAME"
    }

    object CategoryTable{
        const val TABLE_NAME = "Category"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_CATEGORY = "Category"
        val COLUMN_NAMES = arrayOf(
            COLUMN_NAME_ID,
            COLUMN_CATEGORY,
        )

        val SORT_ORDER = "$COLUMN_NAME_ID DESC"

        const val SQL_CREATE_TABLE_CATEGORY =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_CATEGORY TEXT)"

        const val SQL_DELETE_TABLE_CATEGORY = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}