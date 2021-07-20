package com.example.mrgsMapApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;

public class KamarPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_page);

        WebView myWebView = findViewById(R.id.kamarWebView);
        myWebView.loadUrl("https://kamarportal.mrgs.school.nz/index.php/home");

        ImageButton goBack = findViewById(R.id.mapReturn);
        goBack.setOnClickListener(v -> {
            Intent intentLoadNewActivity = new Intent(KamarPage.this, MapPage.class);
            startActivity(intentLoadNewActivity);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}
