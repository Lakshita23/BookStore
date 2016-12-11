package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class User_MainActivity extends AppCompatActivity {

    private String JSON_STRING;
    String UserBookJsonString;

    // Variables For Intent
    String receivedloginid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__main);

        // Run backgroundTask to get all books for users
        new UserGetBooksBackgroundTask().execute();

        Intent fromLogin = getIntent();

        // Receive intent
        receivedloginid = fromLogin.getStringExtra("loginid");
        Toast.makeText(this, receivedloginid, Toast.LENGTH_SHORT).show();

    }

    class UserGetBooksBackgroundTask extends AsyncTask<Void, Void, String> {
        String JSON_URL;
        @Override
        protected void onPreExecute() {
            JSON_URL = "http://bookdb.16mb.com/getAllBooks.php";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            UserBookJsonString = result;
            Toast.makeText(User_MainActivity.this, UserBookJsonString, Toast.LENGTH_SHORT).show();
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

    // When user click on the "Browse all books" button
    // Implemented in activity_admin_main_page.xml
    public void parseJSON(View view){
        if(UserBookJsonString == null){
            Toast.makeText(this, "Refresh to get JSON", Toast.LENGTH_SHORT).show();
        }else{

            Intent booksListView = new Intent(this, UserDisplayListView.class);
            booksListView.putExtra("json_data", UserBookJsonString);
            booksListView.putExtra("loginid",receivedloginid);
            startActivity(booksListView);
        }
    }

}
