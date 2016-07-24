package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}
