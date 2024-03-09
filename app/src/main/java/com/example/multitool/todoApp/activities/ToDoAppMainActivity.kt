package com.example.multitool.todoApp.activities

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.databinding.ActivityTodoappMainBinding
import com.example.multitool.databinding.EdittaskDialogBinding
import com.example.multitool.todoApp.adapters.TaskAdapter
import com.example.multitool.todoApp.database.Category
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.CategoryDAO
import com.example.multitool.todoApp.database.providers.TaskDAO


class ToDoAppMainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityTodoappMainBinding

    private lateinit var taskList:List<Task>
    private lateinit var taskAdapter:TaskAdapter

    private lateinit var categoryList:List<Category>

    private lateinit var taskDAO:TaskDAO
    private lateinit var categoryDAO:CategoryDAO

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
        categoryDAO = CategoryDAO(this)

        loadData()

        // To launch NewTaskActivity from the newTaskButton
        binding.newTaskButton.setOnClickListener{
            val intent: Intent = Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun modifyTaskDone(position: Int){
        val task:Task = taskList[position]
        task.done = !task.done
        taskDAO.update(task)
        taskAdapter.notifyItemChanged(position)
    }

    private fun deleteTask(position:Int){
        val task:Task = taskList[position]
        taskDAO.delete(task)
        refreshData()
    }

    private fun editTask(position: Int){
        val task:Task = taskList[position]
        loadSpinnerDataDialog(task.category)

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        val binding:EdittaskDialogBinding = EdittaskDialogBinding.inflate(layoutInflater)
        builder.setView(binding.root)

        //Set taskEditTextDialog with the actual name of Task
        binding.taskTextFieldDialog.editText?.setText(task.task)
        binding.categorySpinnerDialog.setSelection(position)
        builder.setTitle("Edit Task ${task.task}")

        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        builder.setPositiveButton("Save", null)

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

        // Need to move listener after show dialog to prevent dismiss
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val taskName = binding.taskTextFieldDialog.editText?.text.toString()
            if (taskName.isNotEmpty()) {
                task.task = taskName
                taskDAO.update(task)
                taskAdapter.notifyItemChanged(position)
                Toast.makeText(this, "Successful edited task!", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            } else {
                Toast.makeText(this, "Unsuccessful edited task!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun loadSpinnerDataDialog(category:String){
        val categoryElement:Category? = categoryDAO.find(category)

        // Creating adapter for spinner
        val dataAdapter: ArrayAdapter<String>? = categoryElement?.let {
            ArrayAdapter<String>(this,
                R.layout.simple_spinner_item, it.id)
        }

        // Drop down layout style - list view with radio button
        dataAdapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        val binding:EdittaskDialogBinding = EdittaskDialogBinding.inflate(layoutInflater)
        binding.categorySpinnerDialog.setAdapter(dataAdapter)

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        // On selecting a spinner item
        val category = parent.getItemAtPosition(position).toString()
        Log.i("Spinner position: ", position.toString())

        // Showing selected spinner item
        Toast.makeText(parent.context, "You selected: $category", Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun refreshData() {
        taskList = taskDAO.findAll()
        taskAdapter.updateData(taskList)
    }

    private fun loadData() {
        taskList = taskDAO.findAll()
        categoryList = categoryDAO.findAll()

        taskAdapter = TaskAdapter(taskList,{
            onItemClickListener(it)
        }, {
            onCheckBoxListener(it)
        })
        val recycler:RecyclerView = binding.todolistRecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = taskAdapter
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
            .setNeutralButton("Edit") {_, _ -> editTask(position)}

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun onCheckBoxListener(position: Int) {
        modifyTaskDone(position)
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