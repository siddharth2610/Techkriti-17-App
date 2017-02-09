package com.suleiman.techkriti.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.suleiman.techkriti.R;

public class FullImageActivity extends Activity {
    ViewPager viewPager;int position;String  url[]=new String[49];
    String arr[]=new String[49];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        Intent intent=getIntent();
      arr=  intent.getExtras().getStringArray("Arr");
        position=intent.getExtras().getInt("id");
        viewPager = (ViewPager) findViewById(R.id.pager);
        int k=position;
      for(int i=0;i<49;i++)
        {
            url[i]=arr[k];
            k++;
            if(k==49)
            {k=i;
            break;}
        }int j=0;
        for(int i=k+1;i<49;i++)
        {
            url[i]=arr[j];
            j++;
        }



        PagerAdapter adapter = new FullScreenImageAdapter(FullImageActivity.this,url);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fab_hide, menu);
        return true;
    }

}