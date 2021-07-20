package com.example.mrgsMapApp;

import android.app.Application;

public class GlobalVariable extends Application {
    private String ID;

    public String getGlobalVariable() {
        return ID;
    } //allows the variable to be used in other classes when GlobalVariable.class is mentioned

    public void setGlobalVariable(String ID) {
        this.ID = ID;
    } //sets the variable to be used in different classes
}