package com.example.multitool.todoApp.database.providers

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.DataBaseManager
import com.example.multitool.todoApp.database.TasksModel


class TaskDAO(context: Context){

    private val databaseManager: DataBaseManager = DataBaseManager(context)

    fun insert(task: Task): Task {

        // Gets the data repository in write mode
        val db = databaseManager.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(TasksModel.TaskTable.COLUMN_DATE, task.date)
            put(TasksModel.TaskTable.COLUMN_TASK, task.task)
            put(TasksModel.TaskTable.COLUMN_TASK_CATEGORY, task.category)
            put(TasksModel.TaskTable.COLUMN_DONE, task.done)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(TasksModel.TaskTable.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()
        
        task.id = newRowId.toInt()
        
        return task

    }

    fun update(task: Task){

        val db = databaseManager.writableDatabase

        val values = ContentValues().apply {
            put(TasksModel.TaskTable.COLUMN_TASK, task.task)
            put(TasksModel.TaskTable.COLUMN_TASK_CATEGORY, task.category)
            put(TasksModel.TaskTable.COLUMN_DONE, task.done)
        }

        val updatedRows = db.update(TasksModel.TaskTable.TABLE_NAME, values, "${TasksModel.TaskTable.COLUMN_NAME_ID} = ${task.id}", null)
        Log.i("DATABASE", "Updated $updatedRows record, with id: ${task.id} with Done state: ${task.done}, TaskName: ${task.task} and Category: ${task.category}")

        db.close()

    }

    fun delete(task: Task){
        val db = databaseManager.writableDatabase

        val deleteRows = db.delete(TasksModel.TaskTable.TABLE_NAME, "${TasksModel.TaskTable.COLUMN_NAME_ID} = ${task.id}", null)
        Log.i("DATABASE", "Deleted rows: $deleteRows")

        db.close()

    }

    @SuppressLint("Range")
    fun find(id:Int): Task? {

        val db = databaseManager.writableDatabase

        val cursor = db.query(
            TasksModel.TaskTable.TABLE_NAME,                         // The Table to query
            TasksModel.TaskTable.COLUMN_NAMES,                        // The array of columns to return (pass null to get all)
            "${TasksModel.TaskTable.COLUMN_NAME_ID} = $id",   // The columns for the WHERE clause
            null,                                      // The values for the WHERE clause
            null,                                          // don't group the rows
            null,                                           // don't filter by row groups
            null                                           // The sort order
        )

        var task: Task? = null

        if (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_NAME_ID))
            val date = cursor.getString(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_DATE)).toLong()
            val taskName = cursor.getString(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_TASK))
            val category = cursor.getString(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_TASK_CATEGORY))
            val done = cursor.getInt(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_DONE)) == 1

            // Every task is added to a list
            task = Task(id, date, taskName, category, done)
        }
        cursor.close()
        db.close()

        return task

    }

    @SuppressLint("Range")
    fun findAll(): List<Task>{

        val db = databaseManager.writableDatabase

        val cursor = db.query(
            TasksModel.TaskTable.TABLE_NAME,                       // The Table to query
            TasksModel.TaskTable.COLUMN_NAMES,                     // The array of columns to return (pass null to get all)
            null,                                         // The columns for the WHERE clause
            null,                                      // The values for the WHERE clause
            null,                                          // don't group the rows
            null,                                           // don't filter by row groups
            TasksModel.TaskTable.SORT_ORDER                        // The sort order
        )

        val list:MutableList<Task> = mutableListOf()

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_NAME_ID))
            val date = cursor.getString(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_DATE)).toLong()
            val taskName = cursor.getString(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_TASK))
            val category = cursor.getString(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_TASK_CATEGORY))
            val done = cursor.getInt(cursor.getColumnIndex(TasksModel.TaskTable.COLUMN_DONE)) == 1

            // Every task is added to a list
            val task = Task(id, date, taskName, category, done)
            list.add(task)
        }
        cursor.close()
        db.close()

        return list

    }
}
