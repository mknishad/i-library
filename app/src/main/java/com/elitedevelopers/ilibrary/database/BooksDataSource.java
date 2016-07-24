package com.elitedevelopers.ilibrary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.elitedevelopers.ilibrary.model.Book;

import java.util.ArrayList;

/**
 * Created by Nishad on 21-Jul-16.
 */
public class BooksDataSource {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Book book;

    public BooksDataSource(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    // open the database for operation
    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    // close the database
    public void close() {
        database.close();
    }

    // add a book to Books table
    public boolean addBook(Book book) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_BOOK_NAME, book.getBookName());
        contentValues.put(DatabaseHelper.COL_AUTHOR_NAME, book. getAuthorName());
        contentValues.put(DatabaseHelper.COL_CATEGORY, book.getCategory());
        contentValues.put(DatabaseHelper.COL_DESCRIPTION, book.getDescription());

        long inserted = database.insert(DatabaseHelper.TABLE_BOOKS, null, contentValues);

        this.close();

        return inserted>0 ? true:false;
    }

    // get a single book from the table
    public Book getBook(int id) {
        this.open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_BOOKS, new String[] {DatabaseHelper.COL_ID,
                        DatabaseHelper.COL_BOOK_NAME, DatabaseHelper.COL_AUTHOR_NAME, DatabaseHelper.COL_CATEGORY,
                        DatabaseHelper.COL_DESCRIPTION}, DatabaseHelper.COL_ID + " = " + id, null, null, null, null);

        cursor.moveToFirst();
        book = createBook(cursor);
        cursor.close();
        this.close();

        return book;
    }

    // return all books by author from the table Books
    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> books = new ArrayList<>();

        this.open();
        /*Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_BOOKS + " WHERE "
                + DatabaseHelper.COL_AUTHOR_NAME + " = " + author, null); */
        Cursor cursor = database.query(DatabaseHelper.TABLE_BOOKS, new String[] {DatabaseHelper.COL_ID,
                DatabaseHelper.COL_BOOK_NAME, DatabaseHelper.COL_AUTHOR_NAME, DatabaseHelper.COL_CATEGORY,
                DatabaseHelper.COL_DESCRIPTION}, DatabaseHelper.COL_AUTHOR_NAME + " = '" + author + "';", null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++) {
                book = createBook(cursor);
                books.add(book);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();

        return books;
    }

    // return all books by category from the table Books
    public ArrayList<Book> getBooksByCategory(String category) {
        ArrayList<Book> books = new ArrayList<>();

        this.open();
        /*Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_BOOKS + " WHERE "
                + DatabaseHelper.COL_CATEGORY + " = " + category, null);*/
        Cursor cursor = database.query(DatabaseHelper.TABLE_BOOKS, new String[] {DatabaseHelper.COL_ID,
                DatabaseHelper.COL_BOOK_NAME, DatabaseHelper.COL_AUTHOR_NAME, DatabaseHelper.COL_CATEGORY,
                DatabaseHelper.COL_DESCRIPTION}, DatabaseHelper.COL_CATEGORY + " = '" + category + "';", null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++) {
                book = createBook(cursor);
                books.add(book);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();

        return books;
    }

    // return all categories from the table
    public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        this.open();
        Cursor cursor = database.rawQuery("SELECT DISTINCT " + DatabaseHelper.COL_CATEGORY + " FROM "
                        + DatabaseHelper.TABLE_BOOKS, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++) {
                String mCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CATEGORY));
                categories.add(mCategory);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();

        return categories;
    }

    // return all authors from the table
    public ArrayList<String> getAuthors() {
        ArrayList<String> authors = new ArrayList<>();
        this.open();
        Cursor cursor = database.rawQuery("SELECT DISTINCT " + DatabaseHelper.COL_AUTHOR_NAME + " FROM "
                + DatabaseHelper.TABLE_BOOKS, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i=0; i<cursor.getCount(); i++) {
                String mCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AUTHOR_NAME));
                authors.add(mCategory);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();

        return authors;
    }

    // update a book information
    public boolean updateBook(int id, Book book) {
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_BOOK_NAME, book.getBookName());
        contentValues.put(DatabaseHelper.COL_AUTHOR_NAME, book. getAuthorName());
        contentValues.put(DatabaseHelper.COL_CATEGORY, book.getCategory());
        contentValues.put(DatabaseHelper.COL_DESCRIPTION, book.getDescription());

        int updated = database.update(DatabaseHelper.TABLE_BOOKS, contentValues,
                        DatabaseHelper.COL_ID + " = " + id, null);
        this.close();

        return updated>0 ? true:false;
    }

    // delete a book from the table
    public boolean deleteBook(int id) {
        this.open();

        int deleted = database.delete(DatabaseHelper.TABLE_BOOKS, DatabaseHelper.COL_ID + " = " + id, null);
        this.close();

        return deleted>0 ? true:false;
    }

    // create a book from cursor data
    private Book createBook(Cursor cursor) {
        int mId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
        String mBookName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BOOK_NAME));
        String mAuthorName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_AUTHOR_NAME));
        String mCategory = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CATEGORY));
        String mDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_DESCRIPTION));

        Book b = new Book(mId, mBookName, mAuthorName, mCategory, mDescription);

        return b;
    }

}
