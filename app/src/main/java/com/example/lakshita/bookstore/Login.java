package com.example.lakshita.bookstore;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Login extends AppCompatActivity {

    /** Variables **/
    private Button btn_Login;
    private TextView textView_Register;
    private EditText editText_LoginLoginID, editText_LoginPassword;
    private String loginid, password, admins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Locate widgets in activity_login.xml
        editText_LoginLoginID = (EditText)findViewById(R.id.editText_LoginLoginID);
        editText_LoginPassword = (EditText)findViewById(R.id.editText_LoginPassword);
        btn_Login= (Button) findViewById(R.id.button_login);
        textView_Register = (TextView) findViewById(R.id.textView_RegisterLink);

        // Capture button click
        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                invokeLogin();
            }
        });

        // Capture Click on "Register Link"
        textView_Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start Register.class
                Intent myIntent = new Intent(Login.this,
                        Registration.class);
                startActivity(myIntent);
            }
        });
    }

    private void invokeLogin(){
        loginid = editText_LoginLoginID.getText().toString();
        password = editText_LoginPassword.getText().toString();

        LoginBackGround loginBackGround = new LoginBackGround();
        loginBackGround.execute(loginid, password);
    }
    class LoginBackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String loginid = params[0];
            String password = params[1];

            String data="";
            int tmp;

            try {
                URL url = new URL("http://bookdb.16mb.com/login_hetty.php");
                String urlParams = "loginid="+loginid+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        // After retrieving
        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {

                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("result");


                //loginid = user_data.getString("loginid").toString();
                //password = user_data.getString("password").toString();
                admins = user_data.getString("admins").toString();

                Toast.makeText(Login.this, "ADMIN?!" + admins, Toast.LENGTH_SHORT).show();


            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }
/*            Intent i = new Intent(Login.this, User_MainActivity.class);
            i.putExtra("loginid", loginid);
            i.putExtra("password", password);
            i.putExtra("err", err); // Error
            startActivity(i);*/


        }
    }


}
