package com.example.vra_pringles.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.vra_pringles.LoginActivity
import com.example.vra_pringles.R
import com.example.vra_pringles.databinding.FragmentHomeBinding
import com.example.vra_pringles.tutorial.TutorialActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Atur warna ikon titik tiga (overflow icon) menjadi cokelat tua agar kontras dengan background krem
        binding.homeToolbar.overflowIcon?.setTint(
            ContextCompat.getColor(requireContext(), R.color.choco_dark)
        )

        // Tangani klik item di dalam Option Menu (titik tiga)
        binding.homeToolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_tutorial -> {
                    startActivity(Intent(requireContext(), TutorialActivity::class.java))
                    true
                }
                R.id.action_logout -> {
                    showLogoutDialog()
                    true
                }
                else -> false
            }
        }

        // Memuat foto kopi & cokelat asli menggunakan Glide
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5?w=600")
            .into(binding.imgBannerDrink)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=500")
            .into(binding.imgGrid1)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=500")
            .into(binding.imgGrid2)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1572442388796-11668a67e53d?w=500")
            .into(binding.imgGrid3)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1515823064-d6e0c04616a7?w=500")
            .into(binding.imgGrid4)

        // Tombol & "See all" menuju Katalog RecyclerView
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), CatalogActivity::class.java))
        }
        binding.tvSeeAll.setOnClickListener {
            startActivity(Intent(requireContext(), CatalogActivity::class.java))
        }

        // Aksi klik pada item Grid Home
        binding.cardMenu1.setOnClickListener {
            Toast.makeText(requireContext(), "Signature Coffee dipilih", Toast.LENGTH_SHORT).show()
        }
        binding.cardMenu2.setOnClickListener {
            Toast.makeText(requireContext(), "Dark Choco dipilih", Toast.LENGTH_SHORT).show()
        }
        binding.cardMenu3.setOnClickListener {
            Toast.makeText(requireContext(), "Caramel Latte dipilih", Toast.LENGTH_SHORT).show()
        }
        binding.cardMenu4.setOnClickListener {
            Toast.makeText(requireContext(), "Matcha Choco dipilih", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari akun?")
            .setPositiveButton("Ya, Keluar") { _, _ ->
                val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().putBoolean("isLogin", false).apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}