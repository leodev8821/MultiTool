package com.example.multitool.apcalculator

import kotlin.math.PI
import kotlin.math.pow

interface GeometricForm {

    fun areaCalculator():String

    fun perimeterCalculator():String
}

class Circle (private val radius:Double) : GeometricForm{
    override fun areaCalculator(): String {
        return String.format("%.2f", PI * radius.pow(2)) + " cm2"
    }


    override fun perimeterCalculator():String {
        return String.format("%.2f", 2* PI * radius) + " cm"
    }

}

class Rectangle (private val base:Double, private val height:Double) : GeometricForm{
    override fun areaCalculator(): String {
        return String.format("%.2f", base * height) + " cm2"
    }

    override fun perimeterCalculator(): String {
        return String.format("%.2f", (2 * base) + (2 * height)) + " cm"
    }

}

class Triangle (private val base:Double, private val height:Double, private val side1:Double, private val side2:Double, private val side3:Double) : GeometricForm{
    override fun areaCalculator(): String {
        return String.format("%.2f", (base * height) / 2) + " cm2"
    }

    override fun perimeterCalculator(): String {
        return String.format("%.2f", (side1 + side2 + side3)) + " cm"
    }

}