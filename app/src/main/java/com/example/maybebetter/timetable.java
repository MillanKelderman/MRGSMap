package com.example.maybebetter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class timetable extends AppCompatActivity {
    EditText period_1, period_2, period_3, period_4, period_5;
    Spinner periodOneSpinner, periodTwoSpinner, periodThreeSpinner, periodFourSpinner, periodFiveSpinner;
    Button SaveTimeTable;

    ArrayAdapter<String> adapter;

    GlobalVariable globalVariable;
    String fileName;

    SavingToFile savingToFile;
    String[] items = new String[]{"", "A block", "B block", "C block", "D Block", "E block", "G block", "H block", "M Block", "P block", "R block", "S block", "T Block"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        savingToFile = new SavingToFile(timetable.this);
        globalVariable = new GlobalVariable();


        fileName = globalVariable.getGlobalVariable();

        period_1 = findViewById(R.id.period1);
        period_2 = findViewById(R.id.period2);
        period_3 = findViewById(R.id.period3);
        period_4 = findViewById(R.id.period4);
        period_5 = findViewById(R.id.period5);

        periodOneSpinner = findViewById(R.id.periodOneSpinner);
        periodTwoSpinner = findViewById(R.id.periodTwoSpinner);
        periodThreeSpinner = findViewById(R.id.periodThreeSpinner);
        periodFourSpinner = findViewById(R.id.periodFourSpinner);
        periodFiveSpinner = findViewById(R.id.periodFiveSpinner);

        SaveTimeTable = findViewById(R.id.button2);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        periodOneSpinner.setAdapter(adapter);
        periodTwoSpinner.setAdapter(adapter);
        periodThreeSpinner.setAdapter(adapter);
        periodFourSpinner.setAdapter(adapter);
        periodFiveSpinner.setAdapter(adapter);

        SaveTimeTable.setOnClickListener(v -> {
            SaveTimeTable.setEnabled(false);
            saveTimetable();

        });

    }

    private void saveTimetable() {
        String[][] Data = new String[4][];
        String p1 = period_1.getText().toString().replace(" ", "_");
        String p2 = period_2.getText().toString().replace(" ", "_");
        String p3 = period_3.getText().toString().replace(" ", "_");
        String p4 = period_4.getText().toString().replace(" ", "_");
        String p5 = period_5.getText().toString().replace(" ", "_");

        String[] period = {p1, p2, p3, p4, p5};

        String period_1_class = periodOneSpinner.getSelectedItem().toString();
        String period_2_class = periodTwoSpinner.getSelectedItem().toString();
        String period_3_class = periodThreeSpinner.getSelectedItem().toString();
        String period_4_class = periodFourSpinner.getSelectedItem().toString();
        String period_5_class = periodFiveSpinner.getSelectedItem().toString();

        String[] periodClass = {period_1_class, period_2_class, period_3_class, period_4_class, period_5_class};

        for (int i = 0; i < 4; i++) {
            String[] value = {period[i], periodClass[i]};
            Data[i] = value;
        }

        for (String[] data : Data) {
            savingToFile.write(fileName, data[0] + data[1] + "\n");
        }
    }
}