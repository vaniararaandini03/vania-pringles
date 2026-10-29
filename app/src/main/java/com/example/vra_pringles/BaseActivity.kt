
package com.example.vra_pringles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.vra_pringles.databinding.ActivityBaseBinding
import com.example.vra_pringles.home.HomeFragment
import com.example.vra_pringles.menu.MenuFragment
import com.example.vra_pringles.profile.ProfileFragment
import com.example.vra_pringles.web.WebFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // Terapkan preferensi tema sebelum membuat tampilan
        val prefs = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isDarkMode = prefs.getBoolean("is_dark_mode", false)

        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        )

        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
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
