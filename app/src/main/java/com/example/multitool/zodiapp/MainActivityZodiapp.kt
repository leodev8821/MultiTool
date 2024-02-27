package com.example.multitool.zodiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.multitool.R
import com.example.multitool.zodiapp.adapters.ZodiacAdapter
import com.example.multitool.zodiapp.data.Zodiac
import com.example.multitool.zodiapp.data.ZodiacProvider

class MainActivityZodiapp : AppCompatActivity() {

    private var zodiacList:List<Zodiac> = ZodiacProvider().getZodiacs()
    private lateinit var recicleView:RecyclerView
    private lateinit var zodiacAdapter: ZodiacAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zodiapp)

        initView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun initView() {

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recicleView = findViewById(R.id.recicleView)

    }

    private fun loadData() {
        zodiacAdapter = ZodiacAdapter(zodiacList) {
            onItemClickListener(it)
        }
        recicleView.layoutManager = LinearLayoutManager(this)
        recicleView.adapter = zodiacAdapter
    }



    private fun onItemClickListener(position:Int) {
        val zodiac:Zodiac = zodiacList[position]

        val intent = Intent(this, DetailActivityZodiapp::class.java)
        intent.putExtra(DetailActivityZodiapp.EXTRA_ID, zodiac.id)
        startActivity(intent)
        //Toast.makeText(this, getString(horoscope.name), Toast.LENGTH_LONG).show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.zodiapp_main_menu, menu)

        initSearchView(menu?.findItem(R.id.menu_search))

        return true
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

    private fun initSearchView(searchItem: MenuItem?) {
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query.isNullOrEmpty()) {
                        zodiacList = ZodiacProvider().getZodiacs()
                    } else {
                        zodiacList = ZodiacProvider().getZodiacs()
                            .filter { getString(it.name).contains(query, true) }
                    }
                    zodiacAdapter.updateData(zodiacList)
                    return true
                }
            })
        }
    }
}