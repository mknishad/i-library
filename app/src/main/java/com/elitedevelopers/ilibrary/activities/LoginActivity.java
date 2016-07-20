package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elitedevelopers.ilibrary.sharedpreferences.Preferences;
import com.elitedevelopers.ilibrary.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Preferences preferences;    // instance of SharedPreferences
    EditText etUserName;        // instance of username view
    EditText etPassword;        // instance of password view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = new Preferences(this);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void signIn(View view) {
        String userName = etUserName.getText().toString();              // entered username
        String password = etPassword.getText().toString();              // entered password
        ArrayList<String> savedUserInfo = preferences.retrieveData();   // saved username and password on SharedPreferences

        // check if the username and password fields are empty or not
        if (!userName.equals("") && !password.equals("")) {
            // if username and password fields are not empty
            // check if data was previously saved on shared preferences or not
            if ( savedUserInfo.get(0).equals("No Data") || savedUserInfo.get(1).equals("No Data")) {
                // if username and password were not saved previously
                // save new username and password
                preferences.saveData(userName, password);
                // clear username and password from EditText views
                etUserName.setText("");
                etPassword.setText("");
                // make an intent to go to home activity
                Intent intent = new Intent(this, HomeActivity.class);
                // go to home activity
                startActivity(intent);
            } else {
                // if username and password were saved previously
                // check if entered username and password matched the saved username and password
                if (savedUserInfo.get(0).equals(userName) && savedUserInfo.get(1).equals(password)) {
                    // clear username and password from EditText views
                    etUserName.setText("");
                    etPassword.setText("");
                    // if matched go to home activity
                    // make an intent to go to home activity
                    Intent intent = new Intent(this, HomeActivity.class);
                    // go to home activity
                    startActivity(intent);
                } else {
                    // if not matched show user an error message
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    // clear username and password from EditText views
                    etUserName.setText("");
                    etPassword.setText("");
                }
            }
        } else {
            // if either username or password field is empty show a message to user
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            // clear username and password from EditText views
            etUserName.setText("");
            etPassword.setText("");
        }
    }

}
