package com.example.multitool.todoApp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseManager (context: Context):
    SQLiteOpenHelper(context, TasksModel.DATABASE_NAME, null, TasksModel.DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TasksModel.SQL_CREATE_TABLE)
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
        db.execSQL(TasksModel.SQL_DELETE_TABLE)
    }

}