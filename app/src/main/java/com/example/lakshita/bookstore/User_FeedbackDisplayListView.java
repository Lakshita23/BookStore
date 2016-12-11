package com.example.lakshita.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class User_FeedbackDisplayListView extends AppCompatActivity implements ListView.OnItemClickListener{

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    FeedbackAdapter feedbackAdapter;
    ListView listView;
    //String fid;

    String receivedloginid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__feedback_display_list_view);

        feedbackAdapter = new FeedbackAdapter(this, R.layout.row_layout_feedback);
        listView = (ListView)findViewById(R.id.feedbackListView);
        listView.setOnItemClickListener(this); // To make listView clickable; Go to ratings
        listView.setAdapter(feedbackAdapter);


        // Get data from intent (User_BookDetailsAndFeedback)
        json_string = getIntent().getExtras().getString("allFeedbacks"); // JSON String
        receivedloginid = getIntent().getExtras().getString("loginid");
        //Toast.makeText(this, json_string, Toast.LENGTH_SHORT).show();

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("result");

            int count = 0;
            String isbn, fText, score, FID;

            //Parse json string
            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                isbn = jo.getString("ISBN");
                fText = jo.getString("Ftext");
                score = jo.getString("score");
                FID = jo.getString("FID");

                // Toast.makeText(this,score, Toast.LENGTH_SHORT).show();
                Feedback feedback = new Feedback(isbn, fText, score, FID);
                // Pass object to adapter
                feedbackAdapter.add(feedback);
                count += 1;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // If user click on any Feedback Row
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent goToRatingsToFeedback = new Intent(this, User_RatingsOnFeedback.class);

        //Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
        TextView tv_isbn = (TextView)view.findViewById(R.id.tx_feedback_isbn);  //initialize text view withid label
        String passisbn = tv_isbn.getText().toString(); //get the text

        TextView tv_fText = (TextView)view.findViewById(R.id.tx_feedback_fText);
        String passFtext = tv_fText.getText().toString(); //get the text

        TextView tv_Score = (TextView)view.findViewById(R.id.tx_feedback_score);
        String passscore = tv_Score.getText().toString(); //get the text

        TextView tv_FID = (TextView)view.findViewById(R.id.tx_feedback_FID);
        String passFID = tv_FID.getText().toString(); //get the text



        goToRatingsToFeedback.putExtra("passedloginid",receivedloginid);
        goToRatingsToFeedback.putExtra("passedISBN",passisbn);
        goToRatingsToFeedback.putExtra("passedFtext",passFtext);
        goToRatingsToFeedback.putExtra("passedscore",passscore);
        goToRatingsToFeedback.putExtra("passedFID", passFID);


        startActivity(goToRatingsToFeedback);
    }
}
