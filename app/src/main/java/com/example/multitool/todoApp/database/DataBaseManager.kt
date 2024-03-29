package com.example.multitool.todoApp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseManager (context: Context):
    SQLiteOpenHelper(context, TasksModel.DATABASE_NAME, null, TasksModel.DATABASE_VERSION){

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys = ON;")
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TasksModel.CategoryTable.SQL_CREATE_TABLE_CATEGORY)
        db.execSQL(TasksModel.TaskTable.SQL_CREATE_TABLE_TASK)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onDelete(db)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    private fun onDelete(db: SQLiteDatabase){
        db.execSQL(TasksModel.TaskTable.SQL_DELETE_TABLE_TASK)
        db.execSQL(TasksModel.CategoryTable.SQL_DELETE_TABLE_CATEGORY)
    }

}