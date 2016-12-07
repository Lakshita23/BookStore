package com.example.lakshita.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class EditBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        // Get Intent
        Intent fromDisplayListView = getIntent();

        // Receive intent
        String receivedISBN = fromDisplayListView.getStringExtra("passedISBN");

        Toast.makeText(this, receivedISBN, Toast.LENGTH_SHORT).show();
    }
}
