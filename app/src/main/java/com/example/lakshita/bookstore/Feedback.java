package com.example.lakshita.bookstore;

public class Feedback {
    private String isbn;
    private String fText;
    private String score;
    private String FID;

    // COmnstructore
    public Feedback(String isbn, String fText, String score, String FID){
        this.setIsbn(isbn);
        this.setfText(fText);
        this.setCopies(score);
        this.setFID(FID);

    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getfText() {
        return fText;
    }

    public void setfText(String fText) {
        this.fText = fText;
    }

    public String getScore() {
        return score;
    }

    public void setCopies(String score) {
        this.score = score;
    }

    public String getFID() {
        return FID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }
}
