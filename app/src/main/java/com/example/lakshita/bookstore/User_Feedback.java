package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class User_Feedback extends AppCompatActivity {

    String receivedISBN, receivedNumOfFeedbacks;
    String JSON_STRING;
    String feedbackjsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__feedback_view);

        // Receive intent
        Intent fromBookDetailsAndFeedback = getIntent();
        receivedISBN = fromBookDetailsAndFeedback.getStringExtra("passedISBN");
        receivedNumOfFeedbacks = fromBookDetailsAndFeedback.getStringExtra("numOfFeedbacks");

        // Display Listview based on the number of Feedbacks
        // new UserGetFeedbacksBackgroundTask().execute();


        //parseJSON();
    }

    /*class UserGetFeedbacksBackgroundTask extends AsyncTask<Void, Void, String> {
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
            Toast.makeText(User_Feedback.this, feedbackjsonString, Toast.LENGTH_SHORT).show();
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
    }*/

   /* private void getFeedback(){
        class GetFeedback extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(User_Feedback.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showFeedback(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GETFEEDBACK,receivedISBN);
                return s;
            }
        }
        GetFeedback ge = new GetFeedback();
        ge.execute();
    }

    private void showFeedback(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String Ftext = c.getString(Config.TAG_GET_FEEDBACK_FTEXT);
            String score = c.getString(Config.TAG_GET_FEEDBACK_SCORE);

            Toast.makeText(this, "hey!" + Ftext + " " + score, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
*/
    /*public void parseJSON(){
        if(feedbackjsonString == null){
            Toast.makeText(this, "Refresh to get JSON", Toast.LENGTH_SHORT).show();
        }else{

            Intent booksListView = new Intent(this, User_FeedbackDisplayListView.class);
            booksListView.putExtra("json_data", feedbackjsonString);
            startActivity(booksListView);
        }
    }*/
}
