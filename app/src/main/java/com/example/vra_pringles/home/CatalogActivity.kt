package com.example.vra_pringles.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vra_pringles.R
import com.example.vra_pringles.databinding.ActivityCatalogBinding

class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar & Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Katalog Menu Co/Choc"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Tampilkan TabCFragment (yang berisi RecyclerView)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_catalog, TabCFragment())
                .commit()
        }
    }
}