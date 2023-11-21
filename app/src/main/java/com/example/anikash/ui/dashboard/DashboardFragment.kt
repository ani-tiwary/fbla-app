import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anikash.databinding.FragmentDashboardBinding
import com.example.anikash.ui.dashboard.DashboardViewModel
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    // Key for storing and retrieving the text in SharedPreferences
    private val FILE_NAME = "stored_text.txt"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textView

        // Explicitly specify the type when getting the ViewModel
        val dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.submitButton.setOnClickListener {
            // Store the text in SharedPreferences

            val textToStore = binding.textField.text.toString()
            val applicationContext: Context = requireActivity().applicationContext

            saveTextToFile(applicationContext, textToStore)
            binding.submitButton.text = getStoredText(applicationContext)

            Toast.makeText(applicationContext, "Wrote to file", Toast.LENGTH_SHORT).show()
        }

        val applicationContext: Context = requireActivity().applicationContext
        binding.textView3.text = getStoredText(applicationContext)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Crete the save to file function
    fun saveTextToFile(context: Context, text: String) {
        try {
            val fileOutputStream: FileOutputStream = File(context.filesDir, FILE_NAME).outputStream()
            fileOutputStream.write(text.toByteArray())
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // create the get stored text function with
    fun getStoredText(context: Context): String {
        try {
            val fileInputStream: FileInputStream = File(context.filesDir, FILE_NAME).inputStream()
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
