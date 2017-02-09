package com.food2fork.demoapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.food2fork.demoapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {

    private String webURL;
    private Bundle extras;

    @BindView(R.id.webView) WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        extras = getIntent().getExtras();
        webURL = extras.getString("webURL");

        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(webURL);
    }
}
