package com.example.lakshita.bookstore;

public class Books {
    private String isbn;
    private String title;
    private String copies;

    // COmnstructore
    public Books(String isbn, String title, String copies){
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setCopies(copies);
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }
}
