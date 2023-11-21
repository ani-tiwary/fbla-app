package com.example.anikash.ui.notifications

import DashboardFragment
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anikash.databinding.FragmentNotificationsBinding
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val dashboardFragment = DashboardFragment()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Retrieve stored text from DashboardFragment
        val applicationContext: Context = requireActivity().applicationContext

        val storedText = dashboardFragment.getStoredText(applicationContext)
        System.out.println(storedText)

        // Now, you can use the storedText as needed
        // For example, set it to a TextView
        binding.textView4.text = storedText

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Sample method to retrieve stored text from SharedPreferences
    fun getStoredText(): String {
        try {
            val fileInputStream: FileInputStream = requireContext().openFileInput("stored_text.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String? = null

            while ({ text = bufferedReader.readLine(); text }() != null) {
                stringBuilder.append(text)
            }

            fileInputStream.close()
            return stringBuilder.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }
}