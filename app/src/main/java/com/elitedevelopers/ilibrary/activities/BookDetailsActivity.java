package com.elitedevelopers.ilibrary.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.elitedevelopers.ilibrary.R;
import com.elitedevelopers.ilibrary.database.BooksDataSource;
import com.elitedevelopers.ilibrary.model.Book;

public class BookDetailsActivity extends AppCompatActivity {

    private TextView tvBookName;
    private TextView tvAuthorName;
    private TextView tvCategoryName;
    private TextView tvDescription;
    private BooksDataSource booksDataSource;
    private Book book;
    private int id;
    private String viewBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        // initialize  the views
        tvBookName = (TextView) findViewById(R.id.tvBookName);
        tvAuthorName = (TextView) findViewById(R.id.tvAuthorName);
        tvCategoryName= (TextView) findViewById(R.id.tvCategoryName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);

        // initialize data source for book
        booksDataSource = new BooksDataSource(this);
        // get the id of the selected book
        id = getIntent().getIntExtra("id", 0);
        viewBy = getIntent().getStringExtra("type");

        if (id > 0) {
            book = booksDataSource.getBook(id);
            tvBookName.setText(book.getBookName());
            tvAuthorName.setText(book.getAuthorName());
            tvCategoryName.setText(book.getCategory());
            tvDescription.setText(book.getDescription());
        }
    }

    public void deleteBook(View view) {
        if (id > 0) {
            boolean deleted = booksDataSource.deleteBook(id);
            if (deleted) {
                Toast.makeText(this, tvBookName.getText().toString() + " deleted successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BookDetailsActivity.this, BookListActivity.class);
                intent.putExtra("type", viewBy);
                if (viewBy.equals("Author")) {
                    intent.putExtra("author", tvAuthorName.getText().toString());
                } else if (viewBy.equals("Category")) {
                    intent.putExtra("category", tvCategoryName.getText().toString());
                }
                startActivity(intent);
                this.finish();
            } else {
                Toast.makeText(this, tvBookName.getText().toString() + " can't be deleted!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void editBook(View view) {
        Intent intent = new Intent(BookDetailsActivity.this, AddBookActivity.class);
        intent.putExtra("id", book.getId());
        intent.putExtra("type", viewBy);
        intent.putExtra("name", tvBookName.getText().toString());
        intent.putExtra("author", tvAuthorName.getText().toString());
        intent.putExtra("category", tvCategoryName.getText().toString());
        intent.putExtra("description", tvDescription.getText().toString());
        startActivity(intent);
    }
}
