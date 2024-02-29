package com.example.multitool.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.multitool.R
import com.example.multitool.apcalculator.APCalculatorActivity
import com.example.multitool.imccalculator.IMCCalculatorActivity
import com.example.multitool.superheroes.activities.MainActivitySuperheroes
import com.example.multitool.tempconversor.TempConversorActivity
import com.example.multitool.todoApp.activities.ToDoAppMainActivity
import com.example.multitool.zodiapp.MainActivityZodiapp
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var buttonConversor : FloatingActionButton
    lateinit var buttonCalcIMC : FloatingActionButton
    lateinit var buttonUnitConversor : FloatingActionButton
    lateinit var buttonZodiapp : FloatingActionButton
    lateinit var buttonSearchSuperheroes : FloatingActionButton
    lateinit var buttonToDoList:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalcIMC = findViewById(R.id.buttonCalcIMC)
        buttonConversor = findViewById(R.id.buttonConversor)
        buttonUnitConversor = findViewById(R.id.buttonUnitConversor)
        buttonZodiapp = findViewById(R.id.buttonZodiapp)
        buttonSearchSuperheroes = findViewById(R.id.buttonSearchSuperheroes)
        buttonToDoList = findViewById(R.id.buttonToDoList)

        buttonCalcIMC.setOnClickListener {
            intent = Intent(this, IMCCalculatorActivity::class.java)
            startActivity(intent)
        }

        buttonConversor.setOnClickListener {
            intent = Intent(this, TempConversorActivity::class.java)
            startActivity(intent)
        }

        buttonUnitConversor.setOnClickListener {
            intent = Intent(this, APCalculatorActivity::class.java)
            startActivity(intent)
        }

        buttonZodiapp.setOnClickListener {
            intent = Intent(this, MainActivityZodiapp::class.java)
            startActivity(intent)
        }

        buttonSearchSuperheroes.setOnClickListener {
            intent = Intent(this, MainActivitySuperheroes::class.java)
            startActivity(intent)
        }

        buttonToDoList.setOnClickListener {
            intent = Intent(this, ToDoAppMainActivity::class.java)
            startActivity(intent)
        }

    }

    // To listen the item selected in a menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.opt1 ->{
                Toast.makeText(this, "He pulsado Refresh", Toast.LENGTH_LONG).show()
            }
            R.id.opt2 ->{
                Toast.makeText(this, "He pulsado Settings", Toast.LENGTH_LONG).show()
            }
            R.id.opt3 ->{
                Toast.makeText(this, getString(R.string.toastAbout), Toast.LENGTH_LONG).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        //super.onBackPressed()
        showExitDialog()
    }

    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setIcon(R.drawable.caution_svg)
            .setTitle("Cerrar aplicación")
            .setMessage("Esta seguro de que quiere salir de la aplicación?")
            .setPositiveButton("Salir") { _, _ -> finish() }
            .setNegativeButton("No") { dialog, _ -> dialog?.cancel() }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}