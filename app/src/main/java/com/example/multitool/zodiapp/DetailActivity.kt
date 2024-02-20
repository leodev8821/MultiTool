package com.example.multitool.zodiapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

    // To listen the item selected in a menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.opt1 ->{
                Toast.makeText(this, "He pulsado Refresh", Toast.LENGTH_LONG).show()
            }
            R.id.opt2 ->{
                Toast.makeText(this, "He pulsado Settings", Toast.LENGTH_LONG).show()
            }
            R.id.opt3 ->{
                Toast.makeText(this, getString(R.string.toastAbout), Toast.LENGTH_LONG).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.zodiac_menu, menu)
        return true
    }

    private fun initView() {

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Show title in ActionBar
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setTitle(zodiac.name)
        supportActionBar?.setSubtitle(zodiac.date)

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