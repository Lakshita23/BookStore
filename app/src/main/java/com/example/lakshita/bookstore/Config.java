package com.example.lakshita.bookstore;

public class Config {
    // Address of scripts
    public static final String URL_ADDBOOK = "http://bookdb.16mb.com/addBook.php";
    public static final String URL_GETALLBOOK= "http://bookdb.16mb.com/getAllBooks.php";
    public static final String URL_LOGIN= "http://bookdb.16mb.com/login.php";
    public static final String URL_ADDFEEDBACK = "http://bookdb.16mb.com/insertFeedback.php";
    public static final String URL_GETFEEDBACK = "http://bookdb.16mb.com/getFeedbacks.php";
    public static final String URL_ADDRATING = "http://bookdb.16mb.com/addRating.php";
    public static final String URL_UPDATECOPIES = "http://bookdb.16mb.com/updateCopies.php";

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

    // ADD FEEDBACK - Send request to PHP script
    public static final String KEY_FEEDBACK_LOGINID = "loginid";
    public static final String KEY_FEEDBACK_ISBN = "ISBN";
    public static final String KEY_FEEDBACK_TEXT = "Ftext";
    public static final String KEY_FEEDBACK_RATINGS = "score";

    // ADD RATING - Send request to PHP script
    public static final String KEY_RATING_FID = "FID";
    public static final String KEY_RATING_LOGINID = "loginid";
    public static final String KEY_RATING_ISBN = "ISBN";
    public static final String KEY_RATING_RATE = "rate";

    // UPDATE COUNT - Send request to PHP script
    public static final String KEY_COUNT_ISBN = "ISBN";
    public static final String KEY_COUNT_COUNT = "copies";


    // GET BOOKS - JSON TAGS
    public static final String TAG_JSON_ARRAY="result";

    public static final String TAG_GET_FEEDBACK_FTEXT = "Ftext";
    public static final String TAG_GET_FEEDBACK_SCORE = "score";

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
