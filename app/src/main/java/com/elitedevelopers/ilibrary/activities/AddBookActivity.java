package com.elitedevelopers.ilibrary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.database.BooksDataSource;
import com.elitedevelopers.ilibrary.model.Book;

public class AddBookActivity extends AppCompatActivity {

    EditText etBookName;
    EditText etAuthorName;
    EditText etCategory;
    EditText etDescription;
    Book book;      // book to save
    BooksDataSource booksDataSource;    // for database operation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // initialise the variables
        etBookName = (EditText) findViewById(R.id.etBookName);
        etAuthorName = (EditText) findViewById(R.id.etAuthorName);
        etCategory = (EditText) findViewById(R.id.etCategory);
        etDescription = (EditText) findViewById(R.id.etDescription);
        booksDataSource = new BooksDataSource(this);
    }

    // cancel button action
    public void gotoPreviousActivity(View view) {
        this.finish();
    }

    // save button action
    public void saveData(View view) {
        // check if the input fields are empty or not
        if (!etBookName.equals("") && !etAuthorName.equals("") && !etCategory.equals("") && !etDescription.equals("")) {
            // if input fields are not empty collect the inputs
            String bookName = etBookName.getText().toString();
            String authorName = etAuthorName.getText().toString();
            String category = etCategory.getText().toString();
            String description = etDescription.getText().toString();
            // make a new book with the inputs
            book = new Book(bookName, authorName, category, description);
            // add book to database
            boolean saved = booksDataSource.addBook(book);
            if (saved) {
                Toast.makeText(AddBookActivity.this, bookName + " saved successfully!", Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Toast.makeText(AddBookActivity.this, bookName + " was not saved!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter information properly", Toast.LENGTH_SHORT).show();
        }

    }
}
