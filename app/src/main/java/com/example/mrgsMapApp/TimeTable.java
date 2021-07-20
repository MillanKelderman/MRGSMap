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
    private static final String TAG = "theList";//used to check log.d
    private EditText periodOne, periodTwo, periodThree, periodFour, periodFive;
    private Spinner periodOneSpinner, periodTwoSpinner, periodThreeSpinner, periodFourSpinner, periodFiveSpinner;
    private Button SaveTimeTable;

    private SavingToFile savingToFile;

    private String fileName;

    private final String[] ITEMS = new String[]{"", "A block", "B block", "C block", "D Block", "E block", "G block", "H block", "M Block", "P block", "R block", "S block", "T Block"};
    ImageButton goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        savingToFile = new SavingToFile(this); //linking the class SavingToFile.java to TimeTable class to allow the usage of SavingToFile.java's method
        //links objects inside the XML to the variables in the method
        goBack = findViewById(R.id.mapReturn); //finds the ID from the XML sheet
        goBack.setOnClickListener(v -> { //listens for when the button is clicked
            Intent intentLoadNewActivity = new Intent(TimeTable.this, MapPage.class); //when the button is clicked change activity viewing from timetable.java to MapPage.java
            startActivity(intentLoadNewActivity); //Loads the new Activity
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right); //Simple transition, slides between two different areas.
        });

        fileName = ((GlobalVariable) this.getApplication()).getGlobalVariable(); //gets the variable that was set by the SetGlobalVariable in the MainStudentIDLogin page
//links objects inside the XML to the variables in the method
        periodOne = findViewById(R.id.period1);
        periodTwo = findViewById(R.id.period2);
        periodThree = findViewById(R.id.period3);
        periodFour = findViewById(R.id.period4);
        periodFive = findViewById(R.id.period5);
//links objects inside the XML to the variables in the method
        periodOneSpinner = findViewById(R.id.periodOneSpinner);
        periodTwoSpinner = findViewById(R.id.periodTwoSpinner);
        periodThreeSpinner = findViewById(R.id.periodThreeSpinner);
        periodFourSpinner = findViewById(R.id.periodFourSpinner);
        periodFiveSpinner = findViewById(R.id.periodFiveSpinner);
//links objects inside the XML to the variables in the method
        SaveTimeTable = findViewById(R.id.button2);
//sets the variables inside the string array that was mentioned in initial variables.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ITEMS);

//for the different spinners
        periodOneSpinner.setAdapter(adapter);
        periodTwoSpinner.setAdapter(adapter);
        periodThreeSpinner.setAdapter(adapter);
        periodFourSpinner.setAdapter(adapter);
        periodFiveSpinner.setAdapter(adapter);
//Listens if SaveTimetable button has been pressed
        SaveTimeTable.setOnClickListener(v -> {
            saveTimetable();
            SaveTimeTable.setEnabled(false); //Prevents users from saving multiple files for the same thing
        });
    }

    private void saveTimetable() {
        String[][] Data = new String[5][];
        //set for convenience of readability
        String p1 = periodOne.getText().toString().replace(" ", "_");
        String p2 = periodTwo.getText().toString().replace(" ", "_");
        String p3 = periodThree.getText().toString().replace(" ", "_");
        String p4 = periodFour.getText().toString().replace(" ", "_");
        String p5 = periodFive.getText().toString().replace(" ", "_");
        String[] period = {p1, p2, p3, p4, p5};
        //to use in a for loop to iterate through each item, this prevented duplication of code
        String periodOneClass = periodOneSpinner.getSelectedItem().toString().replace(" ", "_");
        String periodTwoClass = periodTwoSpinner.getSelectedItem().toString().replace(" ", "_");
        String periodThreeClass = periodThreeSpinner.getSelectedItem().toString().replace(" ", "_");
        String periodFourClass = periodFourSpinner.getSelectedItem().toString().replace(" ", "_");
        String periodFiveClass = periodFiveSpinner.getSelectedItem().toString().replace(" ", "_");
        //for loop to iterate through each item, this prevented duplication of code
        String[] periodClass = {periodOneClass, periodTwoClass, periodThreeClass, periodFourClass, periodFiveClass};
        //this puts it into a new nested string array
        for (int i = 0; i < 5; i++) { //nested string array is an array within an array,
            String[] classAndPeriod = {period[i], periodClass[i]};
            Data[i] = classAndPeriod;
        }

        Log.d(TAG, Arrays.deepToString(Data)); //check if everything works correctly
        for (int i = 0; i < 5; i++) {
            savingToFile.write(fileName, Data[i][0] + " " + Data[i][1] + "\n"); //saves to file,(explained in SavingToFile.java)
        }
    }
}