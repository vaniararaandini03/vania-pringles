package com.example.vra_pringles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vra_pringles.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val name = sharedPref.getString("NAME", "") ?: ""
        val email = sharedPref.getString("EMAIL", "") ?: ""
        val dob = sharedPref.getString("DOB", "") ?: ""
        val gender = sharedPref.getString("GENDER", "") ?: ""
        val username = sharedPref.getString("USERNAME", "") ?: ""
        val password = sharedPref.getString("PASSWORD", "") ?: ""
        val confirmPassword = sharedPref.getString("CONFIRM_PASSWORD", "") ?: ""

        binding.tvValName.text = name
        binding.tvValEmail.text = email
        binding.tvValDob.text = dob
        binding.tvValGender.text = gender
        binding.tvValUsername.text = username
        binding.tvValPassword.text = password
        binding.tvValConfirmPassword.text = confirmPassword

        // Tombol Kembali: Cukup panggil finish() untuk menutup layar verifikasi
        // dan otomatis kembali ke RegisterActivity yang masih tersimpan di backstack.
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Tombol Submit (Lulus Validasi -> Kembali ke Login & Bersihkan Backstack)
        binding.btnSubmit.setOnClickListener {
            if (name.isEmpty() || email.isEmpty() || dob.isEmpty() ||
                gender.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Semua inputan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password dan Confirm Password harus sama!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_LONG).show()

            // Kembali ke LoginActivity dan bersihkan tumpukan Register & Verification
            val intent = Intent(this@VerificationActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}