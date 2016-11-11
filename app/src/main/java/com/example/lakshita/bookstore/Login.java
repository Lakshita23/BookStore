package com.example.lakshita.bookstore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Locate the button in activity_login.xml
        btn_Login= (Button) findViewById(R.id.button_login);

        // Capture button clicks
        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start MainActivity_WithNavigation.class
                Intent myIntent = new Intent(Login.this,
                        MainActivity_WithNavigation.class);
                startActivity(myIntent);
            }
        });
    }
}
