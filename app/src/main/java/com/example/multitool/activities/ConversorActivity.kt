package com.example.multitool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.multitool.R
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat

class ConversorActivity : AppCompatActivity() {

    lateinit var inputInitTemp : TextInputEditText
    lateinit var resultTemp : TextView

    lateinit var radioInitCelsius : RadioButton
    lateinit var radioInitFahrenheit : RadioButton
    lateinit var radioInitKelvin : RadioButton

    lateinit var radioFinalCelsius : RadioButton
    lateinit var radioFinalFahrenheit : RadioButton
    lateinit var radioFinalKelvin : RadioButton

    lateinit var btn_convertir : Button

    lateinit var radioGroupInit : RadioGroup
    lateinit var radioGroupFinal : RadioGroup



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        initView()
    }

    private fun initView(){

        inputInitTemp = findViewById(R.id.inputInitTemp)
        resultTemp = findViewById(R.id.resultTemp)

        radioInitCelsius = findViewById(R.id.radioInitCelsius)
        radioInitFahrenheit = findViewById(R.id.radioInitFahrenheit)
        radioInitKelvin = findViewById(R.id.radioInitKelvin)

        radioFinalCelsius = findViewById(R.id.radioFinalCelsius)
        radioFinalFahrenheit = findViewById(R.id.radioFinalFahrenheit)
        radioFinalKelvin = findViewById(R.id.radioFinalKelvin)

        btn_convertir = findViewById(R.id.btn_convertir)

        radioGroupInit = findViewById(R.id.radioGroupInit)
        radioGroupFinal = findViewById(R.id.radioGroupFinal)


        btn_convertir.setOnClickListener {

            val initTemp : Float? = inputInitTemp.text.toString().toFloatOrNull()
            val initUnit : String = initTempSelected()
            val finalUnit : String = finalTempSelected()

            if(initTemp != null){
                conversor(initTemp, initUnit, finalUnit)
            }
        }
    }

    private fun conversor(initTemp: Float, initUnit: String, finalUnit: String){

        val DEGREES_CELSIUS = "Celsius"
        val DEGREES_KELVIN = "Kelvin"
        val DEGREES_FAHRENHEIT = "Fahrenheit"

        val formatter = DecimalFormat("#.##")
        var finalTemp:String = ""

        when(initUnit){

            DEGREES_CELSIUS -> {
                when(finalUnit){
                    DEGREES_FAHRENHEIT -> {
                        val f = (9.0f / 5.0f) * initTemp + 32.0f
                        finalTemp = "${formatter.format(f)}º ${DEGREES_FAHRENHEIT}"
                    }
                    DEGREES_KELVIN -> {
                        val f = initTemp + 273.15f
                        finalTemp = "${formatter.format(f)}º ${DEGREES_KELVIN}"
                    }
                    DEGREES_CELSIUS -> {
                        finalTemp = "${formatter.format(initTemp)}º ${DEGREES_CELSIUS}"
                    }
                }
            }

            DEGREES_KELVIN -> {
                when(finalUnit){
                    DEGREES_CELSIUS -> {
                        val f = initTemp - 273.15f
                        finalTemp = "${formatter.format(f)}º ${DEGREES_CELSIUS}"
                    }
                    DEGREES_FAHRENHEIT -> {
                        val f = (initTemp + 459.67f) * (5.0f / 9.0f)
                        finalTemp = "${formatter.format(f)}º ${DEGREES_FAHRENHEIT}"
                    }
                    DEGREES_KELVIN -> {
                        finalTemp = "${formatter.format(initTemp)}º ${DEGREES_KELVIN}"
                    }
                }
            }

            DEGREES_FAHRENHEIT -> {
                when(finalUnit){
                    DEGREES_CELSIUS -> {
                        val f = (((initTemp - 32.0f) * 5.0f) / 9.0f)
                        finalTemp = "${formatter.format(f)}º ${DEGREES_CELSIUS}"
                    }
                    DEGREES_KELVIN -> {
                        val f = (5.0f / 9.0f * (initTemp - 32.0f)) + 273.15f
                        finalTemp = "${formatter.format(f)}º ${DEGREES_KELVIN}"
                    }
                    DEGREES_FAHRENHEIT -> {
                        finalTemp = "${formatter.format(initTemp)}º ${DEGREES_FAHRENHEIT}"
                    }
                }
            }
        }
        resultTemp.text = finalTemp
    }

    private fun finalTempSelected() : String {
        val finalUnit = when(radioGroupFinal.checkedRadioButtonId){
            R.id.radioFinalCelsius -> {
                radioFinalCelsius.text.toString()
            }
            R.id.radioFinalFahrenheit -> {
                radioFinalFahrenheit.text.toString()
            }
            R.id.radioFinalKelvin -> {
                radioFinalKelvin.text.toString()
            }
            else -> {
                "Por favor seleccione una unidad a convertir"
            }
        }
        return finalUnit
    }

    private fun initTempSelected() : String {
        val initUnit = when(radioGroupInit.checkedRadioButtonId){
            R.id.radioInitCelsius -> {
                radioInitCelsius.text.toString()
            }
            R.id.radioInitFahrenheit -> {
                radioInitFahrenheit.text.toString()
            }
            R.id.radioInitKelvin -> {
                radioInitKelvin.text.toString()
            }
            else -> {
                "Por favor seleccione una unidad inicial"
            }
        }
        return initUnit
    }



}

