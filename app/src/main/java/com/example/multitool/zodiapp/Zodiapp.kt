package com.example.multitool.zodiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.zodiapp.adapters.ZodiacAdapter
import com.example.multitool.zodiapp.data.Zodiac
import com.example.multitool.zodiapp.data.ZodiacProvider

class Zodiapp : AppCompatActivity() {

    private var zodiacList:List<Zodiac> = ZodiacProvider().getZodiacs()
    private lateinit var recicleView:RecyclerView
    private lateinit var adapter: ZodiacAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zodiapp)

        initView()
    }

    private fun initView() {

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recicleView = findViewById(R.id.recicleView)

        adapter = ZodiacAdapter(zodiacList){
            onItemClickListener(it)
        }

        recicleView.layoutManager = LinearLayoutManager(this)

        recicleView.adapter = adapter
    }

    private fun onItemClickListener(position:Int) {
        val zodiac:Zodiac = zodiacList[position]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("HOROSCOPE_ID", zodiac.id)
        startActivity(intent)
        //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_LONG).show()
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
}