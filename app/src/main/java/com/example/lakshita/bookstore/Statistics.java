package com.example.lakshita.bookstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lakshita on 12/10/2016.
 */

public class Statistics extends AppCompatActivity {

    private static final String STATS_URL = "http://bookdb.16mb.com/stats.php";
    private static final String STATS_JSON = "http://bookdb.16mb.com/stats.json";

    RequestQueue requestQueue;
    EditText m;
    Button author, books, publisher;
    ListView statslist;
    String date;
    HashMap<Integer,List<String>> statsMap;
    CustomAdapterStats customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        requestQueue = Volley.newRequestQueue(this);

        m = (EditText)findViewById(R.id.num_m);
        author = (Button)findViewById(R.id.author_m);
        books = (Button)findViewById(R.id.book_m);
        publisher = (Button)findViewById(R.id.publisher_m);
        statslist = (ListView)findViewById(R.id.stat_list);

//        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        date = "2017-12-1";
        statsMap = new HashMap<>();

        getStats();

        author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAdapter.setSelection(1);
                customAdapter.notifyDataSetChanged();
            }
        });
        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAdapter.setSelection(2);
                customAdapter.notifyDataSetChanged();
            }
        });
        publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAdapter.setSelection(3);
                customAdapter.notifyDataSetChanged();

            }
        });
    }

    private void getStats(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, STATS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Statistics.this, response, Toast.LENGTH_LONG).show();
                        if (!response.isEmpty())
                            jsonDisplay();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Statistics.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("date", date);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void jsonDisplay() {
//        statsMap = new HashMap<>();
        System.out.println("JSON DISPLAY!");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, STATS_JSON, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int length;
                            JSONArray jsonArray = response.getJSONArray("stats");
                            if (!m.getText().toString().equals(""))
                                length = Integer.parseInt(m.getText().toString());
                            else
                                length = jsonArray.length();
                            ArrayList<String> list;
                            for (int i=0;i<length;i++){
                                JSONObject stat = jsonArray.getJSONObject(i);
                                list = new ArrayList<>();
                                list.add(stat.getString("amt_sold"));
                                list.add(stat.getString("ISBN"));
                                list.add(stat.getString("title"));
                                list.add(stat.getString("author"));
                                list.add(stat.getString("publisher"));
                                list.add(stat.getString("year_published"));
                                list.add(stat.getString("copies"));
                                list.add(stat.getString("price"));
                                statsMap.put(i,list);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        System.out.println("HASHMAP: "+statsMap);
                        statslist.invalidate();
                        customAdapter = new CustomAdapterStats(getApplicationContext(), statsMap);
                        statslist.setAdapter(customAdapter);
                        customAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY","ERROR");

                    }
                }

        );
        requestQueue.add(jsonObjectRequest);

    }
}
