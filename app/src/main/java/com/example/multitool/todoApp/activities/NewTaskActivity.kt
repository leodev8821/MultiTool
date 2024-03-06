package com.example.multitool.todoApp.activities

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.multitool.databinding.ActivityNewTaskBinding
import com.example.multitool.databinding.NewcategoryEdittextBinding
import com.example.multitool.todoApp.adapters.CategoryAdapter
import com.example.multitool.todoApp.database.Category
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.CategoryDAO
import com.example.multitool.todoApp.database.providers.TaskDAO
import java.util.Calendar


class NewTaskActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private lateinit var binding:ActivityNewTaskBinding

    private lateinit var bindingEditText: NewcategoryEdittextBinding
    private lateinit var categoryList:List<Category>
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var categoryDAO:CategoryDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryDAO = CategoryDAO(this)

        initView()
    }

    private fun initView() {
        initActionBar()

        // To start the activity with unchecked Done
        defaultCheckBox()

        // To set the current date
        setCurrentDate()

        binding.categorySpinner.setOnItemSelectedListener(this)

        loadSpinnerData()

        binding.saveNewTaskButton.setOnClickListener{
            val task:String = binding.newTaskEditText.text.toString()
            val category:String = binding.categorySpinner.getSelectedItem().toString()
            val done:Boolean = binding.doneCheckBox.isChecked

            newTask(task,category,done)
            clearForm()

            intent = Intent(this, ToDoAppMainActivity::class.java)
            finish()
            startActivity(intent)

            Toast.makeText(this, "New Task was created", Toast.LENGTH_LONG).show()
        }

        binding.newCategoryFloatingButton.setOnClickListener {
            alertNewCategory()
        }

    }

    private fun alertNewCategory(){
        bindingEditText = NewcategoryEdittextBinding.inflate(layoutInflater)

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Add new category")
            .setView(bindingEditText.root)
            .setPositiveButton("OK") { _, _ ->
                val newCategoryText:String = bindingEditText.newCategoryEditText.text.toString()
                newCategory(newCategoryText)
                refreshData()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss()}

        builder.show()
    }

    private fun newCategory(category:String?){
        if(category != null){
            val newCategory = Category(-1, category)
            val categoryDAO = CategoryDAO(this)
            categoryDAO.insert(newCategory)
            loadSpinnerData()
            Toast.makeText(this, "New Category was created!", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Please, add a category name", Toast.LENGTH_LONG).show()
        }
    }

    private fun refreshData() {
        categoryList = categoryDAO.findAll()
        categoryAdapter.updateData(categoryList)
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

    private fun loadSpinnerData(){
        categoryList = categoryDAO.findAll()

        // Creating adapter for spinner
        val dataAdapter:ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categoryList.map { it.category })

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner
        binding.categorySpinner.setAdapter(dataAdapter)

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