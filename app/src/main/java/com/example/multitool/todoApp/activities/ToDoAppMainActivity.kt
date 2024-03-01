package com.example.multitool.todoApp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.multitool.R
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.TaskDAO


class ToDoAppMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_app_main)

        var task = Task(-1,"2024-02-04","Hacer compra", "Hogar", false)

        val taskDAO = TaskDAO(this)

        task = taskDAO.insert(task)

        val taskList = taskDAO.findAll()

        for(task in taskList){
            Log.i("DATABASE", task.toString())
        }

        var task2: Task? = taskDAO.find(2)

        if(task2 != null){
            Log.i("DATABASE", task2.toString())

            task2.task = "Lavar coche"

            taskDAO.update(task2)
        }

        task2 = taskDAO.find(2)
        Log.i("DATABASE", task2.toString())

        if(task2 != null){
            taskDAO.delete(task2)
            task2 = taskDAO.find(2)

            if(task2 != null){
                Log.i("DATABASE", task2.toString())
            }
            else{
                Log.i("DATABASE", "La tarea ha sido borrada")
            }
        }

    }
}