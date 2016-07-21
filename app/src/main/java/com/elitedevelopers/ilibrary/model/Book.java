package com.elitedevelopers.ilibrary.model;

/**
 * Created by Nishad on 21-Jul-16.
 */
public class Book {

    private int id;
    private String bookName;
    private String authorName;
    private String category;
    private String description;

    // default constructor
    public Book() {
    }

    // constructor without id
    public Book(String authorName, String bookName, String category, String description) {

        this.authorName = authorName;
        this.bookName = bookName;
        this.category = category;
        this.description = description;
    }

    // constructor with id
    public Book(String authorName, String bookName, String category, String description, int id) {

        this.authorName = authorName;
        this.bookName = bookName;
        this.category = category;
        this.description = description;
        this.id = id;
    }

    @Override
    public String toString() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
