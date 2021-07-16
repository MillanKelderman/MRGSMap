package com.example.maybebetter;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SavingToFile {

    Context context;
    ContextWrapper contextWrapper;

    SavingToFile(Context context){
        this.context = context;
        this.contextWrapper = new ContextWrapper(context);
    }

    public void write(String file, String textData) {
        try {
            FileOutputStream fos = contextWrapper.openFileOutput(file, Context.MODE_APPEND);
            fos.write(textData.getBytes());
            fos.close();
            Toast.makeText(context, "File Saved Successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error Saving File", Toast.LENGTH_SHORT).show();
        }

    }

    public String readFile(String file) {
        String text = "";
        try {
            FileInputStream fis = contextWrapper.openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error reading file", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
}