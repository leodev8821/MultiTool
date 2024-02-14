package com.example.multitool.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multitool.R
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
            intent = Intent(this, ConversorActivity::class.java)
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
}