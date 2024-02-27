package com.example.multitool.superheroes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.multitool.R
import com.example.multitool.databinding.ActivityDetailSuperheroesBinding
import com.example.multitool.superheroes.data.Superhero
import com.example.multitool.superheroes.data.SuperheroesServiceAPI
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivitySuperheroes : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "HERO_ID"
        const val EXTRA_NAME = "HERO_NAME"
        const val EXTRA_IMAGE = "EXTRA_IMAGE"
        const val EXTRA_STATS = "EXTRA_STATS"
    }

    private var heroId:String? = null
    private lateinit var superhero:Superhero

    private lateinit var binding:ActivityDetailSuperheroesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_superheroes)

        initView()
    }

    private fun initView() {
        binding = ActivityDetailSuperheroesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        heroId = intent.getStringExtra(EXTRA_ID)
        var heroName = intent.getStringExtra(EXTRA_NAME)
        var heroImage = intent.getStringExtra(EXTRA_IMAGE)
        var powerStats= arrayOf(intent.getStringExtra(EXTRA_STATS))

        binding.toolbarLayout.title = heroName
        Picasso.get().load(heroImage).into(binding.photoImageView)
        binding.powerstatsTextView.text = powerStats.toString()

        findSuperHeroById(heroId!!)


    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)

        // Show back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    private fun findSuperHeroById(heroId: String) {
        //binding.progress.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: SuperheroesServiceAPI = retrofit.create(SuperheroesServiceAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val response = service.searchById(heroId)

            runOnUiThread {
                // Modificar UI
                //binding.progress.visibility = View.GONE
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    superhero = response.body()!!
                    binding.toolbarLayout.title = superhero.name
                    binding.powerstatsTextView.text = superhero.powerstats.toString()
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }
        }
    }

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