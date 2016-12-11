package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// Main Page :
// 1. Add Books
// 2. View All Books -> Update Books
public class Admin_MainPage extends AppCompatActivity {

    private String JSON_STRING;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__main_page);


        new AdminGetBooksBackgroundTask().execute(); // Load all Books in JSON format


    }
    class AdminGetBooksBackgroundTask extends AsyncTask<Void, Void, String> {

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
            //TextView textView = (TextView) findViewById(R.id.books_textView);

            //JSON data. Set result into this textView
            //textView.setText(result);
            //Toast.makeText(Admin_MainPage.this, result, Toast.LENGTH_SHORT).show();

            jsonString = result;
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

    // Implemented in activity_admin_main_page.xml
    public void parseJSON(View view){
        if(jsonString == null){
            Toast.makeText(this, "Refresh to get JSON", Toast.LENGTH_SHORT).show();
        }else{

            Intent booksListView = new Intent(this, DisplayListView.class);
            booksListView.putExtra("json_data", jsonString);
            startActivity(booksListView);
        }
    }

    public void addBookActivityFunction(View view){
        Intent toAddBookActivity = new Intent(this, AddBook.class);
        startActivity(toAddBookActivity);
    }
}
