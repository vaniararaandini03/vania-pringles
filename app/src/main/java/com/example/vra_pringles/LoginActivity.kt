package com.example.vra_pringles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vra_pringles.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inflate Layout & Set Content View
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Insets Listener untuk Tampilan Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SharedPreferences sesuai nama preference project kamu
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // 3. Navigasi menuju RegisterActivity (Soal 1)
        // Pastikan ID 'btnToRegister' atau 'tvRegister' disesuaikan dengan ID tombol registrasi di XML kamu
        binding.tvRegister?.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 4. Logika Tombol Login (Soal 2)
        binding.btnLogin.setOnClickListener {
            val usernameInput = binding.textUsername.text.toString().trim()
            val passwordInput = binding.textPw.text.toString().trim()

            // Ambil data user yang tersimpan di SharedPreferences saat registrasi
            val savedUsername = sharedPref.getString("USERNAME", "") ?: ""
            val savedPassword = sharedPref.getString("PASSWORD", "") ?: ""

            // Evaluasi 2 Aturan Login:
            // Rule 1: Jika username = password dan tidak kosong (Aturan Praktikum)
            val isRule1Valid = usernameInput.isNotEmpty() && usernameInput == passwordInput

            // Rule 2: Jika username & password cocok dengan data di SharedPreferences
            val isRule2Valid = savedUsername.isNotEmpty() &&
                    savedPassword.isNotEmpty() &&
                    usernameInput == savedUsername &&
                    passwordInput == savedPassword

            if (isRule1Valid || isRule2Valid) {
                // Simpan status session login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", usernameInput)
                editor.apply()

                Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                // Pindah ke BaseActivity (halaman utama dengan Bottom Navigation)
                val intent = Intent(this@LoginActivity, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Tampilkan Alert jika gagal
                AlertDialog.Builder(this)
                    .setTitle("Peringatan")
                    .setMessage("Username atau password salah. Silahkan coba lagi.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}