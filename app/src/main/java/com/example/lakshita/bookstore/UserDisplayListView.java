package com.example.lakshita.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDisplayListView extends AppCompatActivity implements ListView.OnItemClickListener{

    String user_json_string, receivedloginid;
    JSONObject userjsonObject;
    JSONArray userjsonArray;
    BookAdapter userBookAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        userBookAdapter = new BookAdapter(this, R.layout.row_layout);
        listView = (ListView)findViewById(R.id.bookListView);
        listView.setOnItemClickListener(this); // To make listView clickable
        listView.setAdapter(userBookAdapter);


        // Get data from intent (previous activity page)
        user_json_string = getIntent().getExtras().getString("json_data");
        receivedloginid = getIntent().getExtras().getString("loginid");

        try {
            userjsonObject = new JSONObject(user_json_string);
            userjsonArray = userjsonObject.getJSONArray("result");

            int count = 0;
            String isbn, title, copies;

            //Parse json string
            while(count < userjsonArray.length()){
                JSONObject jo = userjsonArray.getJSONObject(count);
                isbn = jo.getString("ISBN");
                title = jo.getString("title");
                copies = jo.getString("copies");

                Books book = new Books(isbn, title, copies);
                // Pass object to adapter
                userBookAdapter.add(book);
                count += 1;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // If user click on any listview row
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent goToEditBook = new Intent(this, User_BookDetailsAndFeedback.class);
        // Get position of item clicked
        // HashMap<String,String> map =(HashMap)adapterView.getItemAtPosition(position);
        //goToEditBook.putExtra("position",position);
        //String selected = (String)listView.getItemAtPosition(position);

        //String data = (String)listView.getAdapter().getItem(position);

        //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        TextView tv = (TextView)view.findViewById(R.id.tx_isbn);  //initialize text view withid label
        String passisbn = tv.getText().toString(); //get the text

        TextView tv_title = (TextView)view.findViewById(R.id.tx_title);
        String passtitle = tv_title.getText().toString(); //get the text

        TextView tv_copies = (TextView)view.findViewById(R.id.tx_copies);
        String passcopies = tv_copies.getText().toString(); //get the text



        goToEditBook.putExtra("passedISBN",passisbn);
        goToEditBook.putExtra("passedTitle",passtitle);
        goToEditBook.putExtra("passedCopies",passcopies);
        goToEditBook.putExtra("loginid",receivedloginid);

        startActivity(goToEditBook);
    }
}
