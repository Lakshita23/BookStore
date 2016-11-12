package com.example.lakshita.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    Button btn_Login;
    TextView textView_Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Locate widgets in activity_login.xml
        btn_Login= (Button) findViewById(R.id.button_login);
        textView_Register = (TextView) findViewById(R.id.textView_RegisterLink);

        // Capture button click
        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start MainActivity_WithNavigation.class
                Intent myIntent = new Intent(Login.this,
                        MainActivity_WithNavigation.class);
                startActivity(myIntent);
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
}
