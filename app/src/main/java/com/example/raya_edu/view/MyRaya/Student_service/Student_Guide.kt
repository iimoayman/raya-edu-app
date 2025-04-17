package com.example.raya_edu.view.MyRaya.Student_service

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.raya_edu.R

class Student_Guide : Fragment(R.layout.student_guide) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = view.findViewById(R.id.progressBar_2)
        val pdfWebView: WebView = view.findViewById(R.id.pdfWebView_2)

        pdfWebView.settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
        }

        pdfWebView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                progressBar.visibility = View.GONE
                super.onReceivedError(view, request, error)
            }
        }

        val googleDriveUrl = "https://drive.google.com/file/d/1eqMIpm9SvQtyCk282gxA7iu5s4G5mtV7/view"
        pdfWebView.loadUrl(googleDriveUrl)
    }
}



