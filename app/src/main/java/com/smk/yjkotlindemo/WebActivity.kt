package com.smk.yjkotlindemo

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import org.jetbrains.anko.find

/**
 * Created on 2019/4/16 15:55
 * .
 *
 * @author yj
 * @org 浙江房超信息科技有限公司
 */
class WebActivity : AppCompatActivity() {

    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        var webView: WebView = find(R.id.web_view)
        progressBar = find(R.id.web_progressBar)

        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar?.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar?.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }
        })

        webView.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar?.progress = newProgress
                super.onProgressChanged(view, newProgress)
            }
        })

        webView.loadUrl(intent.getStringExtra("url"))
    }
}