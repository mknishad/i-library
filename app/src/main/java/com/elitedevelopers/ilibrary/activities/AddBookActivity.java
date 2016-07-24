package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.database.BooksDataSource;
import com.elitedevelopers.ilibrary.model.Book;

public class AddBookActivity extends AppCompatActivity {

    private EditText etBookName;
    private EditText etAuthorName;
    private EditText etCategory;
    private EditText etDescription;
    private Book book;      // book to save
    private BooksDataSource booksDataSource;    // for database operation
    private int id;
    private String viewBy;

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
        id = getIntent().getIntExtra("id", 0);
        viewBy = getIntent().getStringExtra("type");

         // set values to the EditText views
        etBookName.setText(getIntent().getStringExtra("name"));
        etAuthorName.setText(getIntent().getStringExtra("author"));
        etCategory.setText(getIntent().getStringExtra("category"));
        etDescription.setText(getIntent().getStringExtra("description"));
    }

    // cancel button action
    public void gotoPreviousActivity(View view) {
        this.finish();
    }

    // save button action
    public void saveData(View view) {
        // collect the inputs
        String bookName = etBookName.getText().toString();
        String authorName = etAuthorName.getText().toString();
        String category = etCategory.getText().toString();
        String description = etDescription.getText().toString();

        // check if the input fields are empty or not
        if (!bookName.equals("") && !authorName.equals("") && !category.equals("") && !description.equals("")) {
            if (id > 0) {
                // if fields are not empty make a new book with the inputs
                book = new Book(bookName, authorName, category, description);
                // add book to database
                boolean updated = booksDataSource.updateBook(id, book);
                if (updated) {
                    Toast.makeText(AddBookActivity.this, bookName + " updated successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBookActivity.this, BookListActivity.class);
                    intent.putExtra("type", viewBy);
                    intent.putExtra("author", book.getAuthorName());
                    intent.putExtra("category", book.getCategory());
                    startActivity(intent);
                    this.finish();
                } else {
                    Toast.makeText(AddBookActivity.this, bookName + " was not saved!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // if fields are not empty make a new book with the inputs
                book = new Book(bookName, authorName, category, description);
                // add book to database
                boolean saved = booksDataSource.addBook(book);
                if (saved) {
                    Toast.makeText(AddBookActivity.this, bookName + " saved successfully!", Toast.LENGTH_SHORT).show();
                    this.finish();
                } else {
                    Toast.makeText(AddBookActivity.this, bookName + " was not saved!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Enter information properly", Toast.LENGTH_SHORT).show();
        }
    }

}
