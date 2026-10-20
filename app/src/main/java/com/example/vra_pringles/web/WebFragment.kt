package com.example.vra_pringles.web

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.vra_pringles.R
import com.example.vra_pringles.databinding.ActivityArticleWebBinding

class WebFragment : Fragment() {private var _binding: ActivityArticleWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityArticleWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Konfigurasi WebView agar tidak blank
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://www.instagram.com/jambang_space")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}