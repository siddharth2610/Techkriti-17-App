package com.suleiman.techkriti.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter1;
import com.suleiman.techkriti.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class profile extends NavDrawerActivity {
    Toolbar toolbar1;
    int fabMargin;
    LinearLayout toolbarContainer;
    int toolbarHeight;
    FrameLayout fab;
    ImageButton fabBtn;
    View fabShadow;
    LinearLayout mRevealView;
    boolean hidden = true;
    ImageView header;
    static int x;
  static  String username,name,phone,college,email,id,facebook,event[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tabs_header, mContentFrame);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        name=i.getStringExtra("name");
        phone=i.getStringExtra("phone");
        college=i.getStringExtra("college");
        email=i.getStringExtra("email");
        facebook=i.getStringExtra("facebook");
        event=i.getStringArrayExtra("event");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        final Toolbar toolbar2=(Toolbar)findViewById(R.id.toolbar);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        CoordinatorLayout coordinatorLayout=(CoordinatorLayout) findViewById(R.id.htab_maincontent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Technical");
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

        fab = (FrameLayout) findViewById(R.id.myfab_main);
        fabBtn = (ImageButton) findViewById(R.id.myfab_main_btn);
        fabShadow = findViewById(R.id.myfab_shadow);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fabShadow.setVisibility(View.GONE);
            fabBtn.setBackground(getDrawable(R.drawable.ripple_accent));
        }

        fab.startAnimation(animation);

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

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        header = (ImageView) findViewById(R.id.htab_header);

        header.setImageResource(R.drawable.nav_header_bg);

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
        ViewPager.OnPageChangeListener mlistener=new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                if (position == 0) {



                    header = (ImageView) findViewById(R.id.htab_header);
                    header.setImageResource(R.drawable.nav_header_bg);

                } else {



                    header = (ImageView) findViewById(R.id.htab_header);
                    header.setImageResource(R.drawable.header);


                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                return;
            }
        };
        viewPager.setOnPageChangeListener(mlistener);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        viewPager.setCurrentItem(0);
                        toolbar.setTitle("Profile");
                        break;
                    case 1:
                        toolbar.setTitle("Profile");
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
                intent.setData(Uri.parse("http://www.flipkart.com"));
                startActivity(intent);
            }
        });
        ImageView Button2 = (ImageView)findViewById(R.id.vid);
        Button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.facebook.com"));
                startActivity(intent);
            }
        });
        ImageView Button3 = (ImageView)findViewById(R.id.pho);
        Button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.youtube.com"));
                startActivity(intent);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.accent_material_light)), "User");
        adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.ripple_material_light)), "Registered Events");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        int x=1;
        SimpleRecyclerAdapter1 adapter;
        Context context;
        Intent intent;
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

            List<String> list = new ArrayList<String>();
            List<String> list1 = new ArrayList<String>();
            list.add(username);
            list.add(name);
            list.add(phone);list.add(college);list.add(facebook);
            list1.add("Username :");;
            list1.add("Name :");;
            list1.add("Phone :");;
            list1.add("College :");;
            list1.add("Facebook Id :");;



            adapter = new SimpleRecyclerAdapter1(list1,list);
            recyclerView.setAdapter(adapter);

            return view;
        }
    }


    public static class DummyFragment2 extends Fragment {
        int color;
        int x=1;
        SimpleRecyclerAdapter adapter;
        Context context;
        Intent intent;
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

            List<String> list = new ArrayList<String>();
            List<String> list1 = new ArrayList<String>();

            for (int i = 0; i < event.length; i++) {
                list.add(event[i]);
            }

            adapter = new SimpleRecyclerAdapter(list);
            recyclerView.setAdapter(adapter);

            return view;
        }
    }





}
