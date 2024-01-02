package com.example.anikash.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anikash.MainActivity
import com.example.anikash.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
            var full_json = (activity as MainActivity).listPortfolios()
            var json = full_json.get((activity as MainActivity).getCurrentPortfolio())

            println(json.toString())
            if (json.length() == 0) {
            } else {
                binding.name.text = json.getString("Name")
                binding.occupationText.text = json.getString("Position")

                try {
                    binding.organization.text = json.getString("Organization")
                    binding.schoolText.text = json.getString("Education")
                    binding.skillsText.text = json.getString("Skills")
                    binding.honorsText.text = json.getString("Honors")
                    binding.referencesText.text = json.getString("References")
                } catch (e: Exception) {
                    println(e)
                }


                var print_string = json.getString("Name") + '\n' + json.getString("Position") + '\n' + json.getString("Organization")
                println(print_string)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}