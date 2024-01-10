package com.example.anikash.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
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

        var full_json = (activity as MainActivity).listPortfolios()
        var json = full_json.get((activity as MainActivity).getCurrentPortfolio())

        val myWebView: WebView = binding.webview
        // Create an unencoded HTML string, then convert the unencoded HTML string into
        // bytes. Encode it with base64 and load the data.
        /*val unencodedHtml =
            "<html><body>'%23' is the percent code for ‘#‘ </body></html>";

        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)*/
        // myWebView.loadData(encodedHtml, "text/html", "base64")
        myWebView.loadUrl("file:///android_asset/portfolio.html")
        myWebView.settings.javaScriptEnabled = true
        var web_app = WebAppInterface()
        web_app.storeData(json.toString())

        myWebView.addJavascriptInterface(web_app, "Android")

        var portfolios = (activity as MainActivity).listPortfolios()
        System.out.println(portfolios)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}