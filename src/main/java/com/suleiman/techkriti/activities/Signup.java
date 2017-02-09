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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.suleiman.techkriti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Signup extends AppCompatActivity {
    Button login;EditText names,pass,usernames,phones,emails,facebooks,colleges;


    private ProgressDialog pDialog;
    String name,username,phone,college,email,facebook,gender,password;
    RadioGroup radioGroup;
    RadioButton b1,b2;
    String reason="";

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
    TextView ts;


    String name1="",name2="",name3="",name4="",name5="",name6="",name7="",team;String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        title=   intent.getStringExtra("title");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productsList = new ArrayList<HashMap<String, String>>();
radioGroup=(RadioGroup)findViewById(R.id.rgroup);

        names = (EditText) findViewById(R.id.fullname);
        usernames = (EditText) findViewById(R.id.user);
        colleges= (EditText) findViewById(R.id.college);
        phones = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.pwd);
        facebooks = (EditText) findViewById(R.id.fb);
        emails = (EditText) findViewById(R.id.email);






        login = (Button) findViewById(R.id.register);






        b1 = (RadioButton) findViewById(R.id.male);

    b2= (RadioButton) findViewById(R.id.female);




        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



             name=names.getText().toString();
                username=usernames.getText().toString();
                college=colleges.getText().toString();
                phone=phones.getText().toString();
                password=pass.getText().toString();
                email=emails.getText().toString();
                facebook =facebooks.getText().toString();
                int selectedId = radioGroup.getCheckedRadioButtonId();


                if(selectedId ==R.id.male) {
                    gender="Male";

                } else if(selectedId == R.id.female) {
                    gender="Female";
                }






                new abc().execute();
                Log.d("LET",reason);

            }
        });


    }

    class abc extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Signup.this);
            pDialog.setMessage("Logging in. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {












            try {


                    HashMap<String, String>  fin1 = new HashMap<>();
                    fin1.put("college",college);

                    fin1.put("email",email);
                    fin1.put("facebook",facebook);
                    fin1.put("gender",gender);
                    fin1.put("initialusername",username);
                    fin1.put("name",name);
                    fin1.put("pass",password);
                    fin1.put("phone",phone);
                    fin1.put("username",username);
                    String urlp="http://m.techkriti.org/api/register/main/signup";
                    JSONObject jsonsy = jParser.makeHttpRequest(urlp, "GET",fin1);
                    if(jsonsy.getString("status")=="true")
                    {
                        Intent intent=new Intent(Signup.this,AnimateToolbar.class);

                        startActivity(intent);

                    }







                else
                {
                    reason= jsonsy.getString("reason");
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            if(reason!="")
            Toast.makeText(getApplicationContext(),reason,Toast.LENGTH_SHORT).show();
            pDialog.dismiss();



        }

    }
    class cd extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Signup.this);
            pDialog.setMessage("Logging in. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {
            String  urlname = "http://m.techkriti.org/api/register/main/usernameavailable";
            HashMap<String, String>  fin = new HashMap<>();
            fin.put("username",username);










            try {

                JSONObject jsonsw = jParser.makeHttpRequest(urlname, "GET",fin);

String ava=jsonsw.getString("available");

            if(ava=="true")
             {
counter=0;
                }
                else
                {
                    counter=1;
                }





            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();



        }


    }


void hf(String reason)
{

}
}


