package com.example.multitool.superheroes.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.multitool.databinding.ActivitySuperHeroesBinding
import com.example.multitool.superheroes.adapters.SuperheroAdapter
import com.example.multitool.superheroes.data.SuperheroesResponse
import com.example.multitool.superheroes.data.SuperheroesServiceAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SuperHeroesActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySuperHeroesBinding
    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding = ActivitySuperHeroesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ImageButtonSearch.setOnClickListener({
            val searchText = binding.EditTextSearch.text.toString()
            searchSuperheroes(searchText)
        })

        adapter = SuperheroAdapter(listOf()) {

        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
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
            val response:Response<SuperheroesResponse> = service.searchByName(query)

            runOnUiThread {
                if (response != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    adapter.updateItems(response.body()?.results)
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }

            }
        }



    }
}