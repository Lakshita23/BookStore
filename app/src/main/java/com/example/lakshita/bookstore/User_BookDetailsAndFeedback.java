package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class User_BookDetailsAndFeedback extends AppCompatActivity {

    String receivedloginid, receivedISBN,receivedTitle, receivedCopies, DateToStr,numOfFeedbacks;
    EditText optionalFeedbackText;

    RatingBar feedbackRating;
    Button buttonFeedback;
    String addRatings, addFeedback;
    TextView feedbackDLV, feedbackPagebookDetails;

    String JSON_STRING;
    String feedbackjsonString;
    String bookInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__book_details_and_feedback);

        Intent fromUserDisplayListView = getIntent();

        // Receive intent
        receivedloginid = fromUserDisplayListView.getStringExtra("loginid");
        receivedISBN = fromUserDisplayListView.getStringExtra("passedISBN");
        receivedTitle = fromUserDisplayListView.getStringExtra("passedTitle");
        receivedCopies = fromUserDisplayListView.getStringExtra("passedCopies");

        // Locate widgets in activity_user_book_details_and_Feedback.xml
        optionalFeedbackText = (EditText)findViewById(R.id.editText_optionalFeedbackText);
        feedbackRating = (RatingBar)findViewById(R.id.ratingBar_feedback);
        buttonFeedback = (Button)findViewById(R.id.button_submitFeedback);
        feedbackDLV = (TextView)findViewById(R.id.textView_viewAllFeedbacks);
        feedbackPagebookDetails = (TextView)findViewById(R.id.textView_feedbackBookDetails);

        // Toast.makeText(this, receivedloginid + receivedISBN + receivedTitle + receivedCopies, Toast.LENGTH_SHORT).show();

        // Display Book Information - ISBN, Title
        bookInformation = "ISBN: " + receivedISBN + "\n" +
                "Title: " + receivedTitle + " ";

        feedbackPagebookDetails.setText(bookInformation);

        new UserGetFeedbacksBackgroundTask().execute();

        // Store user details + bookISBN + date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateToStr = dateFormat.format(currentDate);

        // Capture Click on "View All Feedback Link"
//        feedbackDLV.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//
//                // Toast.makeText(User_BookDetailsAndFeedback.this, "fdgshfjasdg'ds" + feedbackjsonString, Toast.LENGTH_SHORT).show();
//
//                // Start User_Feedback.class
//                Intent seeAllFeedback = new Intent(User_BookDetailsAndFeedback.this, User_FeedbackDisplayListView.class);
//
//                // Pass ISBN to grab all feedbacks
//                seeAllFeedback.putExtra("loginid", receivedloginid);
//                seeAllFeedback.putExtra("bookISBN", receivedISBN);
//                seeAllFeedback.putExtra("numOfFeedbacks", numOfFeedbacks);
//                seeAllFeedback.putExtra("allFeedbacks", feedbackjsonString);
//
//
//                startActivity(seeAllFeedback);
//            }
//        });

    }
    // At this activity, load all feedbacks


    // When user submits feedback
    // Call BackgroundTask
    public void writeFeedback(View view){

        addRatings = String.valueOf(feedbackRating.getRating());
        addFeedback = optionalFeedbackText.getText().toString().trim();

        // Run BackgroundTask
        class FeedbackBT extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(User_BookDetailsAndFeedback.this,"Adding...",null, true, true);
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
                params.put(Config.KEY_FEEDBACK_TEXT,addFeedback);
                params.put(Config.KEY_FEEDBACK_RATINGS,addRatings);
                params.put(Config.KEY_FEEDBACK_ISBN,receivedISBN);
                params.put(Config.KEY_FEEDBACK_LOGINID,receivedloginid);

                RequestHandler requestHandler = new RequestHandler();
                String result = requestHandler.sendPostRequest(Config.URL_ADDFEEDBACK, params);
                return result;
            }
        }

        FeedbackBT feedbackBT = new FeedbackBT();
        feedbackBT.execute();

        // Clear Form
        optionalFeedbackText.setText("");
        feedbackRating.setRating(0);



    }

    class UserGetFeedbacksBackgroundTask extends AsyncTask<Void, Void, String> {
        String JSON_URL;
        @Override
        protected void onPreExecute() {
            JSON_URL = "http://bookdb.16mb.com/getFeedbacks.php";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            feedbackjsonString = result;
            }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(JSON_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                // Return Stringbuilder
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }






}
