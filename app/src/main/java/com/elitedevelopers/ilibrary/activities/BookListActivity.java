package com.elitedevelopers.ilibrary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookListView = (ListView) findViewById(R.id.lvBookList);    // initialize list view
        booksDataSource = new BooksDataSource(this);    // to operate database operations
        String type = getIntent().getStringExtra("type");
        if (type.equals("Category")) {
            String category = getIntent().getStringExtra("category");
            books = booksDataSource.getBooksByCategory(category);
        } else {
            String author = getIntent().getStringExtra("author");
            books = booksDataSource.getBooksByAuthor(author);      // get all books from database
        }
        booksAdapter = new BookAdapter(this, books);    // initialize new adapter
        bookListView.setAdapter(booksAdapter);          // set the adapter to list view
    }

}
