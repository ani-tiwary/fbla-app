package com.example.anikash.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anikash.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var nameField: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textView
        nameField = binding.textField

        // Load the saved name when the app is loaded
        loadNameFromLocalStorage()

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.submitButton.setOnClickListener {
            val enteredName = nameField.text.toString()
            saveNameToLocalStorage(enteredName)
            textView.text = enteredName
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveNameToLocalStorage(name: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("savedName", name)
            apply()
        }
    }

    private fun loadNameFromLocalStorage() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val savedName = sharedPref.getString("savedName", "")
        nameField.setText(savedName)
    }
}
