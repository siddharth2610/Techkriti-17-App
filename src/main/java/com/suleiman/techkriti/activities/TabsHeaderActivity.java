package com.suleiman.techkriti.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecycleAdapter2;
import com.suleiman.techkriti.model.socialdata;
import com.suleiman.techkriti.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class TabsHeaderActivity extends NavDrawerActivity {
    Toolbar toolbar1;
    int fabMargin;
    LinearLayout toolbarContainer;
    int toolbarHeight;
    FrameLayout fab;
    FloatingActionButton fabBtn;
    View fabShadow;
    ImageView header;
    LinearLayout mRevealView;
    boolean hidden = true;
    static int pos;String url="";
    static int nooftabs;static  int tool;
    String tabs1;
    String tabs2;
    String tabs3;
    String tabs4;
    String tabs5;
    String tabs6;String tabs7;String tabs8;String tabs9;String tabs10;String tabs11;
    static int pos1;
    static int a;
    String Title;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tabs_header, mContentFrame);

        Intent i=getIntent();
        pos=i.getExtras().getInt("id");
        Title=i.getExtras().getString("Title");
        this.setTitle(Title);
        pos1=i.getExtras().getInt("id1");
        tool=i.getExtras().getInt("activity");
        nooftabs=i.getExtras().getInt("tabs");
        tabs1=i.getExtras().getString("tabstr1");
        tabs2=i.getExtras().getString("tabstr2");
        tabs3=i.getExtras().getString("tabstr3");
        tabs4=i.getExtras().getString("tabstr4");
        tabs5=i.getExtras().getString("tabstr5");
        tabs6=i.getExtras().getString("tabstr6");
        tabs7=i.getExtras().getString("tabstr7");
        tabs8=i.getExtras().getString("tabstr8");
        tabs9=i.getExtras().getString("tabstr9");
        tabs10=i.getExtras().getString("tabstr10");
        tabs11=i.getExtras().getString("tabstr11");
        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        final Toolbar toolbar2=(Toolbar)findViewById(R.id.toolbar);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        CoordinatorLayout coordinatorLayout=(CoordinatorLayout) findViewById(R.id.htab_maincontent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tabs1);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
        Intent intent;
        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        mRevealView.setVisibility(View.INVISIBLE);
        //  FAB margin needed for animation
        fabMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
        toolbarHeight = Utils.getToolbarHeight(this);

        toolbarContainer = (LinearLayout) findViewById(R.id.fabhide_toolbar_container);

        // Adding list data thrice for a more comfortable scroll.

      //  fab = (FrameLayout) findViewById(R.id.myfab_main);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabButton);
       // fabShadow = findViewById(R.id.myfab_shadow);
       // fabBtn.setVisibility(View.GONE);
        //fabShadow.setVisibility(View.GONE);
     /*   FloatingActionButton faby=(FloatingActionButton)findViewById(R.id.faby);
        faby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TabsHeaderActivity.this, RegEmbed.class);
                intent.putExtra("title",Title);
                startActivity(intent);

            }
        });*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          //  fabShadow.setVisibility(View.GONE);
            fabBtn.setBackground(getDrawable(R.drawable.ripple_accent));
        }

        fabBtn.startAnimation(animation);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cx = (mRevealView.getLeft() + mRevealView.getRight());
                int cy = mRevealView.getTop();
                int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {


               SupportAnimator animator =
                            ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(800);

                    SupportAnimator animator_reverse = animator.reverse();

                    if (hidden) {
                        mRevealView.setVisibility(View.VISIBLE);
                        animator.start();
                        hidden = false;
                    } else {
                        animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                            @Override
                            public void onAnimationStart() {

                            }

                            @Override
                            public void onAnimationEnd() {
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;

                            }

                            @Override
                            public void onAnimationCancel() {

                            }

                            @Override
                            public void onAnimationRepeat() {

                            }
                        });
                        animator_reverse.start();

                    }
                } else {
                    if (hidden) {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                        mRevealView.setVisibility(View.VISIBLE);
                        anim.start();
                        hidden = false;

                    } else {
                        Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, radius, 0);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mRevealView.setVisibility(View.INVISIBLE);
                                hidden = true;
                            }
                        });
                        anim.start();

                    }
                }
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

         collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        header = (ImageView) findViewById(R.id.htab_header);
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isInternetPresent = cd.isConnectingToInternet();

        if(isInternetPresent==false)
            url="";
        else if(pos==0)
            url="https://2016.techkriti.org/extras/ep/Presentation_events.jpg";
        else if(pos==1)
            url="https://2016.techkriti.org/extras/ep/Presentation_events.jpg";
        else if (pos==2)
            url="https://2016.techkriti.org/extras/ep/Presentation_events.jpg";
        else
        url="";

            if(url!="")
            {
                Picasso.with(this)
                        .load(url)
                        .into(header);
        Picasso.with(this)
                .load(url)
                .into(target);}
            else {

            }
        ViewPager.OnPageChangeListener mlistener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                if (position == 0) {


                } else if(position==1) {

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                return;
            }
        };
        viewPager.setOnPageChangeListener(mlistener);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        coordinatorLayout.startAnimation(animation);
        ImageView Button1 = (ImageView)findViewById(R.id.gal);
        Button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/techkriti.iitk"));
                startActivity(intent);
            }
        });
        ImageView Button2 = (ImageView)findViewById(R.id.vid);
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/user/TechkritiIITK"));
                startActivity(intent);
            }
        });
        ImageView Button3 = (ImageView)findViewById(R.id.pho);
        Button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/techkriti_iitk"));
                startActivity(intent);
            }
        });
        ImageView Button4 = (ImageView)findViewById(R.id.goo);
        Button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://plus.google.com/+TechkritiOrg_iitk"));
                startActivity(intent);
            }
        });
    }
    private Target target = new Target() {

        @Override
        public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {

                    int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                    collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                    collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
                }
            });


        }


        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fab_hide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch:



        }
        return super.onOptionsItemSelected(item);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (nooftabs==7){
            adapter.addFrag(new DummyFragmentx(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragmenty(getResources().getColor(R.color.cardview_light_background)), tabs7);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==6){
            adapter.addFrag(new DummyFragmentx(getResources().getColor(R.color.cardview_light_background)),tabs1);
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs2);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)), tabs3);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)), tabs4);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragmenty(getResources().getColor(R.color.cardview_light_background)), tabs6);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==3){
            adapter.addFrag(new DummyFragmentx(getResources().getColor(R.color.cardview_light_background)),tabs1);
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs2);
            adapter.addFrag(new DummyFragmenty(getResources().getColor(R.color.cardview_light_background)), tabs3);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==8){
            adapter.addFrag(new DummyFragmentx(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragmenty(getResources().getColor(R.color.cardview_light_background)), tabs8);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==10){
            adapter.addFrag(new DummyFragmentx(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs8);
            adapter.addFrag(new DummyFragment8(getResources().getColor(R.color.cardview_light_background)),tabs9);
            adapter.addFrag(new DummyFragmenty(getResources().getColor(R.color.cardview_light_background)),tabs10);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==11){
            adapter.addFrag(new DummyFragmentx(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs8);
            adapter.addFrag(new DummyFragment8(getResources().getColor(R.color.cardview_light_background)),tabs9);
            adapter.addFrag(new DummyFragment9(getResources().getColor(R.color.cardview_light_background)),tabs10);
            adapter.addFrag(new DummyFragmenty(getResources().getColor(R.color.cardview_light_background)),tabs11);
            viewPager.setAdapter(adapter);
        }

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static class DummyFragment1 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;

        public DummyFragment1() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment1(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
                if (pos==0){
                    Spanned text = Html.fromHtml(socialdata.challengeAreas.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==1){
                    Spanned text = Html.fromHtml(socialdata.abs.data1.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==2){
                    Spanned text = Html.fromHtml(socialdata.topic.data2.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.rules.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.modelspecs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.arena.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.rules.data6.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==4){
                    Spanned text = Html.fromHtml(socialdata.rules.data7.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.rules.data8.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.rules.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragment2 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment2() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment2(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
                if (pos==0){
                    Spanned text = Html.fromHtml(socialdata.eventstructure.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==1){
                    Spanned text = Html.fromHtml(socialdata.eventstructure.data1.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==2){
                    Spanned text = Html.fromHtml(socialdata.rules.data2.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.misnobjective.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.rules.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.botspec.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.eventstructure.data6.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.eventstructure.data8.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.objective.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }}

            return view;
        }
    }
    public static class DummyFragment3 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment3() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment3(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
                if (pos==0){
                    Spanned text = Html.fromHtml(socialdata.rules.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==1){
                    Spanned text = Html.fromHtml(socialdata.rules.data1.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==2){
                    Spanned text = Html.fromHtml(socialdata.checklist.data2.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.eventstructure.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.modelspecs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.Gameplay.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.awards.data6.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.arena.data8.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.eventstructure.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

            }

            return view;
        }
    }
    public static class DummyFragment4 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment4() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment4(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
                if (pos==0){
                    Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==1){
                    Spanned text = Html.fromHtml(socialdata.them.data1.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==2){
                    Spanned text = Html.fromHtml(socialdata.declaration.data2.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.abs.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.rules.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.rules.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.points.data6.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.abs.data8.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.abs.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragment5 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment5() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment5(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
                if (pos==0){
                    Spanned text = Html.fromHtml(socialdata.faqs.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.arena.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.abs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.abs.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.faqs.data6.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.modelspecs.data8.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.arena.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragment6 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment6() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment6(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.robotcntrls.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.abs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.points.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.Gameplay.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragment7 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment7() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment7(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.botspec.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.abs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.botspec.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragment8 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment8() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment8(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){

            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.points.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.abs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.points.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragment9 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment9() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment9(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1){
            }
            else {
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.abs.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }

            return view;
        }
    }
    public static class DummyFragmenty extends Fragment {
        int color;
        Context context;
        CardView listItem;
        Intent intent;
        TextView textView,textView1;
        SimpleRecycleAdapter2 adapter;
        public DummyFragmenty() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragmenty(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1) {
                if (pos == 0) {
                    Spanned text = Html.fromHtml(socialdata.contacts.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos == 1) {
                    Spanned text = Html.fromHtml(socialdata.contacts.data1.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos == 2) {
                    Spanned text = Html.fromHtml(socialdata.contacts.data2.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.contacts.data3.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.contacts.data4.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.contacts.data5.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.contacts.data6.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==4){
                    Spanned text = Html.fromHtml(socialdata.contacts.data7.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.contacts.data8.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.contacts.data9.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

            }
            return view;
        }
    }

    public static class DummyFragmentx extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragmentx() {

        }

        @SuppressLint("ValidFragment")
        public DummyFragmentx(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dummy_fragment, container, false);

            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
            frameLayout.setBackgroundColor(color);

            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            if (tool==1) {
                if (pos == 0) {
                    Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos == 1) {
                    Spanned text = Html.fromHtml(socialdata.Home.data1.toString());
                    List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos == 2) {
                    Spanned text = Html.fromHtml(socialdata.Home.data2.toString());
                     List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            else {
                if(pos1==0){
                    Spanned text = Html.fromHtml(socialdata.Home.data3.toString());
                    List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==1){
                    Spanned text = Html.fromHtml(socialdata.Home.data4.toString());
                   //  List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }

                if(pos1==2){
                    Spanned text = Html.fromHtml(socialdata.Home.data5.toString());
                   //  List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==3){
                    Spanned text = Html.fromHtml(socialdata.Home.data6.toString());
                   //  List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==4){
                    Spanned text = Html.fromHtml(socialdata.Home.data7.toString());
                   //  List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==5){
                    Spanned text = Html.fromHtml(socialdata.Home.data8.toString());
                 //    List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if(pos1==6){
                    Spanned text = Html.fromHtml(socialdata.Home.data9.toString());
                  //   List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
            }
            return view;
        }
    }
}
