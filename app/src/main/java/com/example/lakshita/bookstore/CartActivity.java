package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Lakshita on 12/9/2016.
 */

public class CartActivity extends AppCompatActivity {

    private static final String ORDER_URL = "http://10.13.36.73/bookstore/order.php";

    TextView title,author,publisher,copies, orderid, date;
    ArrayList<String> orderinfo;
    Button order;
    String oid, loginid, ISBN, o_date, o_status, quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        orderinfo = new ArrayList<>();
        Intent intent = getIntent();
        orderinfo = intent.getStringArrayListExtra("Order");

        title = (TextView)findViewById(R.id.title_cart);
        author = (TextView)findViewById(R.id.author_cart);
        publisher = (TextView)findViewById(R.id.publisher_cart);
        copies = (TextView)findViewById(R.id.copy_cart);
        orderid = (TextView)findViewById(R.id.order_id);
        date = (TextView)findViewById(R.id.date_cart);

        title.setText(orderinfo.get(0));
        author.setText(orderinfo.get(1));
        publisher.setText(orderinfo.get(2));
        copies.setText(orderinfo.get(3));
        Random generator = new Random();
        int i = generator.nextInt(11) + 1;
        String id = i+"";
        orderid.setText(id);
        date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }

    public void placeOrder(View view){
        oid = orderid.getText().toString();
        loginid = orderinfo.get(5);
        ISBN = orderinfo.get(4);
        o_date = date.getText().toString();
        o_status = "shipping";
        quantity = copies.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ORDER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CartActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CartActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("oid", oid);
                params.put("loginid", loginid);
                params.put("isbn", ISBN);
                params.put("date", o_date);
                params.put("status", o_status);
                params.put("qty", quantity);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
