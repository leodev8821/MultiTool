package com.example.multitool.zodiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.multitool.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.multitool.zodiapp.data.Zodiac
import com.example.multitool.zodiapp.data.ZodiacProvider
import com.example.multitool.zodiapp.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "HOROSCOPE_ID"
    }

    private lateinit var session: SessionManager
    private var isFavorite = false
    private lateinit var favoriteImageButton: ImageButton

    private var currentZodiacIndex:Int = -1
    private var zodiacId:String? = null
    private lateinit var zodiac:Zodiac

    private lateinit var zodiacTextView: TextView
    private lateinit var zodiacImageView: ImageView
    private lateinit var zodiacLuckTextView:TextView

    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailzodiac)

        session = SessionManager(this)

        zodiacId = intent.getStringExtra(EXTRA_ID)
        zodiac = ZodiacProvider().getZodiac(zodiacId!!)
        currentZodiacIndex = ZodiacProvider().getZodiacIndex(zodiac)

        initView()

        loadData()
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
        favoriteImageButton = findViewById(R.id.favoriteImageButton)

        progress = findViewById(R.id.progress)

        favoriteImageButton.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                session.setFavoriteZodiac(zodiac.id)
            } else {
                session.setFavoriteZodiac("")
            }
            setFavoriteDrawable()
        }
    }

    private fun loadData() {
        zodiac = ZodiacProvider().getZodiac(currentZodiacIndex)
        isFavorite = zodiac.id == session.getFavoriteZodiac()

        // Set title
        supportActionBar?.setTitle(zodiac.name);
        supportActionBar?.setSubtitle(zodiac.date);

        zodiacTextView.text = getString(zodiac.name)
        zodiacImageView.setImageResource(zodiac.image)

        setFavoriteDrawable()

        getZodiacLuck()
    }

    private fun setFavoriteDrawable () {
        val favDrawableId = if (isFavorite) {
            R.drawable.black_heart_svg
        } else {
            R.drawable.favorite_svg
        }
        favoriteImageButton.setImageResource(favDrawableId)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.zodiapp_detail_menu, menu)
        return true
    }

    // this event will enable the back
    // function to the button on press
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_prev -> {
                if (currentZodiacIndex == 0) {
                    currentZodiacIndex = ZodiacProvider().getZodiacs().size
                }
                currentZodiacIndex--
                loadData()
                return true
            }
            R.id.menu_next -> {
                currentZodiacIndex ++
                if (currentZodiacIndex == ZodiacProvider().getZodiacs().size) {
                    currentZodiacIndex = 0
                }
                loadData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getZodiacLuck() {
        progress.visibility = View.VISIBLE
        zodiacLuckTextView.text = ""

        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = ZodiacProvider().getZodiacLuck(zodiac.id)
            runOnUiThread {
                // Modificar UI
                progress.visibility = View.GONE

                if (result != null){
                    zodiacLuckTextView.text = result
                }
                else{
                    shoErrorDialog()
                }
            }
        }
    }

    private fun shoErrorDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder
            .setTitle(R.string.alert_error_title)
            .setMessage(R.string.alert_error_connection)

            .setPositiveButton(R.string.alert_ok) {dialog, _ -> dialog?.cancel()}

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}