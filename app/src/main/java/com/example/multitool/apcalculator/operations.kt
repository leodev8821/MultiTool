package com.example.multitool.apcalculator

import kotlin.math.PI
import kotlin.math.pow

interface GeometricForm {

    fun areaCalculator():String

    fun perimeterCalculator():String
}

class Circle (private val radius:Double) : GeometricForm{
    override fun areaCalculator():String{
        val area = String.format("%.2f", PI * radius.pow(2))
        return area
    }

    override fun perimeterCalculator():String {
        val perimeter = String.format("%.2f", 2* PI * radius)
        return perimeter
    }

}

class Rectangle (private val base:Double, private val altura:Double) : GeometricForm{
    override fun areaCalculator():String {
        val area = String.format("%.2f", base * altura)
        return area
    }

    override fun perimeterCalculator():String {
        val perimeter = String.format("%.2f", (2*base)+(2*altura))
        return perimeter
    }

}

class Triangle (private val base:Double, private val altura:Double, private val lado1:Double, private val lado2:Double, private val lado3:Double) : GeometricForm{
    override fun areaCalculator():String {
        val area = String.format("%.2f", (base * altura) / 2)
        return area
    }

    override fun perimeterCalculator():String {
        val perimeter = String.format("%.2f", (lado1+lado2+lado3))
        return perimeter
    }

}