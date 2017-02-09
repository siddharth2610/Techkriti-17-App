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

public class Login extends AppCompatActivity {
    Button login;EditText inputName,inputpwd;


    private ProgressDialog pDialog;
    String pid,aid,username,id,phone,college,email,facebook;
  String event[];

    JSONParser jParser = new JSONParser();
    JSONParser1 jParser1 = new JSONParser1();
    int counter=0;
    String url[]=new String[3];

    ArrayList<HashMap<String, String>> productsList;

    String url_all_products ;
    String url_all_products1;
    String url_all_products2;
    JSONArray  abc=null;


    private static final String TAG_SUCCESS = "login";
    private static final String TAG_ANS = "log";
    private static final String TAG_C = "C";

    private static final String TAG_NAME = "name";
    private static final String TAG_PWD = "password";
    static java.net.CookieManager msCookieManager = new java.net.CookieManager();


    String name;String pwd;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productsList = new ArrayList<HashMap<String, String>>();


        inputName = (EditText) findViewById(R.id.name);
        inputpwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
        signup=(TextView)findViewById(R.id.signUpTextView);








        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = inputName.getText().toString();
                pwd = inputpwd.getText().toString();
                url_all_products = "http://m.techkriti.org/api/register/main/login";
                url_all_products1 = "http://m.techkriti.org/api/register/main/info";
               url_all_products2="http://m.techkriti.org/api/register/main/profileE";
                url[0] = url_all_products;
                url[1] = url_all_products1;
                url[2]=url_all_products2;
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

                Boolean isInternetPresent = cd.isConnectingToInternet();
                if(isInternetPresent==true)
                {
                new abc().execute();}
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });


    }

    class abc extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Logging in. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {


            HashMap<String, String> params = new HashMap<>();
            Log.d("FFFGGG", url_all_products);
            HashMap<String, String>  fin1 = new HashMap<>();
            fin1.put("gender","Male");


            fin1.put("pass",pwd);
            fin1.put("username",name);

            JSONObject json = jParser.makeHttpRequest(url[0], "GET", fin1);
            msCookieManager=jParser.cookme();
            JSONObject jsons = jParser1.makeHttpRequest(url[1], "GET", params,msCookieManager);
            JSONObject jsonss = jParser1.makeHttpRequest(url[2], "GET", params,msCookieManager);


            try {

                String success = json.getString(TAG_SUCCESS);

                if (success =="true" ) {



                    JSONObject products = jsons.getJSONObject("info");
                    id= products.getString("id");
                    username= products.getString("username");
                    name= products.getString("name");
                    phone= products.getString("phone");
                    college= products.getString("college");

                     abc = jsonss.getJSONArray("events");
                    event=new String[abc.length()];
                    for(int i=0;i<abc.length();i++)
                    {
                    products=abc.getJSONObject(i);
                    event[i]= products.getString("event");}
                    Log.d("FFSGGS", id);
                    Log.d("SJSJ", username);



                    Intent intent=new Intent(Login.this,random1.class);
                    intent.putExtra("id",id);


                    intent.putExtra("username",username);
                    intent.putExtra("name",name);

                    intent.putExtra("phone",phone);
                    intent.putExtra("event",event);

                    intent.putExtra("college",college);
                    intent.putExtra("email",email);
                    intent.putExtra("facebook",facebook);
                    intent.putExtra("pwd",pwd);





                    startActivity(intent);


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
