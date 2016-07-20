package com.elitedevelopers.ilibrary.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Nishad on 21-Jul-16.
 */
public class Preferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // constructor
    public Preferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    // save username and password to shared preferences
    public void saveData(String userName, String password) {
        editor.putString("userName", userName);
        editor.putString("password", password);
        editor.commit();
    }

    // return saved data as array list
    public ArrayList<String> retrieveData() {
        ArrayList<String> arrayList = new ArrayList<>();
        String userName = sharedPreferences.getString("userName", "No Data");
        String password = sharedPreferences.getString("password", "No Data");
        arrayList.add(userName);
        arrayList.add(password);

        return arrayList;
    }

}
