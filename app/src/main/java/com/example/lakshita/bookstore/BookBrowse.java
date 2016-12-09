package com.example.lakshita.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
 * Created by Lakshita on 12/8/2016.
 */

public class BookBrowse extends AppCompatActivity{
    private static final String BROWSE_URL = "http://10.13.36.73/bookstore/bookbrowsing.php";
    private static final String BROWSE_JSON = "http://10.13.36.73/bookstore/bookbrowsing.json";

    HashMap<Integer,ArrayList<String>> books;
    CustomAdapter customAdapter;
    RequestQueue requestQueue;
    GridView simpleGrid;
    EditText author, title, publisher, subject;
    String authorstr, titlestr, publisherstr, subjectstr, orderby, ascdesc;
    Button search, year_asc, year_desc, feedback_asc, feedback_desc;
    String loginid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_browse);
        loginid = getIntent().getStringExtra("loginid");
        requestQueue = Volley.newRequestQueue(this);
        authorstr = "";
        titlestr = "";
        publisherstr = "";
        subjectstr = "";
        orderby = "title";
        ascdesc = " ASC";

        author = (EditText)findViewById(R.id.author_browse);
        title = (EditText)findViewById(R.id.title_browse);
        publisher = (EditText)findViewById(R.id.publisher_browse);
        subject = (EditText)findViewById(R.id.subject_browse);
        search = (Button)findViewById(R.id.searchbtn);
        year_asc = (Button)findViewById(R.id.yearasc);
        year_desc = (Button)findViewById(R.id.yeardesc);
        feedback_asc = (Button)findViewById(R.id.feedbackasc);
        feedback_desc = (Button)findViewById(R.id.feedbackdesc);
        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        books = new HashMap<>();

        customAdapter = new CustomAdapter(getApplicationContext(), books);
        simpleGrid.setAdapter(customAdapter);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(BookBrowse.this, BookInformation.class);
                myIntent.putStringArrayListExtra("Info", books.get(position));
                System.out.println("INFO: "+books.get(position));
                startActivity(myIntent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorstr = "";
                titlestr = "";
                publisherstr = "";
                subjectstr = "";
                if (author.getText().toString()!=null)
                    authorstr = author.getText().toString();
                if (publisher.getText().toString()!=null)
                    publisherstr = publisher.getText().toString();
                if (title.getText().toString()!=null)
                    titlestr = title.getText().toString();
                if (subject.getText().toString()!=null)
                    subjectstr = subject.getText().toString();

                browseSearch();

            }
        });
        year_asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderby = "year_published";
                ascdesc = " ASC";
                browseSearch();
            }
        });
        year_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderby = "year_published";
                ascdesc = " DESC";
                browseSearch();
            }
        });
        feedback_asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderby = "AVG(Feedback.score)";
                ascdesc = " ASC";
                browseSearch();
            }
        });
        feedback_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderby = "AVG(Feedback.score)";
                ascdesc = " DESC";
                browseSearch();
            }
        });
    }

    private void browseSearch() {
        System.out.println("BROWSE SEARCH!!");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BROWSE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(BookBrowse.this, response, Toast.LENGTH_LONG).show();
                        if (!response.isEmpty())
                            jsonDisplay();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookBrowse.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("author", authorstr);
                params.put("title", titlestr);
                params.put("publisher", publisherstr);
                params.put("subject", subjectstr);
                params.put("orderby", orderby);
                params.put("ad", ascdesc);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void jsonDisplay() {
        books = new HashMap<>();
        System.out.println("JSON DISPLAY!");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BROWSE_JSON, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("books");
                            ArrayList<String> list;
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject book = jsonArray.getJSONObject(i);
                                list = new ArrayList<>();
                                list.add(book.getString("ISBN"));
                                list.add(book.getString("title"));
                                list.add(book.getString("author"));
                                list.add(book.getString("publisher"));
                                list.add(book.getString("copies"));
                                list.add(book.getString("price"));
                                list.add(book.getString("book_subject"));
                                list.add(book.getString("year_published"));
                                list.add(loginid);
                                books.put(i,list);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        simpleGrid.invalidate();
                        customAdapter = new CustomAdapter(getApplicationContext(), books);
                        simpleGrid.setAdapter(customAdapter);
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
