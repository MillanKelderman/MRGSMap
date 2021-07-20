package com.example.mrgsMapApp;

import android.app.Application;

public class GlobalVariable extends Application {
    private String ID;

    public String getGlobalVariable() {
        return ID;
    }

    public void setGlobalVariable(String ID) {
        this.ID = ID;
    }
}