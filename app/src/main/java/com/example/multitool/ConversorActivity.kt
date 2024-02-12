package com.example.multitool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import com.google.android.material.textfield.TextInputEditText

class ConversorActivity : AppCompatActivity() {

    lateinit var inputInitTemp : TextInputEditText
    lateinit var radioInitCelsius : RadioButton
    lateinit var radioInitFahrenheit : RadioButton
    lateinit var radioInitKelvin : RadioButton
    lateinit var radioFinalCelsius : RadioButton
    lateinit var radioFinalFahrenheit : RadioButton
    lateinit var radioFinalKelvin : RadioButton
    lateinit var btn_convertir : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        initView()
    }

    private fun initView(){

        inputInitTemp = findViewById(R.id.inputInitTemp)
        radioInitCelsius = findViewById(R.id.radioInitCelsius)
        radioInitFahrenheit = findViewById(R.id.radioInitFahrenheit)
        radioInitKelvin = findViewById(R.id.radioInitKelvin)
        radioFinalCelsius = findViewById(R.id.radioFinalCelsius)
        radioFinalFahrenheit = findViewById(R.id.radioFinalFahrenheit)
        radioFinalKelvin = findViewById(R.id.radioFinalKelvin)
        btn_convertir = findViewById(R.id.btn_convertir)

        btn_convertir.setOnClickListener {

            val initTemp : Float? = inputInitTemp.text.toString().toFloatOrNull()
            val initUnit : Boolean = radioInitCelsius.isChecked
        }


    }

    private fun convertTemp() {
        TODO("Not yet implemented")
    }

}

