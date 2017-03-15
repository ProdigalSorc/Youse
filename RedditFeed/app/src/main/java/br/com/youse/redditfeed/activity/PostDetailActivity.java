package br.com.youse.redditfeed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.youse.redditfeed.models.Posts;
import br.com.youse.redditfeed.utils.Utility;

public class PostDetailActivity extends AppCompatActivity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Intent intent = getIntent();
        Posts posts = (Posts) intent.getSerializableExtra("posts");

        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(posts.getUrl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!Utility.isConnected(getApplicationContext())) {
            finish();
        }
    }
}
