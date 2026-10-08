package com.example.vra_pringles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.vra_pringles.databinding.ActivityMainBinding
import com.example.vra_pringles.menu.DetailMenuActivity
import com.example.vra_pringles.web.ArticleWebActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Membuat layout ditarik full melewati Status Bar & Navigation Bar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan ActionBar/Toolbar
        supportActionBar?.hide()

        // Buat Status Bar transparan agar gambar background menyatu penuh
        window.statusBarColor = android.graphics.Color.TRANSPARENT

        // Navigasi ke DetailMenuActivity
        binding.btnDetailMenu.setOnClickListener {
            val intent = Intent(this, DetailMenuActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke ArticleWebActivity
        binding.btnArticleWeb.setOnClickListener {
            val intent = Intent(this, ArticleWebActivity::class.java)
            startActivity(intent)
        }
    }
}