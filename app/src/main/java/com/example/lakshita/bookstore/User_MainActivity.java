package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class User_MainActivity extends AppCompatActivity {

    private String JSON_STRING;
    String UserBookJsonString;
    String[] JSON_STRINGS=new String[4];
    String myJSON1,myJSON2,myJSON3,myJSON4=null;
    // Variables For Intent
    String receivedloginid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__main);

        // Run backgroundTask to get all books for users
        new UserGetBooksBackgroundTask().execute();
//        GetDataJSON1 g1 = new GetDataJSON1();
//        g1.execute();
//
//        GetDataJSON2 g2 = new GetDataJSON2();
//        g2.execute();
//
//        GetDataJSON3 g3 = new GetDataJSON3();
//        g3.execute();
//
//        GetDataJSON4 g4 = new GetDataJSON4();
//        g4.execute();






        Intent fromLogin = getIntent();

        // Receive intent
        receivedloginid = fromLogin.getStringExtra("loginid");
        Toast.makeText(this, receivedloginid, Toast.LENGTH_SHORT).show();

//          getData1();
//          getData2();
//          getData3();
         // getData4();

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


//    public void getData1(){
        class GetDataJSON1 extends AsyncTask<String, Void, String> {
            String JSON_URL;

            @Override
            protected void onPreExecute() {
                JSON_URL = "http://bookdb.16mb.com/part3q1.php";
            }


            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected String doInBackground(String... params) {
                String loginid = params[0];

                try {
                    URL url = new URL(JSON_URL);
                    String urlParams = "loginid="+receivedloginid;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRINGS[0]= bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRINGS[0] + "\n");
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    inputStream.close();
                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON1=result;
              //  Toast.makeText(user_recc.this, myJSON1, Toast.LENGTH_SHORT).show();
            }
        }
//        GetDataJSON1 g1 = new GetDataJSON1();
//        g1.execute();
//    }

//    public void getData2(){
        class GetDataJSON2 extends AsyncTask<String, Void, String> {
            String JSON_URL;

            @Override
            protected void onPreExecute() {
                JSON_URL= "http://bookdb.16mb.com/part3q2.php";
            }


            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected String doInBackground(String... params) {
                String loginid = params[0];
                try {
                    URL url = new URL(JSON_URL);
                    String urlParams = "loginid="+receivedloginid;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRINGS[1] = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRINGS[1]+ "\n");
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    inputStream.close();
                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON2=result;
//                Toast.makeText(user_recc.this, myJSON2, Toast.LENGTH_SHORT).show();
//                showList2();
            }
        }
//        GetDataJSON2 g2 = new GetDataJSON2();
//        g2.execute();
//    }

 //   public void getData3(){
        class GetDataJSON3 extends AsyncTask<String, Void, String> {
            String JSON_URL;

            @Override
            protected void onPreExecute() {
                JSON_URL= "http://bookdb.16mb.com/part3q3.php";
            }


            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected String doInBackground(String... params) {
                String loginid = params[0];

                try {
                    URL url = new URL(JSON_URL);
                    String urlParams = "loginid="+receivedloginid;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRINGS[2] = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRINGS[2]+ "\n");
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    inputStream.close();
                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON3=result;
//                Toast.makeText(user_recc.this, myJSON3, Toast.LENGTH_SHORT).show();
//                showList3();
            }
        }
//        GetDataJSON3 g3 = new GetDataJSON3();
//        g3.execute();
//    }

//    public void getData4(){
        class GetDataJSON4 extends AsyncTask<String, Void, String> {
            String JSON_URL;

            @Override
            protected void onPreExecute() {
                JSON_URL= "http://bookdb.16mb.com/part3q4.php";
            }


            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected String doInBackground(String... params) {
                String loginid = params[0];

                try {
                    URL url = new URL(JSON_URL);
                    String urlParams = "loginid="+receivedloginid;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((JSON_STRINGS[3] = bufferedReader.readLine()) != null) {
                        stringBuilder.append(JSON_STRINGS[3]+ "\n");
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    inputStream.close();
                    return stringBuilder.toString().trim();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result){
                myJSON4=result;
//                Toast.makeText(user_recc.this, myJSON4, Toast.LENGTH_SHORT).show();
//                showList4();
            }
        }
//        GetDataJSON4 g4 = new GetDataJSON4();
//        g4.execute();
   // }

    public void ViewUserRecords(View view){
        Intent toViewUserRecord = new Intent(User_MainActivity.this, user_recc.class);
        toViewUserRecord.putExtra("loginid", receivedloginid);
        toViewUserRecord.putExtra("myJSON1",myJSON1);
        toViewUserRecord.putExtra("myJSON2",myJSON2);
        toViewUserRecord.putExtra("myJSON3",myJSON3);
        toViewUserRecord.putExtra("myJSON4",myJSON4);
        startActivity(toViewUserRecord);
    }


    public  void BrowseBooks(View view){
        Intent myIntent = new Intent(User_MainActivity.this, BookBrowse.class);
        myIntent.putExtra("loginid", receivedloginid);
        startActivity(myIntent);
    }

    // When user click on the "Browse all books" button
    // Implemented in activity_admin_main_page.xml
    public void parseJSON1(View view){
        if(UserBookJsonString == null){
            Toast.makeText(this, "Refresh to get JSON", Toast.LENGTH_SHORT).show();
        }else{

            Intent booksListView = new Intent(this, UserDisplayListView.class);
            booksListView.putExtra("json_data", UserBookJsonString);
            booksListView.putExtra("loginid",receivedloginid);
            startActivity(booksListView);
        }
    }

    public void Admin_Page(View view){
        Intent booksListView = new Intent(this, UserDisplayListView.class);
        startActivity(booksListView);
    }

//    public  void ViewAllBooks(View view){
//        Intent myIntent = new Intent(User_MainActivity.this, BookBrowse.class);
//        myIntent.putExtra("loginid", receivedloginid);
//        startActivity(myIntent);
//    }


}
