package com.example.multitool.todoApp.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.multitool.databinding.ActivityNewTaskBinding
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.TaskDAO
import java.text.DateFormat
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

        // To start the activity with the Undone button checked
        defaultRadioButton()

        // To set the current date
        setCurrentDate()

        binding.saveNewTaskButton.setOnClickListener{
            val date:String = binding.dateTextView.text.toString()
            val task:String = binding.newTaskEditText.text.toString()
            val category:String = binding.categoryEditText.text.toString()
            val done:Boolean = doneSelection()

            newTask(date,task,category,done)
            clearForm()

            intent = Intent(this, ToDoAppMainActivity::class.java)
            finish()
            startActivity(intent)

            Toast.makeText(this, "New Task was created", Toast.LENGTH_LONG).show()

        }

    }

    /*
    * Check what Radiobutton was selected and returns the boolean value:
    * Done -> true
    * Undone -> false
    */
    private fun doneSelection(): Boolean {
        val selection = when (binding.doneRadioGroup.checkedRadioButtonId){
            binding.doneRadioButton.id -> true
            binding.unDoneRadioButton.id -> false
            else -> false
        }
        return selection
    }

    /*
    * To create a new Task and save it into the DB
    */
    private fun newTask(date: String?, task: String?, category: String?, done: Boolean) {

        if(date != null && task != null && category != null){
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
        binding.unDoneRadioButton.setChecked(true)
        binding.doneRadioButton.setChecked(false)
    }

    /*
    * To set the current date when a new task is created
     */
    private fun setCurrentDate() {
        val calendar = Calendar.getInstance().time
        val dateFormat = DateFormat.getDateInstance().format(calendar)
        binding.dateTextView.text = dateFormat
    }

    /*
    * To set the Undone as default value
     */
    private fun defaultRadioButton() {
        binding.unDoneRadioButton.setChecked(true)
        binding.doneRadioButton.setChecked(false)
    }
}