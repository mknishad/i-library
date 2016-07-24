package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.adapters.CategoryAdapter;
import com.elitedevelopers.ilibrary.database.BooksDataSource;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

    private ListView categoryListView;
    private ArrayList<String> categories;
    private ArrayAdapter<String> categoryAdapter;
    private BooksDataSource booksDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryListView = (ListView) findViewById(R.id.lvCategoryList);    // initialize list view
        booksDataSource = new BooksDataSource(this);           // to operate database operations
        categories = booksDataSource.getCategories();          // get all category from database
        categoryAdapter = new CategoryAdapter(this, categories);    // initialize new adapter
        categoryListView.setAdapter(categoryAdapter);          // set the adapter to list view

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // start BookListActivity
                Intent intent = new Intent(CategoryListActivity.this, BookListActivity.class);
                intent.putExtra("type", "Category");
                intent.putExtra("category", categories.get(position));
                startActivity(intent);
            }
        });

        // enable up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.menu_sign_out:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_about:
                Intent intent1 = new Intent(this, AboutActivity.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
