package com.example.mrgsMapApp;

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

public class MainStudentIDLogin extends AppCompatActivity {

    private TextInputEditText username;
    private SavingToFile savingToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savingToFile = new SavingToFile(MainStudentIDLogin.this);
//Calling buttons and sliders for future
        ImageSlider imageSlider = findViewById(R.id.ImageSlider);
        username = findViewById(R.id.textInputEditText);
        Button button = findViewById(R.id.saveIdButton);

        button.setOnClickListener(v -> {
            if (Objects.requireNonNull(username.getText()).length() == 5) {
                if (fileDoesNotExist(username.getText() + ".txt")) {//If they have inputted ID correctly it will save to file here
                    savingToFile.write(username.getText() + ".txt", username.getText() + ".txt");
                    savingToFile.write(username.getText() + ".txt", " " + username.getText() + "\n");
                }
                openMapPage();
            } else { //If the user has not inputted 5 digits It will not allow them to carry on and will give them this toast message
                Toast.makeText(this, "StudentID must be 5 digits!", Toast.LENGTH_SHORT).show();
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

    private void openMapPage() {
        ((GlobalVariable) this.getApplication()).setGlobalVariable(username.getText() + ".txt");
        Intent intent = new Intent(this, MapPage.class);
        startActivity(intent);
    }

    private boolean fileDoesNotExist(String name) {
        File file = getBaseContext().getFileStreamPath(name);
        return !file.exists();
    }
}//end of Appcompat Activity.