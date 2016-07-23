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

        bookListView = (ListView) findViewById(R.id.lvBookList);
        booksDataSource = new BooksDataSource(this);
        books = booksDataSource.getAllBooks();
        booksAdapter = new BookAdapter(this, books);
        bookListView.setAdapter(booksAdapter);
    }

}
