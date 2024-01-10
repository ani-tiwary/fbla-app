package com.example.anikash.ui.home

import android.webkit.JavascriptInterface

/** Instantiate the interface and set the context.  */
class WebAppInterface() {
    var data = ""
    fun storeData(data: String): Int {
        this.data = data
        return 0
    }

    @JavascriptInterface
    fun getDataString(): String {
        System.out.println(data)
        return data
    }

}