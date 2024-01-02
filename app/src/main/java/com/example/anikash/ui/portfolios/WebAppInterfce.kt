package com.example.anikash.ui.portfolios

import android.webkit.JavascriptInterface
import android.widget.Toast

/** Instantiate the interface and set the context.  */
class WebAppInterface() {

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun showToast(toast: String) {
        System.out.println("toast")
    }
}