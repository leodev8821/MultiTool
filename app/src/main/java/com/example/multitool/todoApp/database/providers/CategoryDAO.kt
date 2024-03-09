package com.example.multitool.todoApp.database.providers

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.multitool.todoApp.database.Category
import com.example.multitool.todoApp.database.DataBaseManager
import com.example.multitool.todoApp.database.TasksModel

class CategoryDAO (context:Context) {
    private val databaseManager: DataBaseManager = DataBaseManager(context)

    fun insert(category: Category): Category {

        // Gets the data repository in write mode
        val db = databaseManager.writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(TasksModel.CategoryTable.COLUMN_CATEGORY, category.category)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(TasksModel.CategoryTable.TABLE_NAME, null, values)
        Log.i("DATABASE", "New category id: $newRowId")

        db.close()

        category.id = newRowId.toInt()

        return category

    }

    fun update(category: Category){

        val db = databaseManager.writableDatabase

        val values = ContentValues().apply {
            put(TasksModel.CategoryTable.COLUMN_CATEGORY, category.category)
        }

        val updatedRows = db.update(TasksModel.CategoryTable.TABLE_NAME, values, "${TasksModel.CategoryTable.COLUMN_NAME_ID} = ${category.id}", null)
        Log.i("DATABASE", "Updated $updatedRows record id: ${category.id} with new Category: ${category.category}")

        db.close()

    }

    fun delete(category: Category){
        val db = databaseManager.writableDatabase

        val deleteRows = db.delete(TasksModel.CategoryTable.TABLE_NAME, "${TasksModel.CategoryTable.COLUMN_NAME_ID} = ${category.id}", null)
        Log.i("DATABASE", "Deleted rows: $deleteRows with Category: ${category.category}")

        db.close()

    }

    @SuppressLint("Range")
    fun find(category:String): Category? {

        val db = databaseManager.writableDatabase
        val cursor = db.query(
            TasksModel.CategoryTable.TABLE_NAME,                         // The Table to query
            TasksModel.CategoryTable.COLUMN_NAMES,                        // The array of columns to return (pass null to get all)
            "${TasksModel.CategoryTable.COLUMN_CATEGORY} = '$category'",   // The columns for the WHERE clause
            null,                                      // The values for the WHERE clause
            null,                                          // don't group the rows
            null,                                           // don't filter by row groups
            null                                           // The sort order
        )

        var categoryElement: Category? = null

        if(cursor != null){
            if (cursor.moveToNext()){
                val idCategory = cursor.getInt(cursor.getColumnIndex(TasksModel.CategoryTable.COLUMN_NAME_ID))
                val categoryName = cursor.getString(cursor.getColumnIndex(TasksModel.CategoryTable.COLUMN_CATEGORY))

                // The found element is added to CategoryElement
                categoryElement = Category(idCategory, categoryName)
            }
            cursor.close()
            db.close()
        }else{
            Log.i("Cursor Error","Error de cursor")
        }

        return categoryElement

    }

    @SuppressLint("Range")
    fun findAll(): List<Category>{

        val db = databaseManager.writableDatabase

        val cursor = db.query(
            TasksModel.CategoryTable.TABLE_NAME,                       // The Table to query
            TasksModel.CategoryTable.COLUMN_NAMES,                     // The array of columns to return (pass null to get all)
            null,                                         // The columns for the WHERE clause
            null,                                      // The values for the WHERE clause
            null,                                          // don't group the rows
            null,                                           // don't filter by row groups
            TasksModel.CategoryTable.SORT_ORDER                        // The sort order
        )

        val list:MutableList<Category> = mutableListOf()

        while (cursor.moveToNext()){
            val idCategory = cursor.getInt(cursor.getColumnIndex(TasksModel.CategoryTable.COLUMN_NAME_ID))
            val categoryName = cursor.getString(cursor.getColumnIndex(TasksModel.CategoryTable.COLUMN_CATEGORY))

            // Every task is added to a list
            val category = Category(idCategory, categoryName)
            list.add(category)
        }
        cursor.close()
        db.close()

        return list

    }
}