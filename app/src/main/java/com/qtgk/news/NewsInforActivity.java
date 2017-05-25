package com.qtgk.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class NewsInforActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_infor);
        mWebView = (WebView) findViewById(R.id.webView);
        String url = getIntent().getExtras().getString("URL");
        mWebView.loadUrl(url);
    }
}
