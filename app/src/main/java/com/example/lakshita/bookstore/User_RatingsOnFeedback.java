package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

// When user click on Feedback to Rate
public class User_RatingsOnFeedback extends AppCompatActivity {

    String receivedloginid, receivedISBN, receivedFtext,receivedScore,receivedFID;
    TextView textview_bookDetails;
    RatingBar ratingsOnFeedbacks;
    Button submitRatings;
    String rate;

    String bookDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__ratings_on_feedback);

        // Locate widgets in activity_user_ratings_on_feedback.xml
        ratingsOnFeedbacks = (RatingBar)findViewById(R.id.ratingBar_ratingOnFeedback);
        submitRatings = (Button)findViewById(R.id.button_submitRating);

        // Receive Intent
        Intent fromFeedback = getIntent();
        receivedloginid = fromFeedback.getStringExtra("passedloginid"); // Current User

        receivedISBN = fromFeedback.getStringExtra("passedISBN");
        receivedFtext = fromFeedback.getStringExtra("passedFtext");
        receivedScore = fromFeedback.getStringExtra("passedscore");
        receivedFID = fromFeedback.getStringExtra("passedFID");

        Toast.makeText(this, receivedFID + receivedloginid + receivedISBN + receivedFtext + receivedScore, Toast.LENGTH_SHORT).show();

        if (receivedFtext.isEmpty()){
            receivedFtext = "-";
        }
        bookDetails = "Book ISBN: " + receivedISBN +
                "\n" + "Feedback: " + receivedFtext +
                "\n" + "Score Given:" + receivedScore;

        textview_bookDetails = (TextView)findViewById(R.id.textView_FeedbackDetails);
        textview_bookDetails.setText(bookDetails);
    }

    // When user click on submit Ratings button
    public void RatingforFeedback(View view){
        rate = String.valueOf(ratingsOnFeedbacks.getRating());

        // Start BackgroundTask
        class RatingsBackgroundTask extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(User_RatingsOnFeedback.this,"Adding...",null, true, true);
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
                params.put(Config.KEY_RATING_FID,receivedFID);
                params.put(Config.KEY_RATING_LOGINID,receivedloginid); // User
                params.put(Config.KEY_RATING_ISBN,receivedISBN);
                params.put(Config.KEY_RATING_RATE,rate);

                RequestHandler requestHandler = new RequestHandler();
                String result = requestHandler.sendPostRequest(Config.URL_ADDRATING, params);
                return null;
            }
        }

        RatingsBackgroundTask submitRating = new RatingsBackgroundTask();
        submitRating.execute();

        // Clear Form
        ratingsOnFeedbacks.setRating(0);

    }
}
