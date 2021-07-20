package com.example.mrgsMapApp;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SavingToFile {
    final Context CONTEXT;
    final ContextWrapper CONTEXT_WRAPPER;

    SavingToFile(Context context) {
        this.CONTEXT = context;
        this.CONTEXT_WRAPPER = new ContextWrapper(context);
    }

    public void write(String file, String textData) {
        try {
            FileOutputStream fos = CONTEXT_WRAPPER.openFileOutput(file, Context.MODE_APPEND);//Opens the file
            fos.write(textData.getBytes());//writes to the file
            fos.close();//closes the file
            Toast.makeText(CONTEXT, "File Saved Successfully", Toast.LENGTH_SHORT).show();
            //checks if any error occurred
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(CONTEXT, "Error Saving File", Toast.LENGTH_SHORT).show();
        }

    }

    //This reads the file and returns the output of text into a string value
    public String readFile(String file) {
        String text = "";
        try {
            FileInputStream fis = CONTEXT_WRAPPER.openFileInput(file);
            //checks and sets the size of the byte, byte is the size of the text that is being read in the file
            int size = fis.available();
            byte[] buffer = new byte[size];//Checks the size of the text and sets to the new size of the byte
            fis.read(buffer);//reads the file
            fis.close();//closes the file
            text = new String(buffer);//sets text to the information read from fis.read(buffer)
            //Reading however much the byte reads, which is why the size of the text needs to be found
        } catch (Exception e) { //this catches any errors
            e.printStackTrace();
            Toast.makeText(CONTEXT, "Error reading file", Toast.LENGTH_SHORT).show();
        }
        return text;
    }
}
