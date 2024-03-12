package com.example.multitool.todoApp.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.databinding.ActivityEditCategoryBinding
import com.example.multitool.databinding.ItemCategoryBinding
import com.example.multitool.todoApp.adapters.CategoryAdapter
import com.example.multitool.todoApp.database.Category
import com.example.multitool.todoApp.database.Task
import com.example.multitool.todoApp.database.providers.CategoryDAO
import com.example.multitool.todoApp.database.providers.TaskDAO

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
        categoryAdapter = CategoryAdapter(categoryList, {
            onItemClickListener(it)
        })

        val recycler: RecyclerView = binding.editRecyclerView
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = categoryAdapter
    }

    private fun onItemClickListener(position: Int) {
        val category: Category = categoryList[position]
        showAlert(position)
    }

    private fun showAlert(position: Int) {
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