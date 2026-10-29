
package com.example.vra_pringles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.vra_pringles.databinding.ActivityDeveloperProfileBinding

class DeveloperProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeveloperProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeveloperProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Atur status switch sesuai preferensi yang tersimpan
        binding.switchDarkMode.isChecked =
            prefs.getBoolean("is_dark_mode", false)

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit()
                .putBoolean("is_dark_mode", isChecked)
                .apply()

            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
