package com.example.insanelywrongmechanics.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.insanelywrongmechanics.R
import com.example.insanelywrongmechanics.gameslist.GamePageActivity
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity: AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webview)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                isLoading(true)
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                isLoading(false)
                super.onPageFinished(view, url)
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.settings.defaultTextEncodingName = "utf-8"
        webView.loadUrl(intent.getStringExtra(GamePageActivity.LINK))
    }

    fun isLoading(showProgressBar: Boolean) {

        //Récupération du cercle de chargement
        val loadingBar = findViewById<ProgressBar>(R.id.progressBar)

        if(showProgressBar) {
            loadingBar.visibility = View.VISIBLE
        } else {
            loadingBar.visibility = View.GONE
        }
    }
}