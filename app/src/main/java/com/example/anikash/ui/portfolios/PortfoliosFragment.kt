package com.example.anikash.ui.portfolios

import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.webkit.WebViewAssetLoader
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

        val myWebView: WebView = binding.webview
        // Create an unencoded HTML string, then convert the unencoded HTML string into
        // bytes. Encode it with base64 and load the data.
        /*val unencodedHtml =
            "<html><body>'%23' is the percent code for ‘#‘ </body></html>";

        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)*/
        // myWebView.loadData(encodedHtml, "text/html", "base64")
        myWebView.loadUrl("file:///android_asset/index.html")
        myWebView.settings.javaScriptEnabled = true
        var web_app = WebAppInterface()
        web_app.storeData((activity as MainActivity).listPortfolios().toString())
        web_app.createRunnable((activity as MainActivity)::showPage)

        myWebView.addJavascriptInterface(web_app, "Android")


        var portfolios = (activity as MainActivity).listPortfolios()
        System.out.println(portfolios)

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
