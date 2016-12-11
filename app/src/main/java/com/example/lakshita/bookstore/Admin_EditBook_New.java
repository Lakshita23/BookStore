package com.example.lakshita.bookstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Admin_EditBook_New extends AppCompatActivity {

    TextView tv_bookDetail;
    EditText editText_copies;
    String copies, receivedISBN, receivedCopies, receivedTitle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__edit_book__new);

        Intent fromDisplayListView = getIntent();

        // Receive intent
        receivedISBN = fromDisplayListView.getStringExtra("passedISBN");
        receivedTitle = fromDisplayListView.getStringExtra("passedTitle");
        receivedCopies = fromDisplayListView.getStringExtra("passedCopies");

        Toast.makeText(this, receivedISBN + " " + receivedCopies, Toast.LENGTH_SHORT).show();

        tv_bookDetail = (TextView)findViewById(R.id.textView_adminEditBookCount);
        editText_copies = (EditText)findViewById(R.id.editText_editCopies);

        String details = "ISBN: " + receivedISBN + "\n"
                + "Title: " + receivedTitle +  "\n";

        tv_bookDetail.setText(details);
        editText_copies.setText(receivedCopies);

        // Check if there's any changes?
        //copies = editText_copies.getText().toString().trim();
    }


    // When user click on update button
    public void updateBookCount(View view){

        // Start BackgroundTask

        copies = editText_copies.getText().toString().trim();
        Toast.makeText(this, copies, Toast.LENGTH_SHORT).show();

        class EditCount extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Admin_EditBook_New.this,"Adding...",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_COUNT_ISBN,receivedISBN);
                params.put(Config.KEY_COUNT_COUNT,copies);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_UPDATECOPIES, params);
                return res;
            }
        }

        EditCount editCount = new EditCount();
        editCount.execute();
    }


}
