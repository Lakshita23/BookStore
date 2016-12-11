package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class AddBook extends AppCompatActivity {

    // Variables
    EditText et_addISBN,et_addTitle, et_addAuthor, et_addPublisher, et_addYearPublished, et_addCopies, et_addPrice,et_addBookFormat, et_addKeywords, et_addSubject;
    Button btn_addBook;
    String addisbn, addtitle, addauthor, addpublisher, addYearPublished, addCopies, addPrice, addBookFormat, addKeywords,addSubject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Locate widgets in content_add_book.xml
        et_addISBN = (EditText)findViewById(R.id.editText_addISBN);
        et_addTitle = (EditText)findViewById(R.id.editText_addTitle);
        et_addAuthor = (EditText)findViewById(R.id.editText_addAuthor);
        et_addPublisher = (EditText)findViewById(R.id.editText_addPublisher);
        et_addYearPublished = (EditText)findViewById(R.id.editText_addYearPublished);
        et_addCopies = (EditText)findViewById(R.id.editText_addCopies);
        et_addPrice = (EditText)findViewById(R.id.editText_addPrice);
        et_addBookFormat = (EditText)findViewById(R.id.editText_addBookFormat);
        et_addKeywords = (EditText)findViewById(R.id.editText_addKeywords);
        et_addSubject = (EditText)findViewById(R.id.editText_addSubject);

        btn_addBook = (Button)findViewById(R.id.button_addBook);

        // On click
        btn_addBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                addBookFunction();
            }
        });

    }



    private void addBookFunction()
    {
        addisbn = et_addISBN.getText().toString().trim();
        addtitle = et_addTitle.getText().toString().trim();
        addauthor = et_addAuthor.getText().toString().trim();
        addpublisher = et_addPublisher.getText().toString().trim();
        addYearPublished = et_addYearPublished.getText().toString().trim();
        addCopies = et_addCopies.getText().toString().trim();
        addPrice = et_addPrice.getText().toString().trim();
        addBookFormat = et_addBookFormat.getText().toString().trim();
        addKeywords = et_addKeywords.getText().toString().trim();
        addSubject = et_addSubject.getText().toString().trim();

        class addBook extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AddBook.this,"Adding...",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_BOOK_ISBN,addisbn);
                params.put(Config.KEY_BOOK_TITLE,addtitle);
                params.put(Config.KEY_BOOK_AUTHOR,addauthor);

                params.put(Config.KEY_BOOK_PUBLISHER,addpublisher);
                params.put(Config.KEY_BOOK_YEAR,addYearPublished);
                params.put(Config.KEY_BOOK_COPIES,addCopies);

                params.put(Config.KEY_BOOK_PRICE,addPrice);
                params.put(Config.KEY_BOOK_FORMAT,addBookFormat);
                params.put(Config.KEY_BOOK_KEYWORDS,addKeywords);

                params.put(Config.KEY_BOOK_SUBJECT,addSubject);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADDBOOK, params);
                return res;
            }
        }

        addBook addBook = new addBook();
        addBook.execute();
    }
}

