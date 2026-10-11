package com.example.vra_pringles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.vra_pringles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Klik Tombol 3 Garis -> Muncul PopUp Menu (Profile & Logout)
        binding.btnMenu.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add("Developer Profile")
            popup.menu.add("Logout")

            popup.setOnMenuItemClickListener { item ->
                when (item.title) {
                    "Developer Profile" -> {
                        val intent = Intent(this, DeveloperProfileActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    "Logout" -> {
                        showLogoutDialog()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Navigasi ke DetailMenuActivity
        binding.cardDetailMenu.setOnClickListener {
            val intent = Intent(this, DetailMenuActivity::class.java)
            startActivity(intent)
        }

        // Navigasi ke ArticleWebActivity
        binding.cardArticleWeb.setOnClickListener {
            val intent = Intent(this, ArticleWebActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { dialog, _ ->
                val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                dialog.dismiss()

                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}