package com.example.multitool.zodiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.zodiapp.adapters.ZodiacAdapter
import com.example.multitool.zodiapp.data.Zodiac

class Zodiapp : AppCompatActivity() {

    var zodiacList:List<Zodiac> = listOf(
        Zodiac.Aries,
        Zodiac.Taurus,
        Zodiac.Gemini,
        Zodiac.Cancer,
        Zodiac.Leo,
        Zodiac.Virgo,
        Zodiac.Libra,
        Zodiac.Scorpio,
        Zodiac.Sagittarius,
        Zodiac.Capricorn,
        Zodiac.Aquarius,
        Zodiac.Pisces
    )

    lateinit var recicleView:RecyclerView
    lateinit var adapter: ZodiacAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zodiapp)

        initView()
    }

    private fun initView() {

        recicleView = findViewById(R.id.recicleView)

        adapter = ZodiacAdapter(zodiacList)

        recicleView.layoutManager = LinearLayoutManager(this)

        recicleView.adapter = adapter
    }
}