package com.example.multitool.todoApp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.multitool.R
import com.example.multitool.databinding.ActivityTodoappMainBinding


class ToDoAppMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoappMainBinding

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

        // To launch NewTaskActivity from the newTaskButton
        binding.newTaskButton.setOnClickListener{
            intent = Intent(this, NewTaskActivity::class.java)
            startActivity(intent)
        }

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