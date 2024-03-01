package com.example.multitool.todoApp.database

class Task (var id:Int, var date:String, var task:String, var category:String, var done:Boolean) {

    companion object {
        const val TABLE_NAME = "Task"
        const val COLUMN_DATE = "Date"
        const val COLUMN_TASK = "Task"
        const val COLUMN_CATEGORY = "Category"
        const val COLUMN_DONE = "Done"

        val COLUMN_NAMES = arrayOf(
            TasksModel.COLUMN_NAME_ID,
            COLUMN_DATE,
            COLUMN_TASK,
            COLUMN_CATEGORY,
            COLUMN_DONE
        )
    }

    override fun toString(): String {
        return "$id -> Date: $date, Task: $task, Category: $category - $done"
    }

}
