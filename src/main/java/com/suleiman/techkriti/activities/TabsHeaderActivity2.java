package com.suleiman.techkriti.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter;
import com.suleiman.techkriti.model.competitions_data;
import com.suleiman.techkriti.model.entreprenuerial;
import com.suleiman.techkriti.model.socialdata;
import com.suleiman.techkriti.model.tech2;
import com.suleiman.techkriti.model.technical;
import com.suleiman.techkriti.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class TabsHeaderActivity2 extends NavDrawerActivity {
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
    static int pos1;
    static String Title;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String url="";
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
        ViewGroup layout = (ViewGroup) toolbar.getParent();
  
        //toolbar.setVisibility(View.GONE);
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
//FloatingActionButton faby=(FloatingActionButton)findViewById(R.id.faby);
       /* faby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TabsHeaderActivity2.this, RegEmbed.class);
                intent.putExtra("title",Title);
                startActivity(intent);

            }
        });*/
        toolbarContainer = (LinearLayout) findViewById(R.id.fabhide_toolbar_container);

        // Adding list data thrice for a more comfortable scroll.

     //   fab = (FrameLayout) findViewById(R.id.myfab_main);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabButton);
       // fabBtn.setVisibility(View.GONE);

       // fabShadow = findViewById(R.id.myfab_shadow);
        //fabShadow.setVisibility(View.GONE);
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

        final ImageView header = (ImageView) findViewById(R.id.htab_header);


        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.header);
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



        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isInternetPresent = cd.isConnectingToInternet();

        if(isInternetPresent==false)
            url="";

else if(Title.compareTo("Electromania")==0)

    url="https://2016.techkriti.org/extras/ep/ecdc.jpg";
        else if(Title.compareTo("Battle City")==0)

            url="http://www.datacenterdynamics.com/pictures/2000x2000fit/2/4/8/9248_software-development-operations-devops-Thinkstock-buchachon.jpg";
        else if(Title.compareTo("Chaos")==0)

            url="http://www.datacenterdynamics.com/pictures/2000x2000fit/2/4/8/9248_software-development-operations-devops-Thinkstock-buchachon.jpg";
        else if(Title.compareTo("IOPC")==0)

            url="http://www.datacenterdynamics.com/pictures/2000x2000fit/2/4/8/9248_software-development-operations-devops-Thinkstock-buchachon.jpg";
        else if(Title.compareTo("Crypto")==0)

            url="http://www.datacenterdynamics.com/pictures/2000x2000fit/2/4/8/9248_software-development-operations-devops-Thinkstock-buchachon.jpg";
        else if(Title.compareTo("IHPC")==0)

            url="http://www.datacenterdynamics.com/pictures/2000x2000fit/2/4/8/9248_software-development-operations-devops-Thinkstock-buchachon.jpg";
        else if(Title.compareTo("IORC")==0)

url="http://img07.deviantart.net/6b62/i/2003/45/2/1/rubiks_cube.jpg";
        else if(Title.compareTo("Embedded")==0)
    url="https://2016.techkriti.org/extras/ep/ecdc.jpg";
        else if(Title.compareTo("WhatsUp")==0)
            url="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpaper-1.jpg";

        else if(Title.compareTo("AstroQuiz")==0)
            url="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpaper-1.jpg";
        else if(Title.compareTo("Astro Treasure")==0)
            url="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpaper-1.jpg";
        else if(Title.compareTo("Nutcracker")==0)
            url="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpaper-1.jpg";
        else if(Title.compareTo("CrimeRun")==0)
            url="http://cdn.wonderfulengineering.com/wp-content/uploads/2014/04/space-wallpaper-1.jpg";
        else if(Title.compareTo("FPGA")==0)
    url="https://2016.techkriti.org/extras/ep/ecdc.jpg";
else if(Title.compareTo("Electrade")==0)
    url="https://2016.techkriti.org/extras/ep/ecdc.jpg";
        else if(Title.compareTo("CrimeRun")==0)
            url="http://www.countthecosts.org/sites/default/files/Crime_700x250.jpg?1300386295";
        else if(Title.compareTo("Nutcracker")==0)

            url="http://2.bp.blogspot.com/-6wi9mnS56GQ/UPxgFFlEzSI/AAAAAAAANwc/6P0GWIU9o4A/s1600/6a00e54ee7b64288330147e0681f99970b-500wi.jpeg";
        else if(Title.compareTo("Scientoon")==0)
url="http://sketchbookjack.files.wordpress.com/2013/03/einstein.jpg";


        else
url="http://www.canberraentrepreneur.com/wp-content/uploads/2014/08/entrepreneurship-beginning.jpg";


if(url!="")
{
        Picasso.with(this)
                .load(url)
                .into(header);
    Picasso.with(this).load(url).into(target);}
else
{

    }

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
                intent.setData(Uri.parse("httpss://www.facebook.com/techkriti.iitk"));
                startActivity(intent);
            }
        });
        ImageView Button2 = (ImageView)findViewById(R.id.vid);
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("httpss://www.youtube.com/user/TechkritiIITK"));
                startActivity(intent);
            }
        });
        ImageView Button3 = (ImageView)findViewById(R.id.pho);
        Button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("httpss://twitter.com/techkriti_iitk"));
                startActivity(intent);
            }
        });
        ImageView Button4 = (ImageView)findViewById(R.id.goo);
        Button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("httpss://plus.google.com/+TechkritiOrg_iitk"));
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
        if (nooftabs==3){
            adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)),tabs1);
            adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)), tabs2);
            adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)), tabs3);
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

                if (pos==0){
                    textView.setText(socialdata.Home.data);
                }
                if (pos==1){
                    Spanned text = Html.fromHtml(socialdata.Home.data.toString());
                    // List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);
                }
                if (pos==2){
                    textView.setText("this is not pos==1");
                }



            return view;
        }
    }

    public static class DummyFragmenty extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        SimpleRecyclerAdapter adapter;
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

            final String sr="International_data";
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            List<String> list = new ArrayList<String>();

            if (Title=="Electromania"){
                for (int i = 0; i < competitions_data.International_data.data.length; i++) {
                    list.add(competitions_data.International_data.data[i]);
                }

            }
            else{
                for (int i = 0; i < competitions_data.International_data.data.length; i++) {
                    list.add(competitions_data.International_data.data[i]);
                }
            }
            adapter = new SimpleRecyclerAdapter(list);
            recyclerView.setAdapter(adapter);
            return view;
        }
    }

    public static class DummyFragmentx extends Fragment {
        int color;
        Context context;
        Intent intent;
        TextView textView;
        ImageView imageView;
        public DummyFragmentx() {

        }

        @SuppressLint("ValidFragment")
        public DummyFragmentx(int color) {
            this.color = color;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.imageandlist, container, false);
            textView=(TextView) view.findViewById(R.id.textView2);
            imageView=(ImageView)view.findViewById(R.id.imageView2);

            if (Title=="Electromania"){
                textView.setText(socialdata.Home.data);
                imageView.setImageResource(R.drawable.header);
            }
            else{
                textView.setText("this is not soccon");
                imageView.setImageResource(R.drawable.nav_header_bg);
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
       /*     Log.d("ghh", Title);

            Log.d("ghh",test);

            Log.d("ghh",Boolean.toString(Title==test));*/

            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab1.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab1.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab1.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab1.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Electrade")==0){

                Spanned text = Html.fromHtml(technical.tab1.data9.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Home.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
           else if (Title.compareTo("Battle City")==0){

                Spanned text = Html.fromHtml(tech2.Home.data.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Chaos")==0){

                Spanned text = Html.fromHtml(tech2.Home.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOPC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Home.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Crypto")==0){

                Spanned text = Html.fromHtml(tech2.Home.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IHPC")==0){

                Spanned text = Html.fromHtml(tech2.Home.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Hoverush")==0){

                Spanned text = Html.fromHtml(tech2.Home.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Impulse")==0){

                Spanned text = Html.fromHtml(tech2.Home.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

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
            else if (Title.compareTo("Battlefield")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Marketing Villa")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Be the Tycoon")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Countdown")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Stocksim")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Best Manager")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Elevator Pitch")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data9.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manmohan Gill Bio Buisnesss")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data10.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data11.toString());
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



            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab2.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab2.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab2.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab2.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Electrade")==0){

                Spanned text = Html.fromHtml(technical.tab2.data9.toString());
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

                Spanned text = Html.fromHtml(tech2.Rules.data5.toString());
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

                Spanned text = Html.fromHtml(tech2.Event_Structure.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data12.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data13.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data14.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data15.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Objective.data16.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data17.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battlefield")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Marketing Villa")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Be the Tycoon")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Countdown")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Stocksim")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Best Manager")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Elevator Pitch")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data9.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manmohan Gill Bio Buisnesss")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data10.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab2.data11.toString());
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



            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab3.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab3.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab3.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab3.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Electrade")==0){

                Spanned text = Html.fromHtml(technical.tab3.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battle City")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Chaos")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOPC")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Objective.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Crypto")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IHPC")==0){

                Spanned text = Html.fromHtml(tech2.Tutorial.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.Points_System.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data12.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data13.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data14.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data15.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Points_System.data16.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Problem_Statement.data17.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battlefield")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Marketing Villa")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Be the Tycoon")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Countdown")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Stocksim")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Best Manager")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Elevator Pitch")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data9.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manmohan Gill Bio Buisnesss")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data10.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab3.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

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

            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab4.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Scientoon")==0){

                Spanned text = Html.fromHtml(technical.tab4.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab4.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab4.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Electrade")==0){

                Spanned text = Html.fromHtml(technical.tab4.data9.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battle City")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Chaos")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOPC")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Abstract.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Crypto")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IHPC")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.Awards_and_Recognition.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data12.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data13.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Judging_Criteria.data14.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data15.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data16.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Incentive.data17.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battlefield")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Marketing Villa")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Be the Tycoon")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Countdown")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Stocksim")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data5.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Best Manager")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Elevator Pitch")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data9.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Manmohan Gill Bio Buisnesss")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data10.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab4.data11.toString());
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
        public DummyFragment5(){
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


            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab5.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab5.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab5.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Appathon")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data12.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data13.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data14.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data15.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data16.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data17.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Battlefield")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data1.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Marketing Villa")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Be the Tycoon")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data3.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Countdown")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data4.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
             else if (Title.compareTo("Best Manager")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }  else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Manmohan Gill Bio Buisnesss")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data10.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab5.data11.toString());
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

            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab6.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab6.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab6.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.FAQs.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data12.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data13.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data14.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data15.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Objective.data16.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Eligibility.data17.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }


            else if (Title.compareTo("Marketing Villa")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab6.data2.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }


             else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab6.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab6.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab1.data11.toString());
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

            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab7.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab7.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab7.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IORC")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data11.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("WhatsUp")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data12.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("AstroQuiz")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data13.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Astro Treasure")==0){

                Spanned text = Html.fromHtml(tech2.Event_Structure.data14.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Nutcracker")==0){

                Spanned text = Html.fromHtml(tech2.Rules.data15.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("CrimeRun")==0){

                Spanned text = Html.fromHtml(tech2.Objective.data16.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("IMP")==0){

                Spanned text = Html.fromHtml(tech2.Contacts.data17.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }



            else if (Title.compareTo("Buisness Venture")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab7.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab7.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab7.data11.toString());
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


            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab8.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab8.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab8.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("Social Track")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab8.data8.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }

            else if (Title.compareTo("IOT")==0){

                Spanned text = Html.fromHtml(entreprenuerial.tab8.data11.toString());
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


            if (Title.compareTo("Electromania")==0){

                Spanned text = Html.fromHtml(technical.tab9.data6.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("Embedded")==0){

                Spanned text = Html.fromHtml(technical.tab9.data7.toString());
                 List<Spanned> list = new ArrayList<Spanned>();                 list.add(text);                 adapter = new SimpleRecycleAdapter2(list);                 recyclerView.setAdapter(adapter);

            }
            else if (Title.compareTo("FPGA")==0){

                Spanned text = Html.fromHtml(technical.tab9.data8.toString());
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
            ;
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
