package com.example.mrgsMapApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class TimeTable extends AppCompatActivity {
    private static final String TAG = "theList";
    private EditText periodOne, periodTwo, periodThree, periodFour, periodFive;
    private Spinner periodOneSpinner, periodTwoSpinner, periodThreeSpinner, periodFourSpinner, periodFiveSpinner;
    private Button SaveTimeTable;

    private SavingToFile savingToFile;

    private String fileName;

    final String[] items = new String[]{"", "A block", "B block", "C block", "D Block", "E block", "G block", "H block", "M Block", "P block", "R block", "S block", "T Block"};
    ImageButton goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        savingToFile = new SavingToFile(this);
        //this allows for navigation between activity's.
        goBack = findViewById(R.id.mapReturn); //finds the ID from the XML sheet
        goBack.setOnClickListener(v -> { //listens for when the button is clicked
            Intent intentLoadNewActivity = new Intent(TimeTable.this, MapPage.class); //when the button is clicked change activity viewing from timetable.java to MapPage.java
            startActivity(intentLoadNewActivity); //Loads the new Activity
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); //Simple transition, slides between two different areas.
        });

        fileName = ((GlobalVariable) this.getApplication()).getGlobalVariable();
//
        periodOne = findViewById(R.id.period1);
        periodTwo = findViewById(R.id.period2);
        periodThree = findViewById(R.id.period3);
        periodFour = findViewById(R.id.period4);
        periodFive = findViewById(R.id.period5);

        periodOneSpinner = findViewById(R.id.periodOneSpinner);
        periodTwoSpinner = findViewById(R.id.periodTwoSpinner);
        periodThreeSpinner = findViewById(R.id.periodThreeSpinner);
        periodFourSpinner = findViewById(R.id.periodFourSpinner);
        periodFiveSpinner = findViewById(R.id.periodFiveSpinner);

        SaveTimeTable = findViewById(R.id.button2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        periodOneSpinner.setAdapter(adapter);
        periodTwoSpinner.setAdapter(adapter);
        periodThreeSpinner.setAdapter(adapter);
        periodFourSpinner.setAdapter(adapter);
        periodFiveSpinner.setAdapter(adapter);

        SaveTimeTable.setOnClickListener(v -> {
            saveTimetable();
            SaveTimeTable.setEnabled(false);
        });
    }

    private void saveTimetable() {
        String[][] Data = new String[5][];
        String p1 = periodOne.getText().toString().replace(" ", "_");
        String p2 = periodTwo.getText().toString().replace(" ", "_");
        String p3 = periodThree.getText().toString().replace(" ", "_");
        String p4 = periodFour.getText().toString().replace(" ", "_");
        String p5 = periodFive.getText().toString().replace(" ", "_");
        String[] period = {p1, p2, p3, p4, p5};

        String periodOneClass = periodOneSpinner.getSelectedItem().toString().replace(" ","_");
        String periodTwoClass = periodTwoSpinner.getSelectedItem().toString().replace(" ","_");
        String periodThreeClass = periodThreeSpinner.getSelectedItem().toString().replace(" ","_");
        String periodFourClass = periodFourSpinner.getSelectedItem().toString().replace(" ","_");
        String periodFiveClass = periodFiveSpinner.getSelectedItem().toString().replace(" ","_");

        String[] periodClass = {periodOneClass, periodTwoClass, periodThreeClass, periodFourClass, periodFiveClass};

        for (int i = 0; i < 5; i++) {
            String[] classAndPeriod = {period[i], periodClass[i]};
            Data[i] = classAndPeriod;
        }

        Log.d(TAG, Arrays.deepToString(Data));
        for (int i = 0; i < 5; i++) {
            savingToFile.write(fileName, Data[i][0] + " " +  Data[i][1] + "\n");
        }
    }
}