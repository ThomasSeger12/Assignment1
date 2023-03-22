package com.example.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebLookup extends Activity {
    private EditText editURL;
    private Button btnSearch;
    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        editURL = (EditText) findViewById(R.id.editURL);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        webView = (WebView) findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        // when the button is clicked, send to URL
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editURL.getText().toString());
            }
        });

        // if enter is clicked, send to URL
        btnSearch.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    webView.loadUrl(editURL.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }
}
