package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.elitedevelopers.ilibrary.R;

public class HomeActivity extends AppCompatActivity {

    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void gotoAddBookActivity(View view) {
        Intent addBookIntent = new Intent(this, AddBookActivity.class);
        startActivity(addBookIntent);
    }

    public void gotoAuthorListActivity(View view) {
        Intent authorIntent = new Intent(this, AuthorListActivity.class);
        startActivity(authorIntent);
    }

    public void gotoCategoryListActivity(View view) {
        Intent categoryIntent = new Intent(this, CategoryListActivity.class);
        startActivity(categoryIntent);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}
