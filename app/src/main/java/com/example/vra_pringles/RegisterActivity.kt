package com.example.vra_pringles

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vra_pringles.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var selectedDob: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol Kembali ke Login
        binding.btnBackToLogin.setOnClickListener {
            finish() // Menutup RegisterActivity dan kembali ke LoginActivity
        }

        // Setup DatePicker
        binding.etDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, y, m, d ->
                selectedDob = "$d/${m + 1}/$y"
                binding.etDob.setText(selectedDob)
            }, year, month, day)

            datePickerDialog.show()
        }

        // Tombol Selanjutnya
        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            val selectedRadioId = binding.rgGender.checkedRadioButtonId
            val gender = if (selectedRadioId != -1) {
                findViewById<RadioButton>(selectedRadioId).text.toString()
            } else ""

            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("NAME", name)
            editor.putString("EMAIL", email)
            editor.putString("DOB", selectedDob)
            editor.putString("GENDER", gender)
            editor.putString("USERNAME", username)
            editor.putString("PASSWORD", password)
            editor.putString("CONFIRM_PASSWORD", confirmPassword)
            editor.apply()

            Toast.makeText(this, "Data berhasil disimpan sementara", Toast.LENGTH_SHORT).show()

            // Cukup buka VerificationActivity (JANGAN panggil finish())
            val intent = Intent(this@RegisterActivity, VerificationActivity::class.java)
            startActivity(intent)
        }
    }
}