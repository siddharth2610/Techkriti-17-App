package com.suleiman.techkriti.activities;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.suleiman.techkriti.R;

public class Startpage extends NavDrawerActivity {

private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_startpage, mContentFrame);
        webView=(WebView)findViewById(R.id.wbvw);
        webView=(WebView)findViewById(R.id.wbvw);
        webView.setWebViewClient(new MyWeb());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://2016.techkriti.org/schedule");


    }
    private class MyWeb extends WebViewClient
    {
        public boolean shouldOverride(WebView view,String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}
