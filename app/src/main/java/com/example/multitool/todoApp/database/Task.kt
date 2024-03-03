package com.example.multitool.todoApp.database


class Task (var id:Int, var date:String, var task:String, var category:String, var done:Boolean) {


    override fun toString(): String {
        return "$id -> Date: $date, Task: $task, Category: $category - $done"
    }

}




