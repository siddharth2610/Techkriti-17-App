package com.suleiman.techkriti.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.suleiman.techkriti.MapsActivity;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter;

public class AnimateToolbar extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    SimpleRecyclerAdapter simpleRecyclerAdapter;
    Toolbar toolbar;
    int fabMargin;
    LinearLayout toolbarContainer;
    int toolbarHeight;
    FrameLayout fab;
    ImageButton fabBtn;
    View fabShadow;
    LinearLayout mRevealView;
    boolean hidden = true;

    ImageView imgb1,but,imgb2,imgb3,imgb4;
    AnimationDrawable rocketAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animate_toolbar);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        //  setSupportActionBar(toolbar);

      /*  final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        //  collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // collapsingToolbar.setTitle("TESTING");
        //  Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        //  FAB margin needed for animation

        //  toolbarHeight = Utils.getToolbarHeight(this);

        // toolbarContainer = (LinearLayout) findViewById(R.id.fabhide_toolbar_container);

        // Adding list data thrice for a more comfortable scroll.


        imgb1= (ImageView) findViewById(R.id.imageView);
        imgb1.requestFocus();
        but= (ImageView) findViewById(R.id.imageView8);

        imgb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), competitions.class);
                startActivity(intent);
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

           imgb2= (ImageView) findViewById(R.id.imageView2);
           imgb2.requestFocus();
     imgb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Gallery.class);
                startActivity(intent);
            }
        });
        imgb3= (ImageView) findViewById(R.id.imageView3);
        imgb3.requestFocus();
        imgb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),TabAnimationActivity.class);
                startActivity(intent);
            }
        });
        imgb4= (ImageView) findViewById(R.id.imageView4);
        imgb4.requestFocus();
        imgb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });



        //    ImageView header = (ImageView) findViewById(R.id.header);

        //   Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
        //            R.drawable.header);

    /*    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        }); */

        Picasso.with(getApplicationContext())
                .load("http://cdn.wonderfulengineering.com/wp-content/uploads/2014/01/Technology-Wallpaper-14.jpg")
                .into(imgb1);

        Picasso.with(getApplicationContext())
                .load("http://blog.clickbooth.com/wp-content/uploads/2013/10/internationalandingpage.jpg")
                .into(imgb4);
        Picasso.with(getApplicationContext())
                .load("http://orig08.deviantart.net/8749/f/2012/228/a/d/i_luv_louis_collage_by_iluvlouis-d5bcigh.jpg")
                .into(imgb2);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab_switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_switch:
                Intent intent = new Intent(AnimateToolbar.this, Login.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
