package com.example.maybebetter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class KamarPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_page);

        WebView myWebView = (WebView) findViewById(R.id.kamarwebview);
        myWebView.loadUrl("https://kamarportal.mrgs.school.nz/index.php/home");
    }
}