package com.example.multitool.todoApp.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.multitool.databinding.ActivityNewTaskBinding
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.TaskDAO
import android.text.format.DateFormat
import java.util.Calendar

class NewTaskActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNewTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        initActionBar()

        // To start the activity with unchecked Done
        defaultCheckBox()

        // To set the current date
        setCurrentDate()

        binding.saveNewTaskButton.setOnClickListener{
            val task:String = binding.newTaskEditText.text.toString()
            val category:String = binding.categoryEditText.text.toString()
            val done:Boolean = binding.doneCheckBox.isChecked

            newTask(task,category,done)
            clearForm()

            intent = Intent(this, ToDoAppMainActivity::class.java)
            finish()
            startActivity(intent)

            Toast.makeText(this, "New Task was created", Toast.LENGTH_LONG).show()

        }

    }

    /*
    * To create a new Task and save it into the DB
    */
    private fun newTask(task: String?, category: String?, done: Boolean) {

        if(task != null && category != null){
            val date:Long = getCurrentDate()
            val newTask = Task(-1,date,task, category, done)
            val taskDAO = TaskDAO(this)
            taskDAO.insert(newTask)
        }

    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // To listen the item selected in a menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                intent = Intent(this, ToDoAppMainActivity::class.java)
                finish()
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    /*
    * To clear the New Task Form after click on Button Save
     */
    private fun clearForm(){
        binding.newTaskEditText.text?.clear()
        binding.categoryEditText.text?.clear()
        binding.doneCheckBox.isChecked= false
    }

    /*
    * To set the current date when a new task is created
     */
    private fun setCurrentDate() {
        val calendar = getCurrentDate()
        val dateFormat = DateFormat.format("dd-MMMM-yyyy", calendar)
        binding.dateTextView.text = dateFormat
    }

    private fun getCurrentDate():Long{
        return Calendar.getInstance().timeInMillis
    }

    /*
    * To set the Undone as default value
     */
    private fun defaultCheckBox() {
        binding.doneCheckBox.isChecked = false
    }
}