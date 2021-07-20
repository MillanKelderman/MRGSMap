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
        //links objects inside the XML to the variables in the method
        ImageButton goBack = findViewById(R.id.mapReturn);
        Button submit = findViewById(R.id.submission);
        submit.setOnClickListener(v -> Toast.makeText(this, "Thanks for the feedback!", Toast.LENGTH_SHORT).show());


        goBack.setOnClickListener(v -> { //listens if the button 'goBack' has been clicked
            Intent intentLoadNewActivity = new Intent(FeedbackActivity.this, MapPage.class); //sends users to MapPage screen
            startActivity(intentLoadNewActivity);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); //animation for smoother transition
        });
    }
}
