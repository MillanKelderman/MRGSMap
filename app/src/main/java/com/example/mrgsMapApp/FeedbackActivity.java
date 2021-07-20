package com.example.mrgsMapApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Button submit = findViewById(R.id.submission);
        submit.setOnClickListener(v -> Toast.makeText(this, "Thanks for the feedback!", Toast.LENGTH_SHORT).show());

        ImageButton goBack = findViewById(R.id.mapReturn);
        goBack.setOnClickListener(v -> {
            Intent intentLoadNewActivity = new Intent(FeedbackActivity.this, MapPage.class);
            startActivity(intentLoadNewActivity);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
}
