package com.example.vra_pringles

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.example.vra_pringles.databinding.ActivityBaseBinding
import com.example.vra_pringles.home.HomeFragment
import com.example.vra_pringles.menu.MenuFragment
import com.example.vra_pringles.profile.ProfileFragment
import com.example.vra_pringles.web.WebFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Membuat layout ditarik penuh hingga ke balik Status Bar & Navigation Bar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan ActionBar/Toolbar bawaan
        supportActionBar?.hide()

        // Buat Status Bar transparan total agar gambar background menyatu penuh
        window.statusBarColor = Color.TRANSPARENT

        // Fragment default saat aplikasi pertama kali dibuka
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.menu -> {
                    replaceFragment(MenuFragment())
                    true
                }
                R.id.web -> {
                    replaceFragment(WebFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}