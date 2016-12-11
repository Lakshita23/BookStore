package com.example.lakshita.bookstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.lakshita.bookstore.R.id.listView;
import static com.example.lakshita.bookstore.R.id.listView1;
import static com.example.lakshita.bookstore.R.id.listView2;
import static com.example.lakshita.bookstore.R.id.listView3;

public class user_recc extends AppCompatActivity {

    String myJSON1,myJSON2,myJSON3,myJSON4;
    String receivedloginid;


    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "loginid";
    private static final String TAG_NAME = "fullname";
    private static final String TAG_PASS = "password";
    private static final String TAG_CARD = "creditcard";
    private static final String TAG_ADD ="address";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_ADMINS = "admins";

    private static final String TAG_OID = "OID";
    private static final String TAG_ISBN = "ISBN";
    private static final String TAG_O_DATE = "o_date";
    private static final String TAG_O_STATUS = "o_status";
    private static final String TAG_QUANTITY = "quantity";

    private static final String TAG_FID = "FID";
    private static final String TAG_FTEXT = "Ftext";
    private static final String TAG_SCORE = "score";

    private static final String TAG_USEFUL = "usefulness";


    JSONArray peoples = null;
    JSONArray order=null;
    JSONArray feedback=null;
    JSONArray usefullness=null;

    ArrayList<HashMap<String, String>> personList;
    ArrayList<HashMap<String, String>> orderList;
    ArrayList<HashMap<String, String>> feedbackList;
    ArrayList<HashMap<String, String>> usefullnessList;


    ListView list;
    ListView list1;
    ListView list2;
    ListView list3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recc);

        receivedloginid = getIntent().getStringExtra("loginid");
        myJSON1=getIntent().getStringExtra("myJSON1");
        myJSON2=getIntent().getStringExtra("myJSON2");
        myJSON3=getIntent().getStringExtra("myJSON3");
        myJSON4=getIntent().getStringExtra("myJSON4");

        list = (ListView) findViewById(listView);
        list1= (ListView) findViewById(listView1);
        list2=(ListView) findViewById(listView2);
        list3=(ListView) findViewById(listView3);


        personList = new ArrayList<HashMap<String,String>>();
        orderList = new ArrayList<HashMap<String,String>>();
        feedbackList=new ArrayList<HashMap<String,String>>();
        usefullnessList=new ArrayList<HashMap<String,String>>();

       // getData1();
       // getData2();
      //  getData3();
       // getData4();
        showList1();
        showList2();
        showList3();
       // showList4();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    protected void showList1(){
        try {
            JSONObject jObject1 = null;
            jObject1 = new JSONObject(myJSON1);
            JSONArray peoples = jObject1.getJSONArray(TAG_RESULTS);


            for(int i=0;i<peoples.length();i++){
                JSONObject d = peoples.getJSONObject(i);
                String id = d.getString(TAG_ID);
                String name = d.getString(TAG_NAME);
                String pass=d.getString(TAG_PASS);
                String card=d.getString(TAG_CARD);
                String address = d.getString(TAG_ADD);
                String phone=d.getString(TAG_PHONE);
                String admins=d.getString(TAG_ADMINS);

                HashMap<String,String> persons = new HashMap<String,String>();
                persons.put(TAG_ID,id);
                persons.put(TAG_NAME,name);
                persons.put(TAG_PASS,pass);
                persons.put(TAG_CARD,card);
                persons.put(TAG_ADD,address);
                persons.put(TAG_PHONE,phone);
                persons.put(TAG_ADMINS,admins);

                personList.add(persons);

            }

            ListAdapter adapter1 = new SimpleAdapter(
                    user_recc.this, personList, R.layout.list_item,
                    new String[]{TAG_ID,TAG_NAME,TAG_PASS,TAG_CARD,TAG_ADD,
                            TAG_PHONE,TAG_ADMINS},
                    new int[]{R.id.id, R.id.name,R.id.password,R.id.card, R.id.address,R.id.phone,R.id.admins}
            );
            list.setAdapter(adapter1);
            Utility.setListViewHeightBasedOnChildren(list);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    protected void showList2(){
        try {
            JSONObject jObject2 = null;
            jObject2 = new JSONObject(myJSON2);
            JSONArray order = jObject2.getJSONArray(TAG_RESULTS);
            Toast.makeText(this,String.valueOf(orderList), Toast.LENGTH_SHORT).show();


            for(int j=0;j<order.length();j++){
                JSONObject c = order.getJSONObject(j);

                String oid = c.getString(TAG_OID);
                String id2 = c.getString(TAG_ID);
                String ISBN = c.getString(TAG_ISBN);
                String o_date = c.getString(TAG_O_DATE);
                String o_status = c.getString(TAG_O_STATUS);
                String quantity = c.getString(TAG_QUANTITY);

                HashMap<String,String> orders = new HashMap<String,String>();

                orders.put(TAG_OID, oid);
                orders.put(TAG_ID,id2);
                orders.put(TAG_ISBN, ISBN);
                orders.put(TAG_O_DATE, o_date);
                orders.put(TAG_O_STATUS, o_status);
                orders.put(TAG_QUANTITY,quantity);

                orderList.add(orders);
            }
            Toast.makeText(this,String.valueOf(orderList), Toast.LENGTH_SHORT).show();

            ListAdapter adapter2 = new SimpleAdapter(
                    user_recc.this, orderList, R.layout.list2_item,
                    new String[]{TAG_OID,TAG_ISBN,TAG_O_DATE,TAG_O_STATUS,TAG_QUANTITY},
                    new int[]{R.id.oid,R.id.ISBN,R.id.odate,R.id.ostatus,R.id.quantity}
            );

            list1.setAdapter(adapter2);


            Utility.setListViewHeightBasedOnChildren(list1);
           // Toast.makeText(this,String.valueOf(orderList), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    protected void showList3(){
        try {
            JSONObject jObject3 = null;
            jObject3 = new JSONObject(myJSON3);
            JSONArray feedback = jObject3.getJSONArray(TAG_RESULTS);
            //Toast.makeText(this,String.valueOf(feedbackList), Toast.LENGTH_SHORT).show();


            for(int j=0;j<feedback.length();j++){
                JSONObject c = feedback.getJSONObject(j);

                String FID = c.getString(TAG_FID);
                String id3 = c.getString(TAG_ID);
                String ISBN = c.getString(TAG_ISBN);
                String ftext = c.getString(TAG_FTEXT);
                String score = c.getString(TAG_SCORE);

                HashMap<String,String> feedbackss = new HashMap<String,String>();

                feedbackss.put(TAG_FID, FID);
                feedbackss.put(TAG_ID,id3);
                feedbackss.put(TAG_ISBN, ISBN);
                feedbackss.put(TAG_FTEXT, ftext);
                feedbackss.put(TAG_SCORE,score );

                feedbackList.add(feedbackss);
            }
            Toast.makeText(this,String.valueOf(feedbackList), Toast.LENGTH_SHORT).show();

            ListAdapter adapter3 = new SimpleAdapter(
                    user_recc.this, feedbackList, R.layout.list3_item,
                    new String[]{TAG_FID,TAG_ISBN,TAG_FTEXT,TAG_SCORE},
                    new int[]{R.id.fid,R.id.ISBN3,R.id.ftext,R.id.score}
            );

            list2.setAdapter(adapter3);


            Utility.setListViewHeightBasedOnChildren(list2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void showList4(){
        try {
            JSONObject jObject4 = null;
            jObject4 = new JSONObject(myJSON4);
            JSONArray usefullness = jObject4.getJSONArray(TAG_RESULTS);
            //Toast.makeText(this,String.valueOf(feedbackList), Toast.LENGTH_SHORT).show();


            for(int j=0;j<usefullness.length();j++){
                JSONObject c = usefullness.getJSONObject(j);

                String FID = c.getString(TAG_FID);
                String id = c.getString(TAG_ID);
                String ISBN = c.getString(TAG_ISBN);
                String ftext = c.getString(TAG_FTEXT);
                String score = c.getString(TAG_SCORE);
                String useful=c.getString(TAG_USEFUL);

                HashMap<String,String> usefullnessses = new HashMap<String,String>();

                usefullnessses.put(TAG_FID, FID);
                usefullnessses.put(TAG_ID,id);
                usefullnessses.put(TAG_ISBN, ISBN);
                usefullnessses.put(TAG_FTEXT, ftext);
                usefullnessses.put(TAG_SCORE,score );
                usefullnessses.put(TAG_USEFUL,useful);
                usefullnessList.add(usefullnessses);
            }
            Toast.makeText(this,String.valueOf(usefullnessList), Toast.LENGTH_SHORT).show();

            ListAdapter adapter4 = new SimpleAdapter(
                    user_recc.this, usefullnessList, R.layout.list4_item,
                    new String[]{TAG_FID,TAG_ISBN,TAG_FTEXT,TAG_SCORE,TAG_USEFUL},
                    new int[]{R.id.fid2,R.id.ISBN4,R.id.ftext2,R.id.score2,R.id.useful}
            );

            list3.setAdapter(adapter4);
            Utility.setListViewHeightBasedOnChildren(list3);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    public void getData1(){
//        class GetDataJSON1 extends AsyncTask<String, Void, String> {
//            String JSON_URL;
//
//            @Override
//            protected void onPreExecute() {
//                JSON_URL = "http://bookdb.16mb.com/part3q1.php";
//            }
//
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                    try {
//                        URL url = new URL(JSON_URL);
//                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                        InputStream inputStream = httpURLConnection.getInputStream();
//                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                        StringBuilder stringBuilder = new StringBuilder();
//                        while ((JSON_STRING[0]= bufferedReader.readLine()) != null) {
//                            stringBuilder.append(JSON_STRING[0] + "\n");
//                        }
//                        bufferedReader.close();
//                        httpURLConnection.disconnect();
//                        inputStream.close();
//                       return stringBuilder.toString().trim();
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result){
//                myJSON1=result;
//                Toast.makeText(user_recc.this, myJSON1, Toast.LENGTH_SHORT).show();
//                showList1();
//            }
//        }
//        GetDataJSON1 g1 = new GetDataJSON1();
//        g1.execute();
//    }
//
//    public void getData2(){
//        class GetDataJSON2 extends AsyncTask<String, Void, String> {
//            String JSON_URL;
//
//            @Override
//            protected void onPreExecute() {
//                JSON_URL= "http://bookdb.16mb.com/part3q2.php";
//            }
//
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                    try {
//                        URL url = new URL(JSON_URL);
//                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                        InputStream inputStream = httpURLConnection.getInputStream();
//                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                        StringBuilder stringBuilder = new StringBuilder();
//                        while ((JSON_STRING[1] = bufferedReader.readLine()) != null) {
//                            stringBuilder.append(JSON_STRING[1]+ "\n");
//                        }
//                        bufferedReader.close();
//                        httpURLConnection.disconnect();
//                        inputStream.close();
//                        return stringBuilder.toString().trim();
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result){
//                myJSON2=result;
//                Toast.makeText(user_recc.this, myJSON2, Toast.LENGTH_SHORT).show();
//                showList2();
//            }
//        }
//        GetDataJSON2 g2 = new GetDataJSON2();
//        g2.execute();
//    }
//
//    public void getData3(){
//        class GetDataJSON3 extends AsyncTask<String, Void, String> {
//            String JSON_URL;
//
//            @Override
//            protected void onPreExecute() {
//                JSON_URL= "http://bookdb.16mb.com/part3q3.php";
//            }
//
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                try {
//                    URL url = new URL(JSON_URL);
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    while ((JSON_STRING[1] = bufferedReader.readLine()) != null) {
//                        stringBuilder.append(JSON_STRING[1]+ "\n");
//                    }
//                    bufferedReader.close();
//                    httpURLConnection.disconnect();
//                    inputStream.close();
//                    return stringBuilder.toString().trim();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result){
//                myJSON3=result;
//                Toast.makeText(user_recc.this, myJSON3, Toast.LENGTH_SHORT).show();
//                showList3();
//            }
//        }
//        GetDataJSON3 g3 = new GetDataJSON3();
//        g3.execute();
//    }
//
//    public void getData4(){
//        class GetDataJSON4 extends AsyncTask<String, Void, String> {
//            String JSON_URL;
//
//            @Override
//            protected void onPreExecute() {
//                JSON_URL= "http://bookdb.16mb.com/part3q4.php";
//            }
//
//
//            @Override
//            protected void onProgressUpdate(Void... values) {
//                super.onProgressUpdate(values);
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                try {
//                    URL url = new URL(JSON_URL);
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                    StringBuilder stringBuilder = new StringBuilder();
//                    while ((JSON_STRING[1] = bufferedReader.readLine()) != null) {
//                        stringBuilder.append(JSON_STRING[1]+ "\n");
//                    }
//                    bufferedReader.close();
//                    httpURLConnection.disconnect();
//                    inputStream.close();
//                    return stringBuilder.toString().trim();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result){
//                myJSON4=result;
//                Toast.makeText(user_recc.this, myJSON4, Toast.LENGTH_SHORT).show();
//                showList4();
//            }
//        }
//        GetDataJSON4 g4 = new GetDataJSON4();
//        g4.execute();
//    }


}
