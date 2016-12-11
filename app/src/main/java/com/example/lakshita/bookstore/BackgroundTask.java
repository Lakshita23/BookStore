package com.example.lakshita.bookstore;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundTask extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    // Constructor
    Context context;
    BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Information");
    }

    @Override
    protected String doInBackground(String... params) {
        System.out.println("before SERVER");
        System.out.println(params[0]);
        String registerURL = "http://bookdb.16mb.com/register.php";
        String loginURL = "http://bookdb.16mb.com/login.php";

        // To pass info to DB, depend on the actions (Register/Login etc)
        String method = params[0];

        if (method.equals("register")){
            String loginid = params[1];
            String fullname = params[2];
            String password = params[3];
            String credit_card = params[4];
            String address = params[5];
            String phone = params[6];
            //Integer creditCard = Integer.parseInt(params[4]);
            //Integer phone = Integer.parseInt(params[6]);

            // To create httpUrlConnection
            try {
                URL url = new URL(registerURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                // Create stream
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                // Encode data
                // ("name","UTF-8") : name is the identifier for the server side
                String data = URLEncoder.encode("loginid","UTF-8")+"="+ URLEncoder.encode(loginid,"UTF-8")+"&"+
                        URLEncoder.encode("fullname","UTF-8")+"="+ URLEncoder.encode(fullname,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("credit_card","UTF-8")+"="+ URLEncoder.encode(credit_card,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+ URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+ URLEncoder.encode(phone,"UTF-8");

                // Write data into BufferedWriter
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                System.out.println("Upload Data");
                // Get feedback from Server
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
//                Toast.makeText(context,"Send to Server",Toast.LENGTH_LONG).show();
                System.out.println("AFTER SERVER");
                return "Successfully Registered!";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("login")){
            String loginid = params[1];
            String password = params[2];

            try {
                URL url = new URL(loginURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                // Create stream
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                // Encode data
                // ("name","UTF-8") : name is the identifier for the server side
                String data = URLEncoder.encode("loginid","UTF-8")+"="+ URLEncoder.encode(loginid,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(password,"UTF-8");

                // Write data into BufferedWriter
                bufferedWriter.write(data);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                System.out.println("Upload Data");
                // Get feedback from Server
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                String line = "";
                while((line=bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
//                Toast.makeText(context,"Send to Server",Toast.LENGTH_LONG).show();
                System.out.println("AFTER SERVER");
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(String result) {
        if (result.equals("Successfully Registered!"))
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        else{
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }
}
