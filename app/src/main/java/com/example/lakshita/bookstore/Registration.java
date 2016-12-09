package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private static final String REGISTER_URL = "http://10.13.34.223/bookstore/register.php";

    EditText editText_Name, editText_ChooseUserName, editText_ChoosePassword, editText_CreditCard, editText_Address, editText_Phone;
    String loginid, fullname, password, address, credit_card, phone;
    Button btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editText_Name = (EditText)findViewById(R.id.editText_Name);
        editText_ChooseUserName = (EditText)findViewById(R.id.editText_ChooseUsername);
        editText_ChoosePassword = (EditText)findViewById(R.id.editText_ChoosePassword);
        editText_CreditCard = (EditText)findViewById(R.id.editText_CreditCard);
        editText_Address = (EditText)findViewById(R.id.editText_Address);
        editText_Phone = (EditText)findViewById(R.id.editText_Phone);

        btn_Register = (Button)findViewById(R.id.button_Register);

        // On click
        btn_Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        loginid = editText_Name.getText().toString();
        fullname = editText_ChooseUserName.getText().toString();
        password = editText_ChoosePassword.getText().toString();
        credit_card = editText_CreditCard.getText().toString();
        address = editText_Address.getText().toString();
        phone = editText_Phone.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Registration.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registration.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("loginid", loginid);
                params.put("fullname", fullname);
                params.put("password", password);
                params.put("credit_card", credit_card);
                params.put("address", address);
                params.put("phone", phone);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
