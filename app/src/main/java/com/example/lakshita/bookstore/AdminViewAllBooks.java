package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.HashMap;

public class AdminViewAllBooks extends AppCompatActivity implements ListView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_all_books);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }




}
/*    private void showEmployee(){
        //JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {

            JSONObject jsonObject = new JSONObject(JSON_STRING);

            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);


            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String getISBN = jo.getString(Config.TAG_GET_ISBN);
                String getTitle = jo.getString(Config.TAG_GET_TITLE);
                String getAuthor = jo.getString(Config.TAG_GET_AUTHOR);
                String getPublisher = jo.getString(Config.TAG_GET_PUBLISHER);
                String getYear= jo.getString(Config.TAG_GET_YEAR);
                String getCopies= jo.getString(Config.TAG_GET_COPIES);
                String getPrice= jo.getString(Config.TAG_GET_PRICE);
                String getFormat= jo.getString(Config.TAG_GET_FORMAT);
                String getKeywords= jo.getString(Config.TAG_GET_KEYWORDS);
                String getSubject= jo.getString(Config.TAG_GET_SUBJECT);



                HashMap<String,String> books = new HashMap<String,String>();
                books.put(Config.TAG_GET_ISBN,getISBN);
                books.put(Config.TAG_GET_TITLE,getTitle);
                books.put(Config.TAG_GET_AUTHOR,getAuthor);
                books.put(Config.TAG_GET_PUBLISHER,getPublisher);
                books.put(Config.TAG_GET_YEAR,getYear);
                books.put(Config.TAG_GET_COPIES,getCopies);
                books.put(Config.TAG_GET_PRICE,getPrice);
                books.put(Config.TAG_GET_FORMAT,getFormat);
                books.put(Config.TAG_GET_KEYWORDS,getKeywords);
                books.put(Config.TAG_GET_SUBJECT,getSubject);
                list.add(books);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                AdminViewAllBooks.this, list, R.layout.allbookslistitem,
                new String[]{Config.TAG_GET_ISBN,Config.TAG_GET_TITLE,Config.TAG_GET_AUTHOR,Config.TAG_GET_PUBLISHER,Config.TAG_GET_YEAR,Config.TAG_GET_COPIES,Config.TAG_GET_PRICE,Config.TAG_GET_FORMAT,Config.TAG_GET_KEYWORDS,Config.TAG_GET_SUBJECT},
                new int[]{R.id.BookISBN, R.id.BookTitle, R.id.BookAuthor, R.id.BookPublisher, R.id.BookYearPublished, R.id.BookCopies, R.id.BookPrice, R.id.BookFormat, R.id.BookKeywords, R.id.BookSubject});
        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(AdminViewAllBooks.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GETALLBOOK);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}*/


/*    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(admin_viewAllBooks.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showAdminAllBooks();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GETALLBOOK);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    private void showAdminAllBooks(){

        JSONObject jsonObject = null;

        // hashmap : key-value pair
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            Log.d("try block", "try json 0");
            jsonObject = new JSONObject(JSON_STRING);
            Log.d("try block", "try json 1");
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            Log.d("try block", "try json 2");

            for(int i = 0; i<result.length(); i++){

                Log.d("json iteration", "iteration");
                JSONObject jo = result.getJSONObject(i);
                String getISBN = jo.getString(Config.TAG_GET_ISBN);
                String getTitle = jo.getString(Config.TAG_GET_TITLE);
                String getAuthor = jo.getString(Config.TAG_GET_AUTHOR);
                String getPublisher = jo.getString(Config.TAG_GET_PUBLISHER);
                String getYear= jo.getString(Config.TAG_GET_YEAR);
                String getCopies= jo.getString(Config.TAG_GET_COPIES);
                String getPrice= jo.getString(Config.TAG_GET_PRICE);
                String getFormat= jo.getString(Config.TAG_GET_FORMAT);
                String getKeywords= jo.getString(Config.TAG_GET_KEYWORDS);
                String getSubject= jo.getString(Config.TAG_GET_SUBJECT);


                HashMap<String,String> books = new HashMap<String,String>();
                books.put(Config.TAG_GET_ISBN,getISBN);
                books.put(Config.TAG_GET_TITLE,getTitle);
                books.put(Config.TAG_GET_AUTHOR,getAuthor);
                books.put(Config.TAG_GET_PUBLISHER,getPublisher);
                books.put(Config.TAG_GET_YEAR,getYear);
                books.put(Config.TAG_GET_COPIES,getCopies);
                books.put(Config.TAG_GET_PRICE,getPrice);
                books.put(Config.TAG_GET_FORMAT,getFormat);
                books.put(Config.TAG_GET_KEYWORDS,getKeywords);
                books.put(Config.TAG_GET_SUBJECT,getSubject);
                list.add(books);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



        ListAdapter adapter = new SimpleAdapter(
                admin_viewAllBooks.this, list, R.layout.allbookslistitem,
                new String[]{Config.TAG_GET_ISBN,Config.TAG_GET_TITLE,Config.TAG_GET_AUTHOR,Config.TAG_GET_PUBLISHER,Config.TAG_GET_YEAR,Config.TAG_GET_COPIES,Config.TAG_GET_PRICE,Config.TAG_GET_FORMAT,Config.TAG_GET_KEYWORDS,Config.TAG_GET_SUBJECT},
                new int[]{R.id.BookISBN, R.id.BookTitle, R.id.BookAuthor, R.id.BookPublisher, R.id.BookYearPublished, R.id.BookCopies, R.id.BookPrice, R.id.BookFormat, R.id.BookKeywords, R.id.BookSubject});
        listView.setAdapter(adapter);
    }
}*/
