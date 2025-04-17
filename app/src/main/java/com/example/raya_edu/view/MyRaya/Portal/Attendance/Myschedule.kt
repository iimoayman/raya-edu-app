package com.example.raya_edu.view.MyRaya.Portal.Attendance


import com.example.raya_edu.R
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

class MyScheduleFragment : Fragment(R.layout.myschedule) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = view.findViewById(R.id.progressBar_1)
        val pdfWebView: WebView = view.findViewById(R.id.pdfWebView_1)

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

        val googleDriveUrl = "https://drive.google.com/file/d/1BCnnl0HhgRmiD5sgsSRBYXvQG0vjZhtQ/view?fbclid=IwY2xjawIxp7xleHRuA2FlbQIxMAABHbF-VaVZQsmLmfHmbbNu0tRj-xpWBIgA41SY_sJwKBT5nabxGtqUb7MlpQ_aem_Ol5LVPpSkJC5JsN7QvxuNg"
        pdfWebView.loadUrl(googleDriveUrl)
    }
}



