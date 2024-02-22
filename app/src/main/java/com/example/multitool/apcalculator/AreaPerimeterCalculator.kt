package com.example.multitool.apcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.multitool.R
import com.google.android.material.textfield.TextInputEditText
import java.util.logging.Logger
import kotlin.math.log

class AreaPerimeterCalculator : AppCompatActivity() {

    lateinit var radioGroupGeometric : RadioGroup
    lateinit var radioCircle : RadioButton
    lateinit var radioRectangle : RadioButton
    lateinit var radioTriangle : RadioButton

    lateinit var radioGroupAP : RadioGroup
    lateinit var radioArea : RadioButton
    lateinit var radioPerimeter : RadioButton

    lateinit var circleCard : CardView
    lateinit var inputRadio : TextInputEditText

    lateinit var rectangleCard : CardView
    lateinit var rectangleBase : TextInputEditText
    lateinit var rectangleHeight : TextInputEditText

    lateinit var triangleAreaCard : CardView
    lateinit var triangleBase : TextInputEditText
    lateinit var triangleHeight : TextInputEditText

    lateinit var trianglePerimeterCard : CardView
    lateinit var triangleSide1 : TextInputEditText
    lateinit var triangleSide2 : TextInputEditText
    lateinit var triangleSide3 : TextInputEditText

    lateinit var resultCard : CardView
    lateinit var result : TextView

    lateinit var bntCalculate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area_perimeter_calculator)

        initView()
    }

    private fun initView() {

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialization for topCard
        radioGroupGeometric = findViewById(R.id.radioGroupGeometric)
        radioCircle = findViewById(R.id.radioCircle)
        radioRectangle = findViewById(R.id.radioRectangle)
        radioTriangle = findViewById(R.id.radioTriangle)

        // Initialization for measureCard
        radioGroupAP = findViewById(R.id.radioGroupAP)
        radioArea = findViewById(R.id.radioArea)
        radioPerimeter = findViewById(R.id.radioPerimeter)

        // Initialization for Calculate Button
        bntCalculate = findViewById(R.id.bntCalculate)

        // Initialization for hidden Cards
        circleCard = findViewById(R.id.circleCard)
        inputRadio = findViewById(R.id.inputRadio)
        rectangleCard = findViewById(R.id.rectangleCard)
        rectangleBase = findViewById(R.id.rectangleBase)
        rectangleHeight = findViewById(R.id.rectangleHeight)
        triangleAreaCard = findViewById(R.id.triangleAreaCard)
        triangleBase = findViewById(R.id.triangleBase)
        triangleHeight = findViewById(R.id.triangleHeight)
        trianglePerimeterCard = findViewById(R.id.trianglePerimeterCard)
        triangleSide1 = findViewById(R.id.triangleSide1)
        triangleSide2 = findViewById(R.id.triangleSide2)
        triangleSide3 = findViewById(R.id.triangleSide3)
        resultCard = findViewById(R.id.resultCard)
        result = findViewById(R.id.result)

        // Button calculate
        bntCalculate.setOnClickListener {
            resultCard.visibility = View.GONE
            val geometricSelection:String = geometricSelection()
            val measureSelection:String = measureSelection()

            when(geometricSelection){
                // Circle
                getString(R.string.circle) -> {
                    val radio:Double? = inputRadio.text.toString().toDoubleOrNull()
                    val circle:Circle
                    if(radio != null){
                        circle = Circle(radio)
                        result.text = calculate(circle, measureSelection)
                        resultCard.visibility = View.VISIBLE
                    }
                    else{
                        Toast.makeText(this, "Datos incorrectos!!", Toast.LENGTH_LONG).show()
                    }

                }
                // Rectangle
                getString(R.string.rectangle) -> {
                    val base:Double? = rectangleBase.text.toString().toDoubleOrNull()
                    val height:Double? = rectangleHeight.text.toString().toDoubleOrNull()
                    val rectangle:Rectangle
                    if(base != null && height != null){
                        rectangle = Rectangle(base, height)
                        result.text = calculate(rectangle, measureSelection)
                        resultCard.visibility = View.VISIBLE
                    }
                    else{
                        Toast.makeText(this, "Datos incorrectos!!", Toast.LENGTH_LONG).show()
                    }
                }
                // Triangle
                getString(R.string.triangle) -> {
                    val base:Double? = triangleBase.text.toString().toDoubleOrNull()
                    val height:Double? = triangleHeight.text.toString().toDoubleOrNull()
                    val side1:Double? = triangleSide1.text.toString().toDoubleOrNull()
                    val side2:Double? = triangleSide2.text.toString().toDoubleOrNull()
                    val side3:Double? = triangleSide3.text.toString().toDoubleOrNull()
                    val triangle:Triangle
                    if(base != null && height != null && side1 != null && side2 != null && side3 != null){
                        triangle = Triangle(base, height, side1, side2, side3)
                        result.text = calculate(triangle, measureSelection)
                        resultCard.visibility = View.VISIBLE
                    }
                    else{
                        Toast.makeText(this, "Datos incorrectos!!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        // To show cards for Circle, Rectangle or Triangle
        radioGroupGeometric.setOnCheckedChangeListener { _, checkedId ->
            circleCard.visibility = View.GONE
            triangleAreaCard.visibility = View.GONE
            rectangleCard.visibility = View.GONE
            trianglePerimeterCard.visibility = View.GONE
            resultCard.visibility = View.GONE

            when(checkedId) {
                R.id.radioCircle -> {
                    circleCard.visibility = View.VISIBLE
                }

                R.id.radioRectangle -> {
                    rectangleCard.visibility = View.VISIBLE
                }

                R.id.radioTriangle -> {
                    triangleAreaCard.visibility = View.VISIBLE
                }
            }
        }

        // To show cards for Triangle measure method selection
        radioGroupAP.setOnCheckedChangeListener { _, checkedId ->
            triangleAreaCard.visibility = View.GONE
            trianglePerimeterCard.visibility = View.GONE
            resultCard.visibility = View.GONE

            if(radioGroupGeometric.checkedRadioButtonId == R.id.radioTriangle){
                when(checkedId){
                    R.id.radioArea -> {
                        triangleAreaCard.visibility = View.VISIBLE
                        trianglePerimeterCard.visibility = View.GONE
                    }
                    R.id.radioPerimeter -> {
                        triangleAreaCard.visibility = View.GONE
                        trianglePerimeterCard.visibility = View.VISIBLE
                    }
                }
            }
            else{
                triangleAreaCard.visibility = View.GONE
                trianglePerimeterCard.visibility = View.GONE
                resultCard.visibility = View.GONE
            }

        }
    }



    private fun calculate (form:GeometricForm, measureSelection:String) :String{
        return when(measureSelection){
            getString(R.string.area) -> {
                form.areaCalculator()
            }
            getString(R.string.perimeter) ->{
                form.perimeterCalculator()
            }
            else -> {
                ""
            }
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

    // Geometric Form Selection
    private fun geometricSelection() : String{
        val form = when(radioGroupGeometric.checkedRadioButtonId){
            R.id.radioCircle -> {
                radioCircle.text.toString()
            }
            R.id.radioRectangle -> {
                radioRectangle.text.toString()
            }
            R.id.radioTriangle -> {
                radioTriangle.text.toString()
            }
            else -> {
                "Por favor seleccione una forma geométrica válida"
            }
        }
        return form
    }

    // Measure method selection
    private fun measureSelection() : String{
        val measure = when(radioGroupAP.checkedRadioButtonId){
            R.id.radioArea -> {
                radioArea.text.toString()
            }
            R.id.radioPerimeter -> {
                radioPerimeter.text.toString()
            }
            else -> {
                "Seleccione lo que desea calcular"
            }
        }
        return measure
    }
}