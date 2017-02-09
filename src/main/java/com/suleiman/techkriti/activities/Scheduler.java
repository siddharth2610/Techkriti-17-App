package com.suleiman.techkriti.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class Scheduler extends BroadcastReceiver {
    private PendingIntent pendingIntent;
    JSONParser jParser = new JSONParser();

    String Times[];
    String Title[];
    String Content[];
    String url = "http://m.techkriti.org/api/notif/notif/all";
    String types[];
    String AddData[];
    JSONArray products;
    private static final String DEBUG_TAG = "AlarmReceiver";
    Context cont;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(DEBUG_TAG, "Recurring alarm; requesting download service.");
        ConnectionDetector cd = new ConnectionDetector(context);
cont=context;
       Boolean isInternetPresent = cd.isConnectingToInternet();
        if (isInternetPresent == true) {

            new abc().execute();
        }


    }
    class abc extends AsyncTask<String, String, String> {
        protected String doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<>();

            JSONObject json = jParser.makeHttpRequest(url, "GET", params);
            try {


                products = json.getJSONArray("d" +
                        "");

                Times = new String[products.length()];
                Title = new String[products.length()];


                Content = new String[products.length()];
                types = new String[products.length()];
                AddData = new String[products.length()];

                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    Times[i] = c.getString("time");
                    types[i] = c.getString("type");
                    Title[i] = c.getString("title");



                    Content[i] = c.getString("data");
                    AddData[i] = c.getString("addtional_data");


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            int k = 0;
            for (int i = 0; i < Times.length; i++) {

                Calendar calendar = Calendar.getInstance();


                calendar.set(Calendar.MONTH, Integer.parseInt(Times[i].substring(5, 7)) - 1);
                calendar.set(Calendar.YEAR, 2017);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(Times[i].substring(8, 10)));
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Times[i].substring(11, 13)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(Times[i].substring(14, 16)));
                calendar.set(Calendar.SECOND, Integer.parseInt(Times[i].substring(17, 19)));


                Log.d("retrib",Long.toString(calendar.getTimeInMillis() ));
                Log.d("retribs",Long.toString(Calendar.getInstance().getTimeInMillis() ));


                AlarmManager alarmManager=(AlarmManager)cont.getSystemService(Context.ALARM_SERVICE);
                Long time = 0L;
                if (calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
                    Intent myIntent = new Intent(cont, MyReceiver.class);
                    Toast.makeText(cont, "retribsss", Toast.LENGTH_SHORT).show();

                    time = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
                    myIntent.putExtra("Title", Title[i]);
                    myIntent.putExtra("Content", Content[i]);
                    myIntent.putExtra("Type", types[i]);
                    myIntent.putExtra("AddData", AddData[i]);
                    myIntent.putExtra("ID", i);
                    Log.d("curr",Long.toString(Calendar.getInstance().getTimeInMillis()));
                    Log.d("retribs","retribs");


                    Log.d("SEE",Integer.toString(i));
                    k++;
                    pendingIntent = PendingIntent.getBroadcast(cont, i, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                }




            }


        }

    }
}
