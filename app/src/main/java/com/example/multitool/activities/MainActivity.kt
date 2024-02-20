package com.example.multitool.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.multitool.R
import com.example.multitool.apcalculator.AreaPerimeterCalculator
import com.example.multitool.imccalculator.IMCCalculatorActivity
import com.example.multitool.tempconversor.TempConversorActivity
import com.example.multitool.zodiapp.Zodiapp
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var buttonConversor : FloatingActionButton
    lateinit var buttonCalcIMC : FloatingActionButton
    lateinit var buttonUnitConversor : FloatingActionButton
    lateinit var buttonZodiapp : FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalcIMC = findViewById(R.id.buttonCalcIMC)
        buttonConversor = findViewById(R.id.buttonConversor)
        buttonUnitConversor = findViewById(R.id.buttonUnitConversor)
        buttonZodiapp = findViewById(R.id.buttonZodiapp)

        buttonCalcIMC.setOnClickListener {
            intent = Intent(this, IMCCalculatorActivity::class.java)
            startActivity(intent)
        }

        buttonConversor.setOnClickListener {
            intent = Intent(this, TempConversorActivity::class.java)
            startActivity(intent)
        }

        buttonUnitConversor.setOnClickListener {
            intent = Intent(this, AreaPerimeterCalculator::class.java)
            startActivity(intent)
        }

        buttonZodiapp.setOnClickListener {
            intent = Intent(this, Zodiapp::class.java)
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
}