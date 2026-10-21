package com.example.vra_pringles.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.vra_pringles.ArticleWebActivity
import com.example.vra_pringles.DetailMenuActivity
import com.example.vra_pringles.DeveloperProfileActivity
import com.example.vra_pringles.databinding.FragmentHomeBinding

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

        // 1. More Info & Hero Banner -> DetailMenuActivity
        binding.tvMoreInfo.setOnClickListener {
            startActivity(Intent(requireContext(), DetailMenuActivity::class.java))
        }
        binding.cardBestSeller.setOnClickListener {
            startActivity(Intent(requireContext(), DetailMenuActivity::class.java))
        }

        // 2. See All -> DetailMenuActivity
        binding.tvSeeAll.setOnClickListener {
            startActivity(Intent(requireContext(), DetailMenuActivity::class.java))
        }

        // 3. Iced Americano & Hot Cappucino -> DetailMenuActivity
        binding.cardDetailMenu.setOnClickListener {
            startActivity(Intent(requireContext(), DetailMenuActivity::class.java))
        }
        binding.cardArticleWeb.setOnClickListener {
            startActivity(Intent(requireContext(), DetailMenuActivity::class.java))
        }

        // 4. Menu 3 Garis -> Popup Menu (Pengaturan, Tentang Aplikasi, Keluar)
        binding.btnNavMenu.setOnClickListener { viewMenu ->
            showPopupMenu(viewMenu)
        }
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.menu.add(0, 1, 0, "Pengaturan")
        popup.menu.add(0, 2, 1, "Tentang Aplikasi")
        popup.menu.add(0, 3, 2, "Keluar")

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                1 -> {
                    startActivity(Intent(requireContext(), DeveloperProfileActivity::class.java))
                    true
                }
                2 -> {
                    startActivity(Intent(requireContext(), ArticleWebActivity::class.java))
                    true
                }
                3 -> {
                    requireActivity().finishAffinity()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}