package com.example.multitool.zodiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.multitool.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.multitool.zodiapp.data.Zodiac
import com.example.multitool.zodiapp.data.ZodiacProvider

class DetailActivity : AppCompatActivity() {
    private var zodiacId:String? = null
    private lateinit var zodiac:Zodiac

    private lateinit var zodiacTextView: TextView
    private lateinit var zodiacImageView: ImageView
    private lateinit var zodiacLuckTextView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        zodiacId = intent.getStringExtra("HOROSCOPE_ID")
        zodiac = ZodiacProvider().getZodiac(zodiacId!!)

        initView()

        getHoroscopeLuck()
    }

    private fun initView() {
        zodiacTextView = findViewById(R.id.zodiacTextView)
        zodiacImageView = findViewById(R.id.zodiacImageView)
        zodiacLuckTextView = findViewById(R.id.zodiacLuckTextView)

        zodiacTextView.text = getString(zodiac.name)
        zodiacImageView.setImageResource(zodiac.image)
    }

    private fun getHoroscopeLuck() {
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = ZodiacProvider().getZodiacLuck(zodiac.id)
            runOnUiThread {
                // Modificar UI
                zodiacLuckTextView.text = result
            }
        }
    }
}