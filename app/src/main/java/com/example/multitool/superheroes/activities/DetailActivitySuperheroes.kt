package com.example.multitool.superheroes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.multitool.R
import com.example.multitool.databinding.ActivityDetailSuperheroesBinding
import com.example.multitool.superheroes.data.Superhero
import com.example.multitool.superheroes.data.SuperheroesServiceAPI
import com.example.multitool.superheroes.utils.RetrofitProvider
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
    }

    private var heroId:String? = null
    private lateinit var superhero:Superhero

    private lateinit var binding:ActivityDetailSuperheroesBinding

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        initActionBar()

        heroId = intent.getStringExtra(EXTRA_ID)
        val heroName = intent.getStringExtra(EXTRA_NAME)
        val heroImage = intent.getStringExtra(EXTRA_IMAGE)

        binding.toolbarLayout.title = heroName
        Picasso.get().load(heroImage).into(binding.photoImageView)

        findSuperHeroById(heroId!!)

    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)

        // Show back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
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

    private fun findSuperHeroById(
        heroId: String
    ){

        val service: SuperheroesServiceAPI = RetrofitProvider.getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val response = service.searchById(heroId)

            runOnUiThread {
                // Modificar UI
                //binding.progress.visibility = View.GONE
                if (response.body() != null) {
                    Log.i("HTTP", "respuesta correcta :)")
                    superhero = response.body()!!
                    loadData()
                } else {
                    Log.i("HTTP", "respuesta erronea :(")
                }
            }
        }
    }

    private fun loadData(){
        // Title
        binding.toolbarLayout.title = superhero.name

        // Stats
        binding.content.valueIntelligenceTextView.text = superhero.powerstats.intelligence
        binding.content.intelligenceProgressBar.setProgress(superhero.powerstats.intelligence.toInt())

        binding.content.valuestrengthTextView.text = superhero.powerstats.strength
        binding.content.strengthProgressBar.setProgress(superhero.powerstats.strength.toInt())

        binding.content.valueSpeedTextView.text = superhero.powerstats.speed
        binding.content.speedProgressBar.setProgress(superhero.powerstats.speed.toInt())

        binding.content.valueDurabilityTextView.text = superhero.powerstats.durability
        binding.content.durabilityProgressBar.setProgress(superhero.powerstats.durability.toInt())

        binding.content.valuePowerTextView.text = superhero.powerstats.power
        binding.content.powerProgressBar.setProgress(superhero.powerstats.power.toInt())

        binding.content.valueCombatTextView.text = superhero.powerstats.combat
        binding.content.combatProgressBar.setProgress(superhero.powerstats.combat.toInt())

        // Biography
        binding.content.valueFullNameTextView.text = superhero.biography.full_name
        binding.content.valueAlterEgosTextView.text = superhero.biography.alter_egos
        binding.content.contentAliases.alias1TextView.text = superhero.biography.aliases.toString()

    }

}