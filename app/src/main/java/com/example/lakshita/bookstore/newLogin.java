package com.example.lakshita.bookstore;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class newLogin extends AppCompatActivity {

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

/*        // Capture button click
        btn_Login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                invokeLogin();
            }
        });*/

        // Capture Click on "Register Link"
        textView_Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start Register.class
                Intent myIntent = new Intent(newLogin.this, Registration.class);
                startActivity(myIntent);
            }
        });
    }

    public void invokeLogin(View view) {
        String loginid = editText_LoginLoginID.getText().toString();
        String password = editText_LoginPassword.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, loginid, password);
    }
}
