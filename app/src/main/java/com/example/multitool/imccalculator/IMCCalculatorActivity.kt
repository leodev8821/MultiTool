package com.example.multitool.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.multitool.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat
import kotlin.math.pow

class IMCCalculatorActivity : AppCompatActivity() {

    lateinit var inputHeight: TextInputEditText
    lateinit var inputWeight: TextInputEditText
    lateinit var lblResult: TextView
    lateinit var lblAnalysis: TextView
    lateinit var bntCalculate: Button
    lateinit var sliderHeight: Slider
    lateinit var btnPlus: FloatingActionButton
    lateinit var btnSubs: FloatingActionButton
    lateinit var bottomCard: CardView

    var currentHeight:Float = 100.0f
    var currentWeight:Float = 55.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initView()

    }

    private fun initView() {

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        inputHeight = findViewById(R.id.inputHeight)
        inputWeight = findViewById(R.id.inputWeight)
        lblResult = findViewById(R.id.lblResult)
        lblAnalysis = findViewById(R.id.lblAnalysis)
        bntCalculate = findViewById(R.id.bntCalculate)
        sliderHeight = findViewById(R.id.sliderHeight)
        btnPlus = findViewById(R.id.btnPlus)
        btnSubs = findViewById(R.id.btnSubst)
        bottomCard = findViewById(R.id.circleCard)

        sliderHeight.addOnChangeListener{_, value, _ ->
            currentHeight = value
            inputHeight.setText(currentHeight.toString())
        }

        btnPlus.setOnClickListener {
            currentWeight ++
            inputWeight.setText(currentWeight.toString())
        }

        btnSubs.setOnClickListener {
            currentWeight --
            inputWeight.setText(currentWeight.toString())
        }

        bntCalculate.setOnClickListener {

            val height:Float? = inputHeight.text.toString().toFloatOrNull()
            val weight:Float? = inputWeight.text.toString().toFloatOrNull()

            if (height != null && weight != null) {
                calculateIMC(height, weight)

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

    private fun calculateIMC(height:Float, weight:Float) {

        val imc:Float = weight / (height / 100).pow(2)
        val formatter = DecimalFormat("#.##")

        val analysis:String?
        val analysisColor:Int?

        when(imc){
            in 0.0..18.5 -> {
                analysis = getString(R.string.lowWeight)
                analysisColor = ContextCompat.getColor(this, R.color.lowWeight)
            }
            in 18.5..24.9 -> {
                getString(R.string.normalWeight)
                analysis = getString(R.string.normalWeight)
                analysisColor = ContextCompat.getColor(this, R.color.normalWeight)
            }
            in 24.9..26.9 -> {
                analysis = getString(R.string.upWeight1)
                analysisColor = ContextCompat.getColor(this, R.color.upWeight1)
            }
            in 26.9..29.9 -> {
                analysis = getString(R.string.upWeight2)
                analysisColor = ContextCompat.getColor(this, R.color.upWeight2)
            }
            in 29.9..34.9 -> {
                analysis = getString(R.string.overWeight1)
                analysisColor = ContextCompat.getColor(this, R.color.overWeight1)
            }
            in 34.9..39.9 -> {
                analysis = getString(R.string.overWeight2)
                analysisColor = ContextCompat.getColor(this, R.color.overWeight2)
            }
            in 39.9..49.9 -> {
                analysis = getString(R.string.overWeight3)
                analysisColor = ContextCompat.getColor(this, R.color.overWeight3)
            }
            else -> {
                analysis = getString(R.string.overWeight4)
                analysisColor = ContextCompat.getColor(this, R.color.overWeight4)
            }
        }

        lblResult.text = "${getString(R.string.lbl_result)} ${formatter.format(imc)}"
        lblAnalysis.text = analysis
        lblAnalysis.setTextColor(analysisColor)

        //bottomCard.isVisible = true
        bottomCard.visibility = View.VISIBLE

    }
}