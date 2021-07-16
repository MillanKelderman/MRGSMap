package com.example.maybebetter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextInputEditText Username;

    SavingToFile savingToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savingToFile = new SavingToFile(MainActivity.this);

        ImageSlider imageSlider = findViewById(R.id.ImageSlider);
        Username = findViewById(R.id.textInputEditText);
        button = findViewById(R.id.saveidbut);

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(Username.getText()).length() == 5) {
                if (fileDoesNotExist(Username.getText() + ".txt")) {
                    savingToFile.write(Username.getText() + ".txt", Username.getText() + ".txt");
                    savingToFile.write(Username.getText() + ".txt", " " + Username.getText() + "\n");
                }

                openMapPage();
            } else {
                Toast.makeText(this, "saving file successful", Toast.LENGTH_SHORT).show();
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
        ((GlobalVariable) this.getApplication()).setGlobalVariable(Username.getText() + ".txt");
        Intent intent = new Intent(this, Mappage.class);
        startActivity(intent);
    }

    public boolean fileDoesNotExist(String name) {
        File file = getBaseContext().getFileStreamPath(name);
        return !file.exists();
    }
}