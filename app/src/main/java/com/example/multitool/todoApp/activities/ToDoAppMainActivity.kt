package com.example.multitool.todoApp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.databinding.ActivityTodoappMainBinding
import com.example.multitool.todoApp.adapters.TaskAdapter
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.TaskDAO


class ToDoAppMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoappMainBinding

    private lateinit var taskList:List<Task>
    private lateinit var taskAdapter:TaskAdapter

    private lateinit var taskDAO:TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoappMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         initView()
    }

    private fun initView(){

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        taskDAO = TaskDAO(this)

        loadData()

        // To launch NewTaskActivity from the newTaskButton
        binding.newTaskButton.setOnClickListener{
            val intent: Intent = Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }


    }

    private fun changeDone(position: Int){
        val task:Task = taskList[position]
        task.done = !task.done
        taskDAO.update(task)
        taskAdapter.notifyDataSetChanged()
    }

    private fun deleteTask(position:Int){
        val task:Task = taskList[position]
        taskDAO.delete(task)
        refreshData()

    }

    private fun loadData() {
        taskList = taskDAO.findAll()

        taskAdapter = TaskAdapter(taskList, {
            onItemClickListener(it)
        }, {
            onCheckBoxListener(it)
        })
        val recycler:RecyclerView = binding.todolistRecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = taskAdapter
    }

    private fun refreshData() {
        taskList = taskDAO.findAll()
        taskAdapter.updateData(taskList)
    }

    private fun onItemClickListener(position: Int) {
        val task:Task = taskList[position]
        val done:String = if(task.done){
            "Yes"
        }else{
            "No"
        }
        showAlert(task.task, done, task.category, position)
    }

    private fun showAlert(title:String, done:String, category:String, position:Int){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle(title)
            .setMessage(
                "Category: $category "+
                        "\nDone?: $done "
            )
            .setPositiveButton("Delete") { _, _ -> deleteTask(position)}
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss()}

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun onCheckBoxListener(position: Int) {
        //cambiar el checkbox done

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