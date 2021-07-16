package com.example.maybebetter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

public class KamarPage extends AppCompatActivity {
    ImageButton goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_page);

        WebView myWebView = (WebView) findViewById(R.id.kamarwebview);
        myWebView.loadUrl("https://kamarportal.mrgs.school.nz/index.php/home");

        goback = findViewById(R.id.mapreturn);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(KamarPage.this, Mappage.class);
                startActivity(intentLoadNewActivity);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
