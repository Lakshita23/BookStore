package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Registration extends AppCompatActivity {

    /** Variables **/
    private EditText editText_Registerfullname, editText_RegisterLoginID , editText_RegisterPassword, editText_RegisterCreditCard, editText_RegisterAddress, editText_RegisterPhone;
    private String registerName, registerLoginid,registerPassword,registerCreditCard, registerAddress,registerPhone;
    private Button btn_Register;

    private static final String RegisterURL = "http://bookdb.16mb.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Locate widgets in activity_Registration.xml
        editText_Registerfullname = (EditText)findViewById(R.id.editText_Registerfullname);
        editText_RegisterLoginID = (EditText)findViewById(R.id.editText_RegisterLoginID);
        editText_RegisterPassword = (EditText)findViewById(R.id.editText_RegisterPassword);
        editText_RegisterCreditCard= (EditText)findViewById(R.id.editText_RegisterCreditCard);
        editText_RegisterAddress = (EditText)findViewById(R.id.editText_RegisterAddress);
        editText_RegisterPhone = (EditText)findViewById(R.id.editText_RegisterPhone);

        btn_Register = (Button)findViewById(R.id.button_Register);

        // On click
        btn_Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                registerUserParameters();
            }
        });

    }

    private void registerUserParameters(){
        registerName = editText_Registerfullname.getText().toString().trim();
        registerLoginid =  editText_RegisterLoginID.getText().toString().trim();
        registerPassword = editText_RegisterPassword.getText().toString().trim();
        registerCreditCard = editText_RegisterCreditCard.getText().toString().trim();
        registerAddress = editText_RegisterAddress.getText().toString().trim();
        registerPhone = editText_RegisterPhone.getText().toString().trim();

        register(registerName,registerLoginid,registerPassword,registerCreditCard,registerPhone ,registerAddress);
    }

    private void register(String registerName,String registerLoginid,String registerPassword,String registerCreditCard,String registerPhone ,String registerAddress) {
        class registerUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registration.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("loginid",params[0]);
                data.put("fullname",params[1]);
                data.put("password",params[2]);
                data.put("credit_card",params[3]);
                data.put("address",params[4]);
                data.put("phone",params[5]);


                String result = ruc.sendPostRequest(RegisterURL,data);

                return  result;
            }
        }

        registerUser ru = new registerUser();
        ru.execute(registerName,registerLoginid,registerPassword,registerCreditCard,registerPhone ,registerAddress);
    }


}



