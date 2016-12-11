package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lakshita on 12/9/2016.
 */

public class BookInformation extends AppCompatActivity {

    ArrayList<String> info;
    TextView title, author, isbn, publisher, price, copies ,year;
    Button back, order;
    NumberPicker numpick;
    int num_of_copy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);

        final Intent intent = getIntent();
        info = new ArrayList<>();
        info = intent.getStringArrayListExtra("Info");
        System.out.println(info.get(1));

        title = (TextView)findViewById(R.id.title_view);
        author = (TextView)findViewById(R.id.author_view);
        isbn = (TextView)findViewById(R.id.isbn_view);
        publisher = (TextView)findViewById(R.id.publisher_view);
        price = (TextView)findViewById(R.id.price_view);
        copies = (TextView)findViewById(R.id.copies_view);
        year = (TextView)findViewById(R.id.year_view);
        back = (Button)findViewById(R.id.backtobrowse);
        order = (Button)findViewById(R.id.orderbtn);
        numpick = (NumberPicker)findViewById(R.id.numberPicker);

        title.setText(info.get(1));
        author.setText(info.get(2));
        isbn.setText(info.get(0));
        publisher.setText(info.get(3));
        price.setText(info.get(5));
        copies.setText(info.get(4));
        year.setText(info.get(7));
        numpick.setMinValue(0);
        numpick.setMaxValue(Integer.parseInt(info.get(4)));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBrowse= new Intent(BookInformation.this, BookBrowse.class);
                openBrowse.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(openBrowse);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_of_copy = numpick.getValue();
                ArrayList<String> orderarr = new ArrayList<String>();
                orderarr.add(info.get(1));
                orderarr.add(info.get(2));
                orderarr.add(info.get(3));
                orderarr.add(String.valueOf(num_of_copy));
                orderarr.add(info.get(0));
                orderarr.add(info.get(8));
                Intent myIntent = new Intent(BookInformation.this, CartActivity.class);
                myIntent.putStringArrayListExtra("Order", orderarr);
                startActivity(myIntent);
            }
        });
    }


}
