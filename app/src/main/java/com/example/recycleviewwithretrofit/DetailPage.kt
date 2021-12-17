package com.example.recycleviewwithretrofit

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class DetailPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        var webview = findViewById<WebView>(R.id.webView)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val url= intent.getStringExtra("URL")
        if(url != null){
            webview.settings.javaScriptEnabled = true
            webview.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"
            webview.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    webview.visibility = View.VISIBLE
                }
            }
            webview.loadUrl(url)
        }
    }
}