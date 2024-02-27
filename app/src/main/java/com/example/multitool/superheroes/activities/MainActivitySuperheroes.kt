package com.example.multitool.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.multitool.R
import com.example.multitool.databinding.ActivitySuperheroesBinding
import com.example.multitool.superheroes.adapters.SuperheroAdapter
import com.example.multitool.superheroes.data.Superhero
import com.example.multitool.superheroes.data.SuperheroesServiceAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivitySuperheroes : AppCompatActivity() {

    private lateinit var binding:ActivitySuperheroesBinding
    private lateinit var adapter: SuperheroAdapter
    private var heroList: List<Superhero> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {

        // Show Back Button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivitySuperheroesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = SuperheroAdapter() {
            onItemClickListener(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.superhero_main_menu, menu)

        initSearchView(menu?.findItem(R.id.menu_search))

        return true
    }

    private fun initSearchView(searchItem: MenuItem?) {
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchSuperheroes(query!!)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun onItemClickListener(position:Int) {
        val hero:Superhero  = heroList[position]

        val intent = Intent(this, DetailActivitySuperheroes::class.java)
        intent.putExtra(DetailActivitySuperheroes.EXTRA_ID, hero.id)
        intent.putExtra(DetailActivitySuperheroes.EXTRA_NAME, hero.name)
        intent.putExtra(DetailActivitySuperheroes.EXTRA_IMAGE, hero.image.url)
        intent.putExtra(DetailActivitySuperheroes.EXTRA_STATS, hero.powerstats)
        startActivity(intent)
    }

    private fun searchSuperheroes(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: SuperheroesServiceAPI = retrofit.create(SuperheroesServiceAPI::class.java)

         // Se hace la Co-Rutina para realizar la query
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val response = service.searchByName(query)

            runOnUiThread {
                // Modificar UI
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    heroList = response.body()?.results.orEmpty().toList()
                    adapter.updateItems(heroList)
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }

            }
        }
    }
}