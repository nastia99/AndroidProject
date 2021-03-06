package com.example.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Class qui gère l'affichage de la webView
 * L'utilisateur est redirigé vers une page de règle se situant sur un git
 */

public class MyWebViewActivity extends AppCompatActivity {

    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_layout);

        String url = getIntent().getStringExtra("url");
        webview = (WebView) findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        WebSettings websettings= webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setPluginState(WebSettings.PluginState.ON);
        webview.loadUrl(url);
    }
}
