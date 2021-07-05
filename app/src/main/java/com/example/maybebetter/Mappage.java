package com.example.maybebetter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;
import java.util.Collections;


public class Mappage extends AppCompatActivity {

    Button togglebutton;
    ImageView imageView;

    Spinner spinner;


    SubsamplingScaleImageView mapview;
    SubsamplingScaleImageView maplabelview;


    TextView specifications;
    boolean[] selectedchoice;
    ArrayList<Integer> optionlist = new ArrayList<>();
    String[] optionArray = {"", "A block", "B block", "C block", "D Block", "E block", "G block", "H block", "M Block", "P block",  "R block", "S block", "T Block"};


    //This is old code, used initally to zoom in and out of the map
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
        //To open the labels image ontop of the mapview
        maplabelview = findViewById(R.id.maplabelview);
        spinner = findViewById(R.id.options_spinner);

        ArrayAdapter arrayAdapter=new ArrayAdapter(Mappage.this,android.R.layout.simple_dropdown_item_1line,optionArray);
        spinner.setAdapter((arrayAdapter));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (optionArray[0].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.fullmap));
                }else if (optionArray[1].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.a_block));
                } else if (optionArray[2].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.b_block));
                } else if (optionArray[3].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.c_block));
                }else if(optionArray[4].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.d_block));
                }else if(optionArray[5].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.e_block));
                }else if(optionArray[6].equals(spinner.getItemAtPosition(i).toString())){
                    mapview.setImage(ImageSource.resource(R.drawable.g_block));
                }else if(optionArray[7].equals(spinner.getItemAtPosition(i).toString())){
                    mapview.setImage(ImageSource.resource(R.drawable.h_block));
                }else if(optionArray[8].equals(spinner.getItemAtPosition(i).toString())){
                    mapview.setImage(ImageSource.resource(R.drawable.m_block));
                }else if(optionArray[9].equals(spinner.getItemAtPosition(i).toString())){
                    mapview.setImage(ImageSource.resource(R.drawable.p_block));
                }else if(optionArray[10].equals(spinner.getItemAtPosition(i).toString())){
                    mapview.setImage(ImageSource.resource(R.drawable.r_block));
                }else if(optionArray[11].equals(spinner.getItemAtPosition(i).toString())){
                    mapview.setImage(ImageSource.resource(R.drawable.s_block));
                }else if(optionArray[12].equals(spinner.getItemAtPosition(i).toString())) {
                    mapview.setImage(ImageSource.resource(R.drawable.t_block));
                }

                }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mapview.setImage(ImageSource.resource(R.drawable.fullmap));
            }
        });


        //call the kamar image
        kamarimagebutton = findViewById(R.id.kamar);
        kamarimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(Mappage.this, KamarPage.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }

    public void OnCustomToggleClick(View view) {
        maplabelview.setImage(ImageSource.resource(R.drawable.labels));
    }
}





/*imageView = (ImageView) findViewById(R.id.frontfieldimg);*/
   /*     togglebutton = findViewById(R.id.frontfieldtoggle);

        togglebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.INVISIBLE)
                    imageView.setVisibility(View.VISIBLE);
                else
                    imageView.setVisibility(View.INVISIBLE);
            }
        });*/

       /* //assign the variable for dropdown spinner
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

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //string builder
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
                builder.show();*/
       /*     }
        });


    }
}*/
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
