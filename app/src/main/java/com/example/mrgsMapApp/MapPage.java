package com.example.mrgsMapApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.Arrays;


@SuppressWarnings("unused")
public class MapPage extends AppCompatActivity {

    private Spinner spinner;
    private SubsamplingScaleImageView mapView;
    private SubsamplingScaleImageView mapLabelView;
    private final String[] optionArray = {"Options", "A block", "B block", "C block", "D Block", "E block",
            "G block", "H block", "M Block", "P block", "R block", "S block", "T Block"};

    SavingToFile savingToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappage2);

        savingToFile = new SavingToFile(this);

        //To be able to maneuver the map
        mapView = findViewById(R.id.map_view);
        //To open the labels image on top of the map_view
        mapLabelView = findViewById(R.id.mapLabelView);
        //Sets the image for the label view
        mapLabelView.setImage(ImageSource.resource(R.drawable.labels));
        mapLabelView.setVisibility(View.INVISIBLE);
        //calls the Spinner from XML file
        spinner = findViewById(R.id.options_spinner);
        //
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MapPage.this, android.R.layout.simple_dropdown_item_1line, optionArray);
        spinner.setAdapter((arrayAdapter));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (optionArray[0].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.fullmap)); //if nothing is selected have the original image 'full_map' showing
                } else if (optionArray[1].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.a_block));//when something does get selected, it goes down the list.
                } else if (optionArray[2].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.b_block));
                } else if (optionArray[3].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.c_block));
                } else if (optionArray[4].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.d_block));
                } else if (optionArray[5].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.e_block));
                } else if (optionArray[6].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.g_block));
                } else if (optionArray[7].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.h_block));
                } else if (optionArray[8].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.m_block));
                } else if (optionArray[9].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.p_block));
                } else if (optionArray[10].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.r_block));
                } else if (optionArray[11].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.s_block));
                } else if (optionArray[12].equals(spinner.getItemAtPosition(i).toString())) {
                    mapView.setImage(ImageSource.resource(R.drawable.t_block));
                }
            }
            //When the spinner is clicked but nothing is chosen
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mapView.setImage(ImageSource.resource(R.drawable.fullmap));
            }
        });

        //call the Kamar image
        //Open KamarActivity
        ImageButton kamarImageButton = findViewById(R.id.kamar);
        kamarImageButton.setOnClickListener(v -> {
            Intent intentLoadNewActivity = new Intent(MapPage.this, KamarPage.class);
            startActivity(intentLoadNewActivity);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); //Animation going between the two activities


        });

        //Open Feedback Activity
        ImageButton feedback = findViewById(R.id.FeedbackButton);
        feedback.setOnClickListener(v -> {
            Intent intentLoadNewActivity = new Intent(MapPage.this, FeedbackActivity.class);
            startActivity(intentLoadNewActivity);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    public void timetableChecker(View view) throws InterruptedException {
        timetableChecker();
    }

    private void timetableChecker() throws InterruptedException {
        String filename = ((GlobalVariable) this.getApplication()).getGlobalVariable();
        String[] fileData = Arrays.toString(savingToFile.readFile(filename).split("\n")).split(",");
        String[][] classAndId = new String[fileData.length - 1][];
        for (int i = 1; i < fileData.length; i++) {
            String[] values = fileData[i].split(" ");

            if (values[2].contains("]")) {
                values[2] = values[2].replace("]", "");
            }
            classAndId[i - 1] = new String[]{values[1], values[2]};
        }
        Toast.makeText(this, classAndId[0][0], Toast.LENGTH_SHORT).show();
        whichMap(classAndId[0][1]);
        Thread.sleep(2000);
        Toast.makeText(this, classAndId[1][0], Toast.LENGTH_SHORT).show();
        whichMap(classAndId[1][1]);
        Thread.sleep(2000);
        Toast.makeText(this, classAndId[2][0], Toast.LENGTH_SHORT).show();
        whichMap(classAndId[2][1]);
        Thread.sleep(2000);
        Toast.makeText(this, classAndId[3][0], Toast.LENGTH_SHORT).show();
        whichMap(classAndId[3][1]);
        Thread.sleep(2000);
        Toast.makeText(this, classAndId[4][0], Toast.LENGTH_SHORT).show();
        whichMap(classAndId[4][1]);
    }

    private void whichMap(String classBlock) {
        switch (classBlock) {
            case "A_block": //When the spinner in the timetable.java file has been chosen, choose one of these.
                mapView.setImage(ImageSource.resource(R.drawable.a_block));
                break;
            case "B_block":
                mapView.setImage(ImageSource.resource(R.drawable.b_block));
                break;
            case "C_block":
                mapView.setImage(ImageSource.resource(R.drawable.c_block));
                break;
            case "D_block":
                mapView.setImage(ImageSource.resource(R.drawable.d_block));
                break;
            case "E_block":
                mapView.setImage(ImageSource.resource(R.drawable.e_block));
                break;
            case "G_block":
                mapView.setImage(ImageSource.resource(R.drawable.g_block));
                break;
            case "H_block":
                mapView.setImage(ImageSource.resource(R.drawable.h_block));
                break;
            case "M_block":
                mapView.setImage(ImageSource.resource(R.drawable.m_block));
                break;
            case "P_block":
                mapView.setImage(ImageSource.resource(R.drawable.p_block));
                break;
            case "R_block":
                mapView.setImage(ImageSource.resource(R.drawable.r_block));
                break;
            case "S_block":
                mapView.setImage(ImageSource.resource(R.drawable.s_block));
                break;
            case "T_block":
                mapView.setImage(ImageSource.resource(R.drawable.t_block));
                break;
        }
    }
    //for the label_map view
    public void OnCustomToggleClick(View view) {
        System.out.println(mapLabelView.getVisibility());
        if (mapLabelView.getVisibility() == View.INVISIBLE) { //When the map is pressed, and is invisible, set it visible. (Preset as invisible)
            mapLabelView.setVisibility(View.VISIBLE);
        } else {
            System.out.println("Label_Check");//test to see what happens when it is pressed
            mapLabelView.setVisibility(View.INVISIBLE); //Otherwise, when it is pressed and image is visible, set it as invisible.
        }
    }

    public void toTimeTableSetter(View view) {
        finish();
        Intent toTimeTableScreen = new Intent(MapPage.this, TimeTable.class);
        startActivity(toTimeTableScreen);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}