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
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Diversity.jpg", "Diversity"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Individuals-1920x1280.jpg", "Individuals"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Teaching-1920x1280.jpg", "Teaching"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Purposeful-1920x1280.jpg", "Purposeful"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Kindness-1920x1280.jpg", "Kindness"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Community-1920x1280.jpg", "Community"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Leadership-1920x1280.jpg", "Leadership"));
        slideModels.add(new SlideModel("https://www.mrgs.school.nz/wp-content/uploads/2018/05/Teamwork-1920x1280.jpg", "Teamwork"));
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