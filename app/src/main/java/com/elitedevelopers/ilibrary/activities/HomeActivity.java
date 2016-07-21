package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.elitedevelopers.ilibrary.R;

public class HomeActivity extends AppCompatActivity {

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
}
