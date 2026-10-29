package com.example.vra_pringles

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.vra_pringles.databinding.ActivityDetailMenuBinding

class DetailMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Toolbar dari XML sebagai SupportActionBar (Sesuai cara dosen)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Detail Menu"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.cardCalendarBooking.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}