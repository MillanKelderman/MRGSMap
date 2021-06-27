package com.example.maybebetter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;
import java.util.Collections;


public class Mappage extends AppCompatActivity {
    SubsamplingScaleImageView mapview;
    TextView specifications;
    boolean[] selectedchoice;
    ArrayList<Integer> optionlist = new ArrayList<>();
    String[] optionArray = {"A block","B block", "C block", "Front Field"}; /*"E block", "G block", "H block", "P block", "S block", "R block"};*/

//    ImageView imageView;
//
//    ScaleGestureDetector scaleGestureDetector;
//    float scaleFactor = 1.0f;

    //Open KamarActivity
    ImageButton kamarimagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappage2);
        //To be able to maneuver the map
        mapview = findViewById(R.id.mapview);
        mapview.setImage(ImageSource.resource(R.drawable.fullmap));

        kamarimagebutton = findViewById(R.id.kamar);
        kamarimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Mappage.this, KamarPage.class);
                startActivity(intentLoadNewActivity);
            }
        });

        //assign the variable for dropdown spinner
        specifications = findViewById(R.id.dropdown_choice);

        //Initialise selected choice array
        selectedchoice = new boolean[optionArray.length];

        specifications.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        Mappage.this
                );
                //Set Title
                builder.setTitle("Choose Location");
                //Set dialog no cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(optionArray, selectedchoice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        //Check Condition
                        if (b){
                            //When check box is selected add position in list
                            optionlist.add(i);
                            //sort option list
                            Collections.sort(optionlist);

                        }else {
                            //When Checkbox is unselected remove position from option list
                            optionlist.remove(i);
                        }
                        if ("Front Field");
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //string builder thingy
                        StringBuilder stringBuilder = new StringBuilder();
                        //Use for Loop
                        for (int j=0; j<optionlist.size(); j++){
                            //Concat array value
                            stringBuilder.append(optionArray[optionlist.get(j)]);
                            //condition Check
                            if (j != optionlist.size()-1){
                                //when J value does not equal to option list size -1
                                stringBuilder.append(", ");

                            }
                        }
                        //Set Text on text view
                        specifications.setText(stringBuilder.toString());

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Dismiss Dialog
                        dialogInterface.dismiss();

                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Use for loop
                        for (int j=0; j<selectedchoice.length; j++){
                            selectedchoice[j] = false;
                            // clear option list
                            optionlist.clear();
                            //Clear text view vlaue
                            specifications.setText("");
                        }
                    }
                });
                //show Dialog
                builder.show();
            }
        });


    }
}
       /* imageView = findViewById(R.id.imageView);

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
            return true;*/
