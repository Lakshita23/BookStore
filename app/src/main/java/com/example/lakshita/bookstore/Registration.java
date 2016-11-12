package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Registration extends AppCompatActivity {

    /** Variables **/
    EditText editText_Name, editText_ChooseUserName, editText_ChoosePassword, editText_CreditCard, editText_Address, editText_Phone;
    String name, chooseUsername,choosePassword,address, creditCard,phone;
    Button btn_Register;

    private static final String RegisterURL = "http://192.168.1.3/webapp/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Locate widgets in activity_Registration.xml
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
        name = editText_Name.getText().toString();
        chooseUsername = editText_ChooseUserName.getText().toString();
        choosePassword = editText_ChoosePassword.getText().toString();
        creditCard = editText_CreditCard.getText().toString();
        address = editText_Address.getText().toString();
        phone = editText_Phone.getText().toString();

        register(name,chooseUsername,choosePassword,creditCard,address,phone);
    }

    private void register(String name, String chooseUsername, String choosePassword, String creditCard, String address, String phone){
        String urlSuffix = "?name="+name+"&username="+chooseUsername+"&password="+choosePassword+"&creditCard="+creditCard+"&address="+address+"&phone="+phone;

        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registration.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(RegisterURL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}



