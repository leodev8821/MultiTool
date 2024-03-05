package com.example.multitool.todoApp.database

class Category (var id:Int, var category:String) {

    override fun toString(): String {
        return "$id -> Category: $category"
    }
}