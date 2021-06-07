package com.example.maybebetter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class Mappage extends AppCompatActivity {

    ImageView imageView;

    ScaleGestureDetector scaleGestureDetector;
    float scaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappage2);

        imageView = findViewById(R.id.imageView);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
    }
    //this detects the touch on the entire view
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scaleGestureDetector.onTouchEvent(event);
    }
    //Sets up the scale factor and applies it to the image
    private class ScaleListener extends  ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= scaleGestureDetector.getScaleFactor();
            imageView.setScaleX(scaleFactor);
            imageView.setScaleY(scaleFactor);
            return true;
        }
    }
}