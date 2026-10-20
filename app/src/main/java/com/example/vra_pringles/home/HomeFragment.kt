package com.example.vra_pringles.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vra_pringles.DetailMenuActivity
import com.example.vra_pringles.ArticleWebActivity
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

        // Event click untuk kartu Detail Menu
        binding.cardDetailMenu.setOnClickListener {
            val intent = Intent(requireContext(), DetailMenuActivity::class.java)
            startActivity(intent)
        }

        // Event click untuk kartu Seputar Kafe
        binding.cardArticleWeb.setOnClickListener {
            val intent = Intent(requireContext(), ArticleWebActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}