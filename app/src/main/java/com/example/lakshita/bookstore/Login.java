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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

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

    private static final String LOGIN_URL = "http://172.23.25.107/bookstore/login.php";

    Button btn_Login;
    TextView textView_Register;
    EditText id;
    EditText pass;
    String loginid, password,admins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Locate widgets in activity_login.xml
        btn_Login= (Button) findViewById(R.id.button_login);
        textView_Register = (TextView) findViewById(R.id.textView_RegisterLink);
        id = (EditText) findViewById(R.id.loginId);
        pass = (EditText) findViewById(R.id.loginPassword);

        // Capture button click
        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                userLogin();
            }
        });

        // Capture Click on "Register Link"
        textView_Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start Register.class
                Intent myIntent = new Intent(Login.this, Registration.class);
                startActivity(myIntent);
            }
        });
    }

    public void userLogin(){
        loginid = id.getText().toString();
        password = pass.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if (response.equals("success")){
//
//                        }
//                        else
                            Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                        System.out.println("RESPONSE: "+response);
                        if (response.contains("Success"))
                            loginSuccess();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("loginid", loginid);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void loginSuccess(){
        // Start MainActivity.class
        Intent myIntent = new Intent(Login.this, BookBrowse.class);
        myIntent.putExtra("loginid", loginid);
        startActivity(myIntent);
    }

}
