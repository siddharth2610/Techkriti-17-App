package com.suleiman.techkriti.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter;
import com.suleiman.techkriti.model.VersionModel;

import java.util.ArrayList;
import java.util.List;

public class random1 extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbar;
    RecyclerView recyclerView;
    int mutedColor = R.attr.colorPrimary;
    SimpleRecyclerAdapter simpleRecyclerAdapter;
    String username;
    String name="",phone,college,email,id,facebook,event[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_random);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        id=i.getStringExtra("id");
        name=i.getStringExtra("name");
        phone=i.getStringExtra("phone");
        college=i.getStringExtra("college");
        email=i.getStringExtra("email");
        facebook=i.getStringExtra("facebook");
        event=i.getStringArrayExtra("event");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //collapsingToolbar.setTitle("Suleiman Ali Shakir");



        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                mutedColor = palette.getMutedColor(R.color.primary_500);
                collapsingToolbar.setContentScrimColor(mutedColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.black_trans80);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        List<String> listData = new ArrayList<String>();

        for (int j = 0; j < VersionModel.data.length; j++) {
            listData.add(VersionModel.data[j]);

        }

        if (simpleRecyclerAdapter == null) {
            simpleRecyclerAdapter = new SimpleRecyclerAdapter(listData);
            recyclerView.setAdapter(simpleRecyclerAdapter);
        }

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
                                        Intent intent=new Intent(random1.this, com.suleiman.techkriti.activities.profile.class);
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
                                        intent=new Intent(random1.this,random.class);
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