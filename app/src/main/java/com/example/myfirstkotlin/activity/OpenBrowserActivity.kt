package com.example.myfirstkotlin.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import com.example.myfirstkotlin.R

class OpenBrowserActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_browser)
        //toolbarを表示
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Google検索窓を埋め込み
        val myWebView = findViewById<WebView>(R.id.webView)
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl("https://www.google.com/")
    }
}