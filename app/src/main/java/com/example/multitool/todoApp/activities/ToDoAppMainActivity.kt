package com.example.multitool.todoApp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.databinding.ActivityTodoappMainBinding
import com.example.multitool.todoApp.adapters.TaskAdapter
import com.example.multitool.todoApp.database.DataBaseManager
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.TaskDAO


class ToDoAppMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoappMainBinding

    private lateinit var taskList:List<Task>
    private lateinit var taskAdapter:TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todoapp_main)
         initView()
    }

    private fun initView(){

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityTodoappMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        // To launch NewTaskActivity from the newTaskButton
        binding.newTaskButton.setOnClickListener{
            intent = Intent(this, NewTaskActivity::class.java)
            finish()
            startActivity(intent)
        }

    }

    private fun loadData() {
        val taskDAO = TaskDAO(this)
        taskList = taskDAO.findAll()

        taskAdapter = TaskAdapter(taskList){
            //onItemClickListener(it)
        }
        val recycler:RecyclerView = binding.todolistRecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = taskAdapter
    }

    private fun onItemClickListener(position: Int) {
        val task:Task = taskList[position]



    }

    // To listen the item selected in a menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}