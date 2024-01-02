package com.example.anikash.ui.portfolios

import android.webkit.JavascriptInterface
import android.widget.Toast
import com.example.anikash.MainActivity

/** Instantiate the interface and set the context.  */
class WebAppInterface() {
    var data = ""
    var showPageMethod = { _: Int -> 0 }

    fun storeData(data: String): Int {
        this.data = data
        return 0
    }

    fun createRunnable(showPage: (input: Int) -> Int) {
        this.showPageMethod = showPage
    }
    
    @JavascriptInterface
    fun getDataString(): String {
        System.out.println(data)
        return data
    }

    @JavascriptInterface
    fun showPage(id: Int) {
        showPageMethod(id)
    }
}