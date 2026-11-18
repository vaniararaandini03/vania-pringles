package com.example.vra_pringles.tutorial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vra_pringles.LoginActivity
import com.example.vra_pringles.databinding.ActivityTutorialMessageBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)

        binding.btnFinishTutorial.setOnClickListener {
            // Arahkan ke LoginActivity atau tutup halaman tutorial
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}