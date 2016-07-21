package com.elitedevelopers.ilibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nishad on 21-Jul-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "Books.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_BOOKS = "Books";

    static final String COL_ID = "Id";
    static final String COL_BOOK_NAME = "BookName";
    static final String COL_AUTHOR_NAME = "AuthorName";
    static final String COL_CATEGORY = "Category";
    static final String COL_DESCRIPTION = "Description";

    // query to create a table on the database
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_BOOKS + " (" + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_BOOK_NAME + " TEXT, " + COL_AUTHOR_NAME + " TEXT, " + COL_CATEGORY + " TEXT, " + COL_DESCRIPTION
            + " TEXT);";

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

}
