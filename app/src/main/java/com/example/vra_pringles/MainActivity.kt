package com.example.vra_pringles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.vra_pringles.databinding.ActivityMainBinding
import com.example.vra_pringles.menu.DetailMenuActivity
import com.example.vra_pringles.web.ArticleWebActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.btnLogout.setOnClickListener {
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
}
