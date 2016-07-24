package com.elitedevelopers.ilibrary.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private  Activity activity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        activity = this;

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

    public void deleteBook(View view) {
        if (id > 0) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure to delete " + book.getBookName() + "?");

            alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    boolean deleted = booksDataSource.deleteBook(id);
                    if (deleted) {
                        Toast.makeText(BookDetailsActivity.this, tvBookName.getText().toString() + " deleted successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookDetailsActivity.this, BookListActivity.class);
                        intent.putExtra("type", viewBy);
                        if (viewBy.equals("Author")) {
                            intent.putExtra("author", tvAuthorName.getText().toString());
                        } else if (viewBy.equals("Category")) {
                            intent.putExtra("category", tvCategoryName.getText().toString());
                        }
                        startActivity(intent);
                        activity.finish();
                    } else {
                        Toast.makeText(BookDetailsActivity.this, tvBookName.getText().toString() + " can't be deleted!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BookDetailsActivity.this, BookListActivity.class);
        intent.putExtra("type", viewBy);
        if (viewBy.equals("Author")) {
            intent.putExtra("author", tvAuthorName.getText().toString());
        } else if (viewBy.equals("Category")) {
            intent.putExtra("category", tvCategoryName.getText().toString());
        }
        startActivity(intent);
        this.finish();
    }
}
