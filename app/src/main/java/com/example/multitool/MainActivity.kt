package com.example.multitool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var buttonConversor:FloatingActionButton
    lateinit var buttonCalcIMC:FloatingActionButton
    lateinit var buttonUnitConversor:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalcIMC = findViewById(R.id.buttonCalcIMC)
        buttonConversor = findViewById(R.id.buttonConversor)

        buttonCalcIMC.setOnClickListener {
            intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        buttonConversor.setOnClickListener {
            intent = Intent(this, ConversorActivity::class.java)
            startActivity(intent)
        }

        buttonUnitConversor.setOnClickListener {
            intent = Intent(this, AreaPerimeterCalculator::class.java)
            startActivity(intent)
        }

    }
}