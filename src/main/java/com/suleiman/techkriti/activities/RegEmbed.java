package com.suleiman.techkriti.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.suleiman.techkriti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RegEmbed extends AppCompatActivity {
    Button login;EditText inputName,inputpwd,inteam,inname1,inname2,inname3,inname4,inname5,inname6,inname7;


    private ProgressDialog pDialog;
    String pid,aid,username,id,phone,college,email,facebook;
    String event[];
    String name[];EditText inname[]=new EditText[7];TextView intext[]=new TextView[7];
    JSONObject jsons[];
    String pname[];
    int n;

    JSONParser jParser = new JSONParser();
    JSONParser1 jParser1 = new JSONParser1();
    JSONParser2 jParser2 = new JSONParser2();
    int counter=0;
    String url[];
    String success[];
    JSONObject jsons1,jsons2,jsons3,jsons4,jsons5,jsons6,jsons7;

    ArrayList<HashMap<String, String>> productsList;

    String url_all_products ;
    String url_all_products1;
    String url_all_products2;
    String url_all_products3;
    String url_all_products4;

    JSONArray  abc=null;
    String title="";


    private static final String TAG_SUCCESS = "status";
    private static final String TAG_ANS = "log";
    private static final String TAG_C = "C";

    private static final String TAG_NAME = "name";
    private static final String TAG_PWD = "password";
    static java.net.CookieManager msCookieManager = new java.net.CookieManager();


    String name1="",name2="",name3="",name4="",name5="",name6="",name7="",team;String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
     title=   intent.getStringExtra("title");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_embed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productsList = new ArrayList<HashMap<String, String>>();

TextView katy=(TextView)findViewById(R.id.katy);
        katy.setText(title);
       inteam = (EditText) findViewById(R.id.teamname);
        inname[0] = (EditText) findViewById(R.id.num1);
        inname[1] = (EditText) findViewById(R.id.num2);
        inname[2] = (EditText) findViewById(R.id.num3);
        inname[3] = (EditText) findViewById(R.id.num4);
        inname[4] = (EditText) findViewById(R.id.num5);
        inname[5] = (EditText) findViewById(R.id.num6);
        inname[6] = (EditText) findViewById(R.id.num7);
        intext[0]=(TextView)findViewById(R.id.text1);
        intext[1]=(TextView)findViewById(R.id.text2);
        intext[2]=(TextView)findViewById(R.id.text3);
        intext[3]=(TextView)findViewById(R.id.text4);
        intext[4]=(TextView)findViewById(R.id.text5);
        intext[5]=(TextView)findViewById(R.id.text6);
        intext[6]=(TextView)findViewById(R.id.text7);



        login = (Button) findViewById(R.id.register);


          if(title.compareTo("Electromania")==0||title.compareTo("Embedded")==0||title.compareTo("Electrade")==0||title.compareTo("FPGA")==0||title.compareTo("Bridge Design Challenge")==0||title.compareTo("IARC")==0||title.compareTo("Manoeuvre")==0||title.compareTo("Hoverush")==0||title.compareTo("Rule The Sky")==0||title.compareTo("Sky Sparks")==0||title.compareTo("Astro Treasure")==0||title.compareTo("Battlefield")==0||title.compareTo("Marketing Villa")==0||title.compareTo("Buisness Venture")==0||title.compareTo("Social Track")==0||title.compareTo("Elevator Pitch")==0||title.compareTo("IOT")==0||title.compareTo("Manmohan Gill Bio Buisness")==0||title.compareTo("Soccon")==0||title.compareTo("Techkriti Innovation Challenge")==0) {
              n = 4;

            }
        else if(title.compareTo("WhatsUp")==0||title.compareTo("Battle City")==0||title.compareTo("Chaos")==0||title.compareTo("IOPC")==0||title.compareTo("Appathon")==0||title.compareTo("IHPC")==0||title.compareTo("Countdown")==0||title.compareTo("AstroQuiz0.")==0)
              n=2;
          else if(title.compareTo("Scientoon")==0)

              n=1;
          else if(title.compareTo("Concatenate")==0||title.compareTo("Multirotor")==0)
              n=5;
          else if(title.compareTo("Techkriti Grand Prix")==0||title.compareTo("Wild Soccer")==0)
              n=7;
          else if(title.compareTo("Junkyard Wars")==0||title.compareTo("IRGT")==0||title.compareTo("Be The Tycoon")==0||title.compareTo("Best Manager")==0||title.compareTo("29 States")==0)
              n=6;
        else if(title.compareTo("Crypto")==0||title.compareTo("IORC")==0||title.compareTo("Nutcracker")==0||title.compareTo("Stocksim")==0)
          n=0;
        else  if(title.compareTo("Impulse")==0||title.compareTo("Crimerun")==0)
              n=3;



        name = new String[n+1];
        url=new String[n+1];
        jsons=new JSONObject[n+1];
        success=new String[n+1];
        pname=new String[n+1];

        for(int i=n;i<=6;i++) {
            inname[i].setVisibility(View.GONE);
            intext[i].setVisibility(View.GONE);
        }



        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                for(int i=0;i<n;i++)
                {
                    name[i+1]=inname[i].getText().toString();
                }
                team = inteam.getText().toString();
                url[0] = "http://m.techkriti.org/api/register/main/info";
                  for(int i=0;i<n;i++) {


                  url[i + 1] = "http://m.techkriti.org/api/register/main/getname?id="+name[i+1];
                   }

                new abc().execute();
            }
        });


    }

    class abc extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RegEmbed.this);
            pDialog.setMessage("Logging in. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {


            HashMap<String, String> params = new HashMap<>();
            msCookieManager=jParser.cookme();
for(int i=0;i<n+1;i++)
{
    jsons[i] = jParser1.makeHttpRequest(url[i], "GET", params, msCookieManager);

}








            try {

String k="true";
                for(int i=1;i<n+1;i++)
                {
                    success[i] = jsons[i].getString(TAG_SUCCESS);
                    if(success[i]=="false"&&k=="true")
                        k="false";

                }
                if (k=="true" ) {

                    for (int i = 1; i < n + 1; i++)
                        pname[i] = jsons[i].getString("name");

                    JSONObject products = jsons[0].getJSONObject("info");
                  name[0]= products.getString("id");
                    pname[0]= products.getString("name");


                   JSONObject names=new JSONObject();

                    JSONObject id=new JSONObject();
                    JSONObject error=new JSONObject();
                    for(int i=0;i<n+1;i++) {
                          names.put(Integer.toString(i),pname[i]);
                          error.put(Integer.toString(i),"false");
                          id.put(Integer.toString(i),name[i]);
                          }
                    String errory =error.toString();
                    String namey =name.toString();
                    Log.d("FOOL",namey);
                    String idy=id.toString();
                    HashMap<String, String>  fin1 = new HashMap<>();
                    fin1.put("error",errory);

                    fin1.put("event",title);
                    fin1.put("id",idy);
                    fin1.put("name",namey);
                    fin1.put("title",team);
                    String urlp="http://m.techkriti.org/api/register/main/registerE";
                    JSONObject jsonsy = jParser2.makeHttpRequest(urlp, "GET",fin1,msCookieManager);
                   if(jsonsy.getString("register")=="true")
                   {
                       Intent intent=new Intent(RegEmbed.this,AnimateToolbar.class);

                       startActivity(intent);

                   }






                }
                else
                {}





            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();



        }

    }



}


