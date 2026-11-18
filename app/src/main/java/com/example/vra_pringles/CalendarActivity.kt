package com.example.vra_pringles

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vra_pringles.databinding.ActivityCalendarBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarBinding
    private var selectedDateStr: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("id-ID"))
        selectedDateStr = dateFormat.format(calendar.time)
        binding.tvSelectedDate.text = "Tanggal dipilih: $selectedDateStr"

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedCal = Calendar.getInstance()
            selectedCal.set(year, month, dayOfMonth)
            selectedDateStr = dateFormat.format(selectedCal.time)
            binding.tvSelectedDate.text = "Tanggal dipilih: $selectedDateStr"
        }

        binding.btnReserve.setOnClickListener {
            Toast.makeText(this, "Reservasi berhasil untuk tanggal: $selectedDateStr", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
