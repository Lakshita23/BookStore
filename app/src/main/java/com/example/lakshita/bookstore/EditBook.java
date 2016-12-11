package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EditBook extends AppCompatActivity {

    EditText et_edit_ISBN;
    private String JSON_STRING;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        // Get Intent
        Intent fromDisplayListView = getIntent();

        // Receive intent
        String receivedISBN = fromDisplayListView.getStringExtra("passedISBN");
        String receivedTitle = fromDisplayListView.getStringExtra("passedTitle");
        String receivedCopies = fromDisplayListView.getStringExtra("passedCopies");

        Toast.makeText(this, receivedISBN + receivedTitle + receivedCopies, Toast.LENGTH_SHORT).show();



        // Only allow copies to be editable
        // Update copies from books where ISBN = ""



    }

}
