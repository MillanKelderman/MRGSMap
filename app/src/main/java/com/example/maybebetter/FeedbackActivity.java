package com.example.maybebetter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    ImageButton goback;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        submit = findViewById(R.id.submission);
        submit.setOnClickListener(v -> {
            Toast.makeText(this, "Thanks for the feedback!", Toast.LENGTH_SHORT).show();
        });

        goback = findViewById(R.id.mapreturn);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(FeedbackActivity.this, Mappage.class);
                startActivity(intentLoadNewActivity);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}
