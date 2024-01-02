package com.example.anikash.ui.portfolios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anikash.MainActivity
import com.example.anikash.databinding.FragmentPortfoliosBinding
import com.example.anikash.ui.home.HomeViewModel

class PortfoliosFragment : Fragment() {
    private var _binding: FragmentPortfoliosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentPortfoliosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var portfolios = (activity as MainActivity).listPortfolios()
        System.out.println(portfolios)
        binding.textView.text = portfolios.toString()
        // add portfolios to recycler view
        // binding.textHome.text = homeViewModel.text.value
        // binding.portfoliosRecycler.adapter = PortfolioAdapter(portfolios)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
