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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextInputEditText Username;

    SavingToFile savingToFile;
    GlobalVariable globalVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalVariable = new GlobalVariable();
        savingToFile = new SavingToFile(MainActivity.this);



        ImageSlider imageSlider = findViewById(R.id.imageslider);
        Username = findViewById(R.id.textInputEditText);
        button = findViewById(R.id.saveidbut);
        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(Username.getText()).length() == 5) {
                if (fileDoesNotExist(Username.getText() + ".txt")) {
                    savingToFile.write(Username.getText() + ".txt",Username.getText() + ".txt" + "\n");
                }
                savingToFile.write(Username.getText() + ".txt", String.valueOf(Username.getText()));
                globalVariable.setGlobalVariable(Username.getText() + ".txt");
                openMapPage();
            } else {
                Toast.makeText(this, "StudentID must be 5 digits", Toast.LENGTH_SHORT).show();
            }
        });






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

    public void openMapPage() {
        Intent intent = new Intent(this, Mappage.class);
        startActivity(intent);
    }

    public boolean fileDoesNotExist(String name) {
        File file = getBaseContext().getFileStreamPath(name);
        return !file.exists();
    }

}