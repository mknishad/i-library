package com.elitedevelopers.ilibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Preferences preferences;
    EditText etUserName;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = new Preferences(this);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void signIn(View view) {
        String userName = etUserName.getText().toString();
        String pasword = etPassword.getText().toString();
        ArrayList<String> savedUserInfo = preferences.retrieveData();
        Intent intent = new Intent(this, Home.class);

        if (!userName.equals("") && !pasword.equals("")) {
            if (savedUserInfo.get(0).equals("No Data") || savedUserInfo.get(1).equals("No Data")) {
                preferences.saveData(userName, pasword);
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
        }
    }

}
