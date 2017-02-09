package com.suleiman.techkriti.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.suleiman.techkriti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class LaunchScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 4000;
    private PendingIntent pendingIntent;
    JSONParser jParser = new JSONParser();
    private  PendingIntent alarmIntent;

    String Times[];
    String Title[];
    String Content[];
    String url = "http://m.techkriti.org/api/notif/notif/all";
    String types[];
    String AddData[];
    JSONArray products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isInternetPresent = cd.isConnectingToInternet();

            Calendar cale=Calendar.getInstance();
            Intent intent = new Intent(getApplicationContext(), Scheduler.class);
            alarmIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

            AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cale.getTimeInMillis(),
                    1000 * 60 * 5, alarmIntent);

         //   new abc().execute();


        int n[] = {R.drawable.splash1, R.drawable.splash2, R.drawable.initial_pic};
        Random random = new Random();
        int s = n[random.nextInt(n.length)];


        setContentView(R.layout.activity_launch_screen);
        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(s);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                Intent i = new Intent(LaunchScreenActivity.this, startPage2.class);

                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                finish();
            }
        }, SPLASH_TIME);
    }


    class abc extends AsyncTask<String, String, String> {
        protected String doInBackground(String... args) {
            HashMap<String, String> params = new HashMap<>();

            JSONObject json = jParser.makeHttpRequest(url, "GET", params);
            try {


                products = json.getJSONArray("d" +
                        "");

                Times = new String[products.length()];


                Content = new String[products.length()];
                types = new String[products.length()];
                AddData = new String[products.length()];

                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    Times[i] = c.getString("time");
                    types[i] = c.getString("type");
                    Log.d("jjjj", Times[i]);

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
                Log.d("Month0", Times[i].substring(5, 7));
                Log.d("Month0", Times[i].substring(8, 10));
                Log.d("Month0", Times[i].substring(11, 13));


                calendar.set(Calendar.MONTH, Integer.parseInt(Times[i].substring(5, 7)) - 1);
                calendar.set(Calendar.YEAR, 2016);
                calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(Times[i].substring(8, 10)));

                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(Times[i].substring(11, 13)));
                calendar.set(Calendar.MINUTE, Integer.parseInt(Times[i].substring(14, 16)));
                calendar.set(Calendar.SECOND, Integer.parseInt(Times[i].substring(17, 19)));


                Log.d("ohNo1", types[i]);
                Log.d("ohNo1", Times[i]);
                Log.d("ohNo1", Content[i]);


                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Long time = 0L;
                if (calendar.getTimeInMillis() > Calendar.getInstance().getTimeInMillis()) {
                    Intent myIntent = new Intent(LaunchScreenActivity.this, MyReceiver.class);
                    time = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
                    myIntent.putExtra("Title", "Title");
                    myIntent.putExtra("Content", Content[i]);
                    myIntent.putExtra("Type", types[i]);
                    myIntent.putExtra("AddData", AddData[i]);
                    myIntent.putExtra("ID", i);
                    Log.d("SEE",Integer.toString(i));
                    k++;
                    pendingIntent = PendingIntent.getBroadcast(LaunchScreenActivity.this, i, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                }




            }


        }

    }
}



