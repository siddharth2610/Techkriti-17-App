package com.suleiman.techkriti.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.suleiman.techkriti.MapsActivity1;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapternotif;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Notification_Act extends AppCompatActivity {
    private PendingIntent pendingIntent;
    JSONParser jParser = new JSONParser();
    List<String> listData = new ArrayList<String>();
    List<String> listData1 = new ArrayList<String>();
    List<String> listData2 = new ArrayList<String>();
    String Times[];
    String Timey[];

    String Title[];
    String Content[];
    String url = "http://m.techkriti.org/api/notif/notif/all";
    String types[];
    String AddData[];
    JSONArray products;
    private static final String DEBUG_TAG = "AlarmReceiver";
    Context cont;
RecyclerView recyclerView;
    SimpleRecyclerAdapternotif simpleRecyclerAdapter;
    startPage2 st=new startPage2();
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate( R.layout.activity_start_page2, null );
        ImageView notif=(ImageView)view.findViewById(R.id.imageViewn);
        notif.setImageResource(R.drawable.minicons);
        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        simpleRecyclerAdapter = new SimpleRecyclerAdapternotif(listData,listData1,listData2);
        recyclerView.setAdapter(simpleRecyclerAdapter);

        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isInternetPresent = cd.isConnectingToInternet();
        if(isInternetPresent==true)
        {
            new abc().execute();}




    }
    class abc extends AsyncTask<String, String, String> {
        protected String doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<>();

            JSONObject json = jParser.makeHttpRequest(url, "GET", params);
            try {


                products = json.getJSONArray("d" +
                        "");

                Times = new String[products.length()];
                Timey = new String[products.length()];

                Title = new String[products.length()];


                Content = new String[products.length()];
                types = new String[products.length()];
                AddData = new String[products.length()];

                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    Times[i] = c.getString("time");
                    types[i] = c.getString("type");
                    Title[i] = c.getString("title");

Timey[i]=Times[i].substring(11,19);

                    Content[i] = c.getString("data");
                    AddData[i] = c.getString("addtional_data");


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {

            Calendar calendar = Calendar.getInstance();



            for (int i = 0; i < Times.length; i++) {
                calendar.set(Calendar.MONTH, Integer.parseInt(Times[i].substring(5, 7)) - 1);
                calendar.set(Calendar.YEAR, 2016);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(Times[i].substring(8, 10)));

                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Times[i].substring(11, 13)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(Times[i].substring(14, 16)));
                calendar.set(Calendar.SECOND, Integer.parseInt(Times[i].substring(17, 19)));
                if(calendar.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                    listData.add(Title[i]);
                    listData1.add(Content[i]);
                    listData2.add(Timey[i]);
                }

            }


            simpleRecyclerAdapter = new SimpleRecyclerAdapternotif(listData,listData1,listData2);
            recyclerView.setAdapter(simpleRecyclerAdapter);

           simpleRecyclerAdapter.SetOnItemClickListener(new SimpleRecyclerAdapternotif

                    .OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    if (types[position].compareTo("location") == 0) {
                        Intent intent = new Intent(Notification_Act.this, MapsActivity1.class);
                        intent.putExtra("Location", AddData[position]);
                        intent.putExtra("Snippet", Content[position]);
                        startActivity(intent);
                    }
                    if (types[position].compareTo("link") == 0) {
                       Intent resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(AddData[position]));
                        startActivity(resultIntent);
                    }
                    if (types[position].compareTo("notice") == 0) {
                        Intent intent = new Intent(Notification_Act.this, startPage2.class);

                        startActivity(intent);
                    }
                }
            });




            }


        }

    }


