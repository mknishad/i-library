package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.adapters.AuthorAdapter;
import com.elitedevelopers.ilibrary.database.BooksDataSource;

import java.util.ArrayList;

public class AuthorListActivity extends AppCompatActivity {

    private ListView authorListView;
    private ArrayList<String> authors;
    private ArrayAdapter<String> authorAdapter;
    private BooksDataSource booksDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_list);

        authorListView = (ListView) findViewById(R.id.lvAuthorList);    // initialize list view
        booksDataSource = new BooksDataSource(this);           // to operate database operations
        authors = booksDataSource.getAuthors();          // get all authors from database
        authorAdapter = new AuthorAdapter(this, authors);    // initialize new adapter
        authorListView.setAdapter(authorAdapter);          // set the adapter to list view

        authorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // start BookListActivity
                Intent intent = new Intent(AuthorListActivity.this, BookListActivity.class);
                intent.putExtra("type", "Author");
                intent.putExtra("author", authors.get(position));
                startActivity(intent);
            }
        });
    }
}
