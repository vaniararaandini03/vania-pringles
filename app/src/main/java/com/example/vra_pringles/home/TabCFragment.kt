package com.example.vra_pringles.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vra_pringles.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel("Iced Americano", "Rp 20.000", "https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5?w=500"),
        ProductModel("Iced Coffee Choco Hazelnut", "Rp 24.000", "https://images.unsplash.com/photo-1461023058943-07fcbe16d735?w=500"),
        ProductModel("Hot Cappuccino", "Rp 22.000", "https://images.unsplash.com/photo-1572442388796-11668a67e53d?w=500"),
        ProductModel("Signature Dark Chocolate", "Rp 25.000", "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=500"),
        ProductModel("Caramel Macchiato", "Rp 27.000", "https://images.unsplash.com/photo-1485808191679-5f86510681a2?w=500"),
        ProductModel("Choco Mint Frappe", "Rp 28.000", "https://images.unsplash.com/photo-1579888944880-d98341245702?w=500"),
        ProductModel("Matcha Choco Fusion", "Rp 26.000", "https://images.unsplash.com/photo-1515823064-d6e0c04616a7?w=500"),
        ProductModel("Mocha Latte Classic", "Rp 24.000", "https://images.unsplash.com/photo-1578314675249-a6910f80cc4e?w=500")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Anda memilih ${selectedItem.name} (${selectedItem.price})",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}