package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.adapters.BookAdapter;
import com.elitedevelopers.ilibrary.database.BooksDataSource;
import com.elitedevelopers.ilibrary.model.Book;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private ListView bookListView;
    private ArrayList<Book> books;
    private ArrayAdapter<Book> booksAdapter;
    private BooksDataSource booksDataSource;
    String viewBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookListView = (ListView) findViewById(R.id.lvBookList);    // initialize list view
        booksDataSource = new BooksDataSource(this);    // to operate database operations

        // get intent type
        String type = getIntent().getStringExtra("type");
        if (type.equals("Category")) {
            String category = getIntent().getStringExtra("category");
            viewBy = "Category";
            books = booksDataSource.getBooksByCategory(category);   // get books of this category from database
        } else if (type.equals("Author")){
            String author = getIntent().getStringExtra("author");
            viewBy = "Author";
            books = booksDataSource.getBooksByAuthor(author);      // get books of this author from database
        }

        booksAdapter = new BookAdapter(this, books);    // initialize new adapter
        bookListView.setAdapter(booksAdapter);          // set the adapter to list view

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookListActivity.this, BookDetailsActivity.class);
                intent.putExtra("id", books.get(position).getId());
                intent.putExtra("type", viewBy);
                startActivity(intent);
            }
        });

        // enable up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent;
        if (viewBy.equals("Author")) {
            intent = new Intent(BookListActivity.this,  AuthorListActivity.class);
        } else {
            intent = new Intent(BookListActivity.this,  CategoryListActivity.class);
        }
        startActivity(intent);
        this.finish();
    }

}
