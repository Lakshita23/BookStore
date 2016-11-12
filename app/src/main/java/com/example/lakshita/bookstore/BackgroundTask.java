package com.example.lakshita.bookstore;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundTask extends AsyncTask<String,Void,String> {


    // Constructor
    Context context;
    BackgroundTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        System.out.println("before SERVER");
        System.out.println(params[0]);
        String registerURL = "http://192.168.1.3/webapp/register.php";
        String loginURL = "http://192.168.1.3/webapp/login.php";

        // To pass info to DB, depend on the actions (Register/Login etc)
        String method = params[0];

        if (method.equals("register")){
            String name = params[1];
            String chooseUsername = params[2];
            String choosePassword = params[3];
            String creditCard = params[4];
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
                String data = URLEncoder.encode("name","UTF-8")+"="+ URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("username","UTF-8")+"="+ URLEncoder.encode(chooseUsername,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+ URLEncoder.encode(choosePassword,"UTF-8")+"&"+
                        URLEncoder.encode("creditCard","UTF-8")+"="+ URLEncoder.encode(creditCard,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+ URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+ URLEncoder.encode(phone,"UTF-8");

                // Write data into BufferedWriter
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                System.out.println("Upload Data");
                // Get feedback from Server
                //InputStream inputStream = httpURLConnection.getInputStream();
                //inputStream.close();
                //Toast.makeText(context,"Send to Server",Toast.LENGTH_LONG).show();
                System.out.println("AFTER SERVER");
                return "Successfully Registered!";


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
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();

    }
}
