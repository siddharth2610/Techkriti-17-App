package com.suleiman.techkriti.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suleiman.techkriti.MapsActivity;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter;

public class AnimateToolbar1 extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    SimpleRecyclerAdapter simpleRecyclerAdapter;
    String username;
    String name="",phone,college,email,id,facebook,event[];
    Toolbar toolbar;
    int fabMargin;
    LinearLayout toolbarContainer;
    int toolbarHeight;
    FrameLayout fab;
    ImageButton fabBtn;
    View fabShadow;
    LinearLayout mRevealView;
    boolean hidden = true;
    ImageView imgb1,imgb2,imgb3,imgb4;


    //final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animate_toolbar1);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        id=i.getStringExtra("id");
        name=i.getStringExtra("name");
        phone=i.getStringExtra("phone");
        college=i.getStringExtra("college");
        email=i.getStringExtra("email");
        facebook=i.getStringExtra("facebook");
        event=i.getStringArrayExtra("event");

        imgb1= (ImageView) findViewById(R.id.imageView);
        imgb1.requestFocus();
final TextView but;
        but= (TextView) findViewById(R.id.imageView8);

but.setText(name);
      final  PopupMenu popupMenu = new PopupMenu(this, but);


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

                popupMenu.inflate(R.menu.create_post_menu);
                popupMenu.show();


                popupMenu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_r:
                                        Intent intent = new Intent(AnimateToolbar1.this, com.suleiman.techkriti.activities.profile.class);
                                        intent.putExtra("id", id);


                                        intent.putExtra("username", username);
                                        intent.putExtra("name", name);

                                        intent.putExtra("phone", phone);
                                        intent.putExtra("event", event);

                                        intent.putExtra("college", college);
                                        intent.putExtra("email", email);
                                        intent.putExtra("facebook", facebook);
                                        startActivity(intent);
                                        break;

                                    case R.id.menu_g:
                                        intent = new Intent(AnimateToolbar1.this, AnimateToolbar.class);
                                        startActivity(intent);
                                        break;
                                }
                                return true;
                            }
                        });

            }
        });


        imgb2= (ImageView) findViewById(R.id.imageView2);
        imgb2.requestFocus();
        imgb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Gallery.class);
                startActivity(intent);
            }
        });
        imgb3= (ImageView) findViewById(R.id.imageView3);
        imgb3.requestFocus();
        imgb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TabAnimationActivity.class);
                startActivity(intent);
            }
        });
        imgb4= (ImageView) findViewById(R.id.imageView4);
        imgb4.requestFocus();
        imgb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });


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

    public String Namer()
    {
        return name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reveal, menu);
        MenuItem item=menu.findItem(R.id.action_clip);
        item.setTitle(name);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_clip:
                View menuItemView = findViewById(R.id.action_clip);
                PopupMenu popupMenu = new PopupMenu(this, menuItemView);
                popupMenu.inflate(R.menu.create_post_menu);
                popupMenu.show();


                popupMenu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu_r:
                                        Intent intent=new Intent(AnimateToolbar1.this, com.suleiman.techkriti.activities.profile.class);
                                        intent.putExtra("id",id);


                                        intent.putExtra("username",username);
                                        intent.putExtra("name",name);

                                        intent.putExtra("phone",phone);
                                        intent.putExtra("event",event);

                                        intent.putExtra("college",college);
                                        intent.putExtra("email",email);
                                        intent.putExtra("facebook",facebook);
                                        startActivity(intent);
                                        break;

                                    case R.id.menu_g:
                                        intent=new Intent(AnimateToolbar1.this,AnimateToolbar.class);
                                        startActivity(intent);
                                        break;
                                }
                                return true;
                            }
                        });

                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
