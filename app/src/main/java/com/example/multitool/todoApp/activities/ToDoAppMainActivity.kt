package com.example.multitool.todoApp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multitool.R
import com.example.multitool.todoApp.database.DataBaseHelper

class ToDoAppMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_app_main)

        var db:DataBaseHelper = DataBaseHelper(this)
        //db.createTask()
        db.readTask()
        db.updateTask()
    }
}