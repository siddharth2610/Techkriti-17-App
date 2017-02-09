package com.suleiman.techkriti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.suleiman.techkriti.MapsActivity;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapterstart;
import com.suleiman.techkriti.model.VersionModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class startPage2 extends AppCompatActivity {
    ViewPager viewPager;int position;int  url[]=new int[5];
    RecyclerView recyclerView;
    public  static ImageView notif;

    SimpleRecyclerAdapterstart simpleRecyclerAdapter;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page2);
 notif=(ImageView)findViewById(R.id.imageViewn);
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Notification_Act
                        .class);
                startActivity(intent);

            }
        });

ImageView imageView1=(ImageView)findViewById(R.id.imageView8);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(getApplicationContext(),Startpage.class);
                startActivity(intent);

            }
        });

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);

url[0]=R.drawable.farhan;
        url[1]=R.drawable.lyn;

        url[2]=R.drawable.hamid;

        url[3]=R.drawable.tvf;
        url[4]=R.drawable.bikes;


        PagerAdapter adapter = new FullScreenImageAdapter1(startPage2.this,url);
        viewPager.setAdapter(adapter);
        CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
     titleIndicator.setViewPager(viewPager);
        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> listData = new ArrayList<String>();

        for (int i = 0; i < VersionModel.data.length; i++) {
            listData.add(VersionModel.data[i]);


        }

        List<Integer> listData1 = new ArrayList<Integer>();
        listData1.add(R.drawable.comp);
        listData1.add(R.drawable.talks);        listData1.add(R.drawable.shows);
        listData1.add(R.drawable.exhibition);

        listData1.add(R.drawable.gallery1);listData1.add(R.drawable.cont);
        listData1.add(R.drawable.map);



        simpleRecyclerAdapter = new SimpleRecyclerAdapterstart(listData,listData1,startPage2.this);
        recyclerView.setAdapter(simpleRecyclerAdapter);

        simpleRecyclerAdapter.SetOnItemClickListener(new SimpleRecyclerAdapterstart

                .OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent;
                switch (position) {

                    case 0:
                        intent = new Intent(startPage2.this, competitions.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(startPage2.this, AndroidGridLayoutActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(startPage2.this, TabAnimationActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(startPage2.this, MapsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(startPage2.this, talks.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(startPage2.this, shows.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(startPage2.this, exhibition.class);
                        startActivity(intent);
                        break;

                }
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fab_hide, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_clip:

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyWeb extends WebViewClient
    {
        public boolean shouldOverride(WebView view,String url)
        {
            view.loadUrl(url);
            return true;
        }
    }

}