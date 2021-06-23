package com.example.maybebetter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextInputLayout Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (fileDosentExist("StudentIDFile.txt")){
            write("StudentIDFile.txt", "");
        }
        Username = findViewById(R.id.textInputLayout);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String fileData = readFile("StudentIDFile.txt");
            String[] DataString = fileData.split("\n");
            write("StudentIDFile.txt", String.valueOf(Username.getEditText().getText()));
            openMappage();
        });


        ImageSlider imageSlider = findViewById(R.id.imageslider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("", "1 Image"));
        slideModels.add(new SlideModel("", "2 Image"));
        slideModels.add(new SlideModel("", "3 Image"));
        slideModels.add(new SlideModel("", "4 Image"));
        slideModels.add(new SlideModel("", "5 Image"));
        slideModels.add(new SlideModel("", "6 Image"));
        imageSlider.setImageList(slideModels, true);
    }
    public void openMappage() {
        Intent intent = new Intent(this, Mappage.class);
        startActivity(intent);


    }
    public void write(String file, String textData) {
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_APPEND);
            fos.write(textData.getBytes());
            fos.close();
            Toast.makeText(this, "saving file successful", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
        }

    }

    public String readFile(String file) {
        String text = "";
        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
    public boolean fileDosentExist(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return !file.exists();
    }
}