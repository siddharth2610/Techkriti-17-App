package com.suleiman.techkriti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.suleiman.techkriti.R;

/**
 * Created by GUNDA ABHISHEK on 01-12-2015.
 */
public class AndroidGridLayoutActivity extends NavDrawerActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.grid_layout, mContentFrame);


        Menu menu = mNavigationView.getMenu();
        menu.getItem(5).setChecked(true);
        GridView gridView = (GridView) findViewById(R.id.grid);
        final String imgList[]={"https://2016.techkriti.org/A/images/gallery/1.jpg","https://2016.techkriti.org/A/images/gallery/2.jpg","https://2016.techkriti.org/A/images/gallery/3.jpg","https://2016.techkriti.org/A/images/gallery/4.jpg","https://2016.techkriti.org/A/images/gallery/5.jpg","https://2016.techkriti.org/A/images/gallery/6.jpg","https://2016.techkriti.org/A/images/gallery/7.jpg","https://2016.techkriti.org/A/images/gallery/8.jpg","https://2016.techkriti.org/A/images/gallery/9.jpg","https://2016.techkriti.org/A/images/gallery/10.jpg","https://2016.techkriti.org/A/images/gallery/11.jpg","https://2016.techkriti.org/A/images/gallery/12.jpg","https://2016.techkriti.org/A/images/gallery/13.jpg","https://2016.techkriti.org/A/images/gallery/14.jpg","https://2016.techkriti.org/A/images/gallery/15.jpg","https://2016.techkriti.org/A/images/gallery/16.jpg","https://2016.techkriti.org/A/images/gallery/17.jpg","https://2016.techkriti.org/A/images/gallery/18.jpg","https://2016.techkriti.org/A/images/gallery/19.jpg","https://2016.techkriti.org/A/images/gallery/20.jpg","https://2016.techkriti.org/extras/android/gallery/21.jpg","https://2016.techkriti.org/extras/android/gallery/22.jpg","https://2016.techkriti.org/extras/android/gallery/23.jpg","https://2016.techkriti.org/extras/android/gallery/24.jpg","https://2016.techkriti.org/extras/android/gallery/25.jpg","https://2016.techkriti.org/extras/android/gallery/26.jpg","https://2016.techkriti.org/extras/android/gallery/27.jpg","https://2016.techkriti.org/extras/android/gallery/28.jpg","https://2016.techkriti.org/extras/android/gallery/29.jpg","https://2016.techkriti.org/extras/android/gallery/30.jpg","https://2016.techkriti.org/extras/android/gallery/31.jpg","https://2016.techkriti.org/extras/android/gallery/32.jpg","https://2016.techkriti.org/extras/android/gallery/33.jpg","https://2016.techkriti.org/extras/android/gallery/34.jpg","https://2016.techkriti.org/extras/android/gallery/35.jpg","https://2016.techkriti.org/extras/android/gallery/36.jpg","https://2016.techkriti.org/extras/android/gallery/37.jpg","https://2016.techkriti.org/extras/android/gallery/38.jpg","https://2016.techkriti.org/extras/android/gallery/39.jpg","https://2016.techkriti.org/extras/android/gallery/40.jpg","https://2016.techkriti.org/extras/android/gallery/41.jpg","https://2016.techkriti.org/extras/android/gallery/42.jpg","https://2016.techkriti.org/extras/android/gallery/43.jpg","https://2016.techkriti.org/extras/android/gallery/44.jpg","https://2016.techkriti.org/extras/android/gallery/45.jpg","https://2016.techkriti.org/extras/android/gallery/46.jpg","https://2016.techkriti.org/extras/android/gallery/47.jpg","https://2016.techkriti.org/extras/android/gallery/48.jpg","https://2016.techkriti.org/extras/android/gallery/49.jpg"};

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this,imgList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                ImageView imageView = (ImageView) v.findViewById(R.id.img);
                int[] screenLocation = new int[2];
                imageView.getLocationOnScreen(screenLocation);
                //Pass the image title and url to DetailsActivity
                i.putExtra("Arr", imgList);
                i.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", imageView.getWidth()).
                        putExtra("height", imageView.getHeight()).
                        putExtra("id", position);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
 /*   @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
            return true;
        }
        else
            return false;
    }

    */


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
