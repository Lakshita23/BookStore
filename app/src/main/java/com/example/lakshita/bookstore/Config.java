package com.example.lakshita.bookstore;

public class Config {
    // Address of scripts
    public static final String URL_ADDBOOK = "http://bookdb.16mb.com/addBook.php";
    public static final String URL_GETALLBOOK= "http://bookdb.16mb.com/getAllBooks.php";
    public static final String URL_LOGIN= "http://bookdb.16mb.com/login.php";

    // ADD BOOK - Send request to PHP script
    public static final String KEY_BOOK_ISBN = "ISBN";
    public static final String KEY_BOOK_TITLE = "title";
    public static final String KEY_BOOK_AUTHOR = "author";
    public static final String KEY_BOOK_PUBLISHER = "publisher";
    public static final String KEY_BOOK_YEAR = "year_published";
    public static final String KEY_BOOK_COPIES = "copies";
    public static final String KEY_BOOK_PRICE = "price";
    public static final String KEY_BOOK_FORMAT = "book_format";
    public static final String KEY_BOOK_KEYWORDS = "keywords";
    public static final String KEY_BOOK_SUBJECT = "book_subject";

    // GET BOOKS - JSON TAGS
    public static final String TAG_JSON_ARRAY="result";

    public static final String TAG_GET_ISBN = "ISBN";
    public static final String TAG_GET_TITLE= "title";
    public static final String TAG_GET_AUTHOR= "author";
    public static final String TAG_GET_PUBLISHER= "publisher";
    public static final String TAG_GET_YEAR = "year_published";
    public static final String TAG_GET_COPIES = "copies";
    public static final String TAG_GET_PRICE = "price";
    public static final String TAG_GET_FORMAT = "book_format";
    public static final String TAG_GET_KEYWORDS= "keywords";
    public static final String TAG_GET_SUBJECT= "book_subject";


}
