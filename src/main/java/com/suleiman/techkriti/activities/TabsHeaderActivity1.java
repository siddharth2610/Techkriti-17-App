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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
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
import com.suleiman.techkriti.adapter.SimpleRecycleAdapter3;
import com.suleiman.techkriti.model.socialdata;
import com.suleiman.techkriti.model.tech2;
import com.suleiman.techkriti.model.technical;
import com.suleiman.techkriti.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class TabsHeaderActivity1 extends NavDrawerActivity {
    Toolbar toolbar1;
    int fabMargin;
    LinearLayout toolbarContainer;
    int toolbarHeight;
    FrameLayout fab;
    FloatingActionButton fabBtn;
    View fabShadow;
    LinearLayout mRevealView;
    boolean hidden = true;
    static int pos;
    static int nooftabs;static  int tool;
    String tabs1;
    String tabs2;
    String tabs3;
    String tabs4;
    String tabs5;
    String tabs6;String tabs7;String tabs8;String tabs9;String tabs10;String tabs11;
    String url;
    static int pos1;
   static String Title;
     CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tabs_header, mContentFrame);

        Intent i=getIntent();
        pos=i.getExtras().getInt("id");
        Title=i.getExtras().getString("Title");


            this.setTitle(Title);

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
       /* FloatingActionButton faby=(FloatingActionButton)findViewById(R.id.faby);
       faby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TabsHeaderActivity1.this, RegEmbed.class);
                intent.putExtra("title",Title);
                startActivity(intent);

            }
        });*/

        // Adding list data thrice for a more comfortable scroll.

      //  fab = (FrameLayout) findViewById(R.id.myfab_main);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabButton);
     //   fabBtn.setVisibility(View.GONE);

      //  fabShadow = findViewById(R.id.myfab_shadow);
       // fabShadow.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           // fabShadow.setVisibility(View.GONE);
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

        final ImageView header = (ImageView) findViewById(R.id.htab_header);
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isInternetPresent = cd.isConnectingToInternet();

        if(isInternetPresent==false)
        {
            url="";
        }
        else if(Title.compareTo("Bridge Design Challenge")==0)
        url="https://2016.techkriti.org/extras/ep/bridge_design_challenge.jpg";

        else if(Title.compareTo("Junkyard Wars")==0)
            url="https://2016.techkriti.org/extras/ep/Junk_yard_wars.jpg";
        else if(Title.compareTo("Techkriti Grand Prix")==0)
            url="https://2016.techkriti.org/extras/ep/tgp.jpg";
        else if(Title.compareTo("Concatenate")==0)

            url="https://2016.techkriti.org/extras/ep/concatenate.jpg";
        else if(Title.compareTo("Wild Soccer")==0)
            url="https://2016.techkriti.org/extras/ep/wild_soccer.jpg";
        else if(Title.compareTo("IARC")==0)

            url="https://2016.techkriti.org/extras/ep/IARC.jpg";
        else if(Title.compareTo("Manoeuvre")==0)
            url="https://2016.techkriti.org/extras/ep/manua.jpg";
        else if(Title.compareTo("IRGT")==0)

            url="https://2016.techkriti.org/extras/ep/IRGT.jpg";
        else if(Title.compareTo("Hoverush")==0)

            url="https://2016.techkriti.org/extras/ep/hover_rush.jpg";
        else if(Title.compareTo("Impulse")==0)

            url="https://2016.techkriti.org/extras/ep/impulse.jpg";
        else if(Title.compareTo("Multirotor")==0)

            url="https://2016.techkriti.org/extras/ep/multirover.jpg";
        else
            url="";

        if(url!="") {
            Picasso.with(this)
                    .load(url)
                    .into(header);
           Picasso.with(this).load(url).into(target);


        }

        //   Picasso.with(this).load(url).into(header);
      //  Drawable d=header.getDrawable();


    /*   final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.header );

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        toolbar.setTitle(tabs1);
                        break;
                    case 1:
                        toolbar.setTitle(tabs2);
                        break;
                    case 2:
                        toolbar.setTitle(tabs3);
                        break;
                    case 3:
                        toolbar.setTitle(tabs4);
                        break;
                    case 4:
                        toolbar.setTitle(tabs5);
                        break;
                    case 5:
                        toolbar.setTitle(tabs6);
                        break;
                    case 6:
                        toolbar.setTitle(tabs7);
                        break;
                    case 7:
                        toolbar.setTitle(tabs8);
                        break;
                    case 8:
                        toolbar.setTitle(tabs9);
                        break;
                    case 9:
                        toolbar.setTitle(tabs10);
                        break;
                    case 10:
                        toolbar.setTitle(tabs11);
                        break;
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




    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (nooftabs==7){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs7);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==6){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)), tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)), tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)), tabs6);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==5){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)), tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)), tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==4){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)), tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)), tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), tabs4);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==8){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragment8(getResources().getColor(R.color.cardview_light_background)), tabs8);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==9){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragment8(getResources().getColor(R.color.cardview_light_background)), tabs8);
            adapter.addFrag(new DummyFragment9(getResources().getColor(R.color.cardview_light_background)),tabs9);

            viewPager.setAdapter(adapter);
        }
        if (nooftabs==10){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragment8(getResources().getColor(R.color.cardview_light_background)), tabs8);
            adapter.addFrag(new DummyFragment9(getResources().getColor(R.color.cardview_light_background)),tabs9);
            adapter.addFrag(new DummyFragment10(getResources().getColor(R.color.cardview_light_background)),tabs10);
            viewPager.setAdapter(adapter);
        }
        if (nooftabs==11){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)),tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)),tabs3);
            adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)),tabs4);
            adapter.addFrag(new DummyFragment5(getResources().getColor(R.color.cardview_light_background)), tabs5);
            adapter.addFrag(new DummyFragment6(getResources().getColor(R.color.cardview_light_background)),tabs6);
            adapter.addFrag(new DummyFragment7(getResources().getColor(R.color.cardview_light_background)), tabs7);
            adapter.addFrag(new DummyFragment8(getResources().getColor(R.color.cardview_light_background)), tabs8);
            adapter.addFrag(new DummyFragment9(getResources().getColor(R.color.cardview_light_background)),tabs9);
            adapter.addFrag(new DummyFragment10(getResources().getColor(R.color.cardview_light_background)),tabs10);
            adapter.addFrag(new DummyFragment11(getResources().getColor(R.color.cardview_light_background)),tabs11);
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

    public static class DummyFragment extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        public DummyFragment() {


        }

        @SuppressLint("ValidFragment")
        public DummyFragment(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home, container, false);
            textView=(TextView) view.findViewById(R.id.textView);
            if (tool==1){
                if (pos==0){
                    textView.setText(socialdata.Home.data);
                }
                if (pos==1){
                    Spanned text = Html.fromHtml(socialdata.Home.data.toString());

                }
                if (pos==2){
                    textView.setText("this is not pos==1");
                }
            }
            else {
                if(pos1==0)
                    textView.setText(socialdata.Home.data);
            }

            return view;
        }
    }

    public static class DummyFragmenty extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        public DummyFragmenty() {
            ;

        }

        @SuppressLint("ValidFragment")
        public DummyFragmenty(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home, container, false);
            textView=(TextView) view.findViewById(R.id.textView);
            if (pos==0){
                textView.setText(socialdata.Home.data);
            }
            if (pos==1){
                textView.setText("this is not soccon");
            }
            return view;
        }
    }

    public static class DummyFragmentx extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        public DummyFragmentx() {

        }

        @SuppressLint("ValidFragment")
        public DummyFragmentx(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.home, container, false);
            textView=(TextView) view.findViewById(R.id.textView);
            if (pos==0){
                textView.setText(socialdata.Home.data);
            }
            if (pos==1){
                textView.setText("this is not soccon");
            }
            return view;
        }
    }
    public static class DummyFragment1 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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
           /*View view = inflater.inflate(R.layout.home, container, false);
            textView=(TextView) view.findViewById(R.id.textView);

            textView.setMovementMethod(LinkMovementMethod.getInstance());

            textView.setMovementMethod(new ScrollingMovementMethod());
            Toast.makeText(getContext(),Title, Toast.LENGTH_SHORT).show();
       /*     Log.d("ghh", Title);

            Log.d("ghh",test);

            Log.d("ghh",Boolean.toString(Title==test));*/

            if (Title.compareTo("Embedded")==0){


               Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab1.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
              /*  Spanned text = Html.fromHtml(technical.tab1.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);*/

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab1.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);

             //   List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab1.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
               // List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab1.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
               // List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){

                Spanned text = Html.fromHtml(technical.tab1.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
               // List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battle City")==0){

                Spanned text = Html.fromHtml(tech2.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
             //   List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Chaos")==0){

                Spanned text = Html.fromHtml(tech2.Home.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
             //   List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOPC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
               // List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Home.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
              //  List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Crypto")==0){

                Spanned text = Html.fromHtml(tech2.Home.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
                //List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IHPC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
                //List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Home.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Home.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();
                list.add(text);
                adapter = new SimpleRecycleAdapter2(list);
                recyclerView.setAdapter(adapter);
                //List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Home.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Home.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Home.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Home.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Home.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Home.data14.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Home.data15.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Home.data16.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Home.data17.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab1.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab1.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab1.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab1.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment2 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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


            if (Title.compareTo("Embedded")==0){
                Spanned text = Html.fromHtml(socialdata.challengeAreas.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab2.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab2.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab2.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab2.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){

                Spanned text = Html.fromHtml(technical.tab2.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battle City")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Chaos")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOPC")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Crypto")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IHPC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Abstract.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Home.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Home.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Home.data14.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Home.data15.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Home.data16.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Home.data17.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab3.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab2.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab2.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab2.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment3 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
        SimpleRecycleAdapter3 adapter;
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


            if (Title.compareTo("Embedded")==0){



            }
            else if (Title.compareTo("Bridge Design Challenge")==0){
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/bridge-1correct.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/bridge-1.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("https://2016.techkriti.org/extras/arena/bridge-3.jpg");
                List<String> list4 = new ArrayList<String>();

                list4.add("https://2016.techkriti.org/extras/arena/bridge-4.jpg");
                List<String> list5 = new ArrayList<String>();
list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();
                Spanned text=Html.fromHtml("");
                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);



            }
            else if (Title.compareTo("Junkyard Wars")==0){
                /*Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/junk-1.JPG")
                        .into(img1);
                        */
                Spanned text = Html.fromHtml(technical.tab5.data2.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/junk-1.JPG");
                List<String> list2 = new ArrayList<String>();
                list2.add("");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab3.data3.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("");
                List<String> list2 = new ArrayList<String>();
                list2.add("");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){
              /*  Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/tgp-1.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/tgp-2.jpg")
                        .into(img2);*/
                Spanned text = Html.fromHtml(technical.tab3.data4.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/tgp-1.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/tgp-2.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);


            }
            else if (Title.compareTo("Concatenate")==0){
               /* Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/con-1.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/con-2.jpg")
                        .into(img2);*/
                Spanned text = Html.fromHtml(technical.tab4.data5.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/con-1.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/con-2.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){
                /*Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/hoverx.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/hoverx1.jpg")
                        .into(img2);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/hoverx2.jpg")
                        .into(img3);*/
                Spanned text = Html.fromHtml(tech2.Arena.data6.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/hoverx.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/hoverx1.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("https://2016.techkriti.org/extras/arena/hoverx2.jpg");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){
               /* Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/impulse.jpg")
                        .into(img1);*/
                Spanned text = Html.fromHtml(tech2.Arena.data7.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/impulse.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);


            }
            else if (Title.compareTo("Multirotor")==0){
            /*    Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/multi-1.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/multi-2.jpg")
                        .into(img2);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/multi-3.jpg")
                        .into(img3);*/
                Spanned text = Html.fromHtml(tech2.Arena.data8.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/multi-1.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/multi-2.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("https://2016.techkriti.org/extras/arena/multi-3.jpg");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);
            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Arena.data9.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("");
                List<String> list2 = new ArrayList<String>();
                list2.add("");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);


            }
            else if (Title.compareTo("Sky Sparks")==0){
               /* Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/skyspark1.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/skyspark.jpg")
                        .into(img2);*/


                Spanned text = Html.fromHtml(tech2.Arena.data10.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/skyspark1.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/skyspark.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);
            }
            else if (Title.compareTo("Wild Soccer")==0){
              /*  Picasso.with(getContext())
                        .load("http://2015.techkriti.org/competitions/images/arena/wildsoccerarena.jpg")
                        .into(img1);*/
                Spanned text = Html.fromHtml(technical.tab2.data10.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2015.techkriti.org/competitions/images/arena/wildsoccerarena.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);


            }
            else if (Title.compareTo("IARC")==0){
               /* Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/iarc-1.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/iarc-2.jpg")
                        .into(img2);*/
                Spanned text = Html.fromHtml(technical.tab10.data11.toString());
             //   List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/iarc-1.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/iarc-2.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){
               /* Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/mano/mano.jpg")
                        .into(img1);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/mano/top-2.jpg")
                        .into(img2);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/mano/top.jpg")
                        .into(img3);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/mano/iso-zoom.jpg")
                        .into(img4);
                Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/mano/pit.jpg")
                        .into(img5);*/
                Spanned text = Html.fromHtml(technical.tab10.data12.toString());
             //   List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2016.techkriti.org/extras/arena/mano/mano.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("https://2016.techkriti.org/extras/arena/mano/top-2.jpg");
                List<String> list3 = new ArrayList<String>();
                list3.add("https://2016.techkriti.org/extras/arena/mano/top.jpg");
                List<String> list4 = new ArrayList<String>();

                list4.add("https://2016.techkriti.org/extras/arena/mano/iso-zoom.jpg");
                List<String> list5 = new ArrayList<String>();
                list5.add("https://2016.techkriti.org/extras/arena/mano/pit.jpg");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){
               /* Picasso.with(getContext())
                        .load("http://2016.techkriti.org/extras/arena/irgt.png")
                        .into(img1);*/
                Spanned text = Html.fromHtml(technical.tab4.data13.toString());
                List<String> list1 = new ArrayList<String>();
                list1.add("https://2015.techkriti.org/competitions/images/arena/irgt.jpg");
                List<String> list2 = new ArrayList<String>();
                list2.add("");
                List<String> list3 = new ArrayList<String>();
                list3.add("");
                List<String> list4 = new ArrayList<String>();

                list4.add("");
                List<String> list5 = new ArrayList<String>();
                list5.add("");

                List<Spanned> list6 = new ArrayList<Spanned>();

                list6.add(text);
                adapter = new SimpleRecycleAdapter3(list1,list2,list3,list4,list5,list6);                 recyclerView.setAdapter(adapter);


                //  List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }


            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment4 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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


            if (Title.compareTo("Embedded")==0){
                Spanned text = Html.fromHtml(socialdata.rules.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab4.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab4.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab4.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab4.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){

                Spanned text = Html.fromHtml(technical.tab3.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Model_Specifications.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Model_Specifications.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Model_Specifications.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Model_Specifications
                        .data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Model_Specifications.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
           else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab4.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab4.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab4.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab3.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment5 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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


            if (Title.compareTo("Embedded")==0){
                Spanned text = Html.fromHtml(socialdata.faqs.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab5.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab3.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab5.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab5.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){

                Spanned text = Html.fromHtml(technical.tab5.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab5.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab5.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab5.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab5.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment6 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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



            if (Title.compareTo("Embedded")==0){
                Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){
                Spanned text = Html.fromHtml(technical.tab6.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab6.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab6.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab6.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab3.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab6.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab6.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab6.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab6.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Abstract.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Abstract.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Abstract.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }


            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment7 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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

            if (Title.compareTo("Embedded")==0){
                Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab7.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab7.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab7.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab7.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){

                Spanned text = Html.fromHtml(technical.tab7.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Useful_Links.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Abstract.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Useful_Links.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Useful_Links.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Useful_Links.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            } else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab7.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab7.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab7.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab7.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment8 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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

            if (Title=="Electromania"){
                Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Bridge Design Challenge")==0){

                Spanned text = Html.fromHtml(technical.tab8.data1.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Junkyard Wars")==0){

                Spanned text = Html.fromHtml(technical.tab8.data2.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab8.data3.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Techkriti Grand Prix")==0){

                Spanned text = Html.fromHtml(technical.tab8.data4.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Concatenate")==0){

                Spanned text = Html.fromHtml(technical.tab8.data5.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Useful_Links.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab8.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab8.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab8.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab8.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }

    }
    public static class DummyFragment9 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
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


            if (Title=="Electromania"){
                Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data6.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Multirotor")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data8.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Rule The Sky")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data9.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Sky Sparks")==0){

                Spanned text = Html.fromHtml(tech2.Contacts
                        .data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Wild Soccer")==0){

                Spanned text = Html.fromHtml(technical.tab9.data10.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab9.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab9.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab9.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment10 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment10() {

        }

        @SuppressLint("ValidFragment")
        public DummyFragment10(int color) {
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


            if (Title=="Electromania"){

                Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);


            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data7.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IARC")==0){

                Spanned text = Html.fromHtml(technical.tab3.data11.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("IRGT")==0){

                Spanned text = Html.fromHtml(technical.tab10.data13.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manoeuvre")==0){

                Spanned text = Html.fromHtml(technical.tab3.data12.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }
    public static class DummyFragment11 extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
        SimpleRecycleAdapter2 adapter;
        public DummyFragment11() {

        }

        @SuppressLint("ValidFragment")
        public DummyFragment11(int color) {
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

            if (Title=="Electromania"){
                Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else{
                textView.setText("this is not Electromania");

            }
            return view;
        }
    }


}
