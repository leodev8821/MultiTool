package com.example.multitool.todoApp.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.databinding.ActivityEditCategoryBinding
import com.example.multitool.databinding.ItemCategoryBinding
import com.example.multitool.todoApp.adapters.CategoryAdapter
import com.example.multitool.todoApp.database.Category
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.CategoryDAO

class EditCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditCategoryBinding
    private lateinit var bindingItemCategory: ItemCategoryBinding
    private lateinit var categoryList:List<Category>
    private lateinit var categoryDAO: CategoryDAO
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        categoryDAO = CategoryDAO(this)

        loadData()

    }

    private fun loadData() {
        categoryList = categoryDAO.findAll()
        categoryAdapter = CategoryAdapter(categoryList,{
            onEditButtonClick(it)
        }, {
            onEraseButtonClick(it)
        })

        val recycler: RecyclerView = binding.editRecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = categoryAdapter
    }

    private fun onEraseButtonClick(position: Int) {
        val category:Category = categoryList[position]
        eraseAlert(category.category, position)
    }

    private fun eraseAlert(category:String, position: Int) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Erase Category")
            .setMessage(
                "Do you want to erase $category?: "
            )
            .setPositiveButton("Yes") { _, _ -> deleteCategory(position)}
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss()}

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun deleteCategory(position: Int) {
        val category: Category = categoryList[position]
        categoryDAO.delete(category)
        refreshData()
        Toast.makeText(this, "Category deleted!", Toast.LENGTH_SHORT).show()
    }

    private fun onEditButtonClick(position: Int) {
        val category: Category = categoryList[position]
        editAlert(position)
    }

    private fun editAlert(position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Edit Category:")
            .setMessage(
                "Category: "
            )
            .setPositiveButton("Save") { _, _ -> updateCategory(position)}
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss()}

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun updateCategory(position: Int) {
        Toast.makeText(this, "Category updated!", Toast.LENGTH_SHORT).show()
    }

    private fun refreshData() {
        categoryList = categoryDAO.findAll()
        categoryAdapter.updateData(categoryList)
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