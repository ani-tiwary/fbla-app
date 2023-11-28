package com.example.anikash.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anikash.MainActivity
import com.example.anikash.databinding.FragmentHomeBinding


// Steps to run the app
// Start the Emulator
// wait for it to load and build while waiting
// then run app
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            var json = (activity as MainActivity).readJSONFromFile()
            System.out.println(json.toString())
            if (json.length() == 0) {
                textView.text = "Lol"
            } else {
                binding.name.text = json.getString("Name")
                binding.occupationText.text = json.getString("Position")
                binding.organization.text = json.getString("Organisation")
                var print_string = json.getString("Name") + '\n' + json.getString("Position") + '\n' + json.getString("Organisation")
                System.out.println(print_string)
                textView.text = print_string
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}