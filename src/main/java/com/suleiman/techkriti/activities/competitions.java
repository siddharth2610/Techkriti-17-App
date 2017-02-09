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
import android.support.design.widget.AppBarLayout;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdapter;
import com.suleiman.techkriti.adapter.SimpleRecyclerAdaptern;
import com.suleiman.techkriti.model.competitions_data;
import com.suleiman.techkriti.utils.MyRecyclerScroll;
import com.suleiman.techkriti.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

public class competitions extends NavDrawerActivity {
    Toolbar toolbar;
    Toolbar toolbar2;
  static  int fabMargin;
   static LinearLayout toolbarContainer;
   static int toolbarHeight;
  static   FrameLayout fab;
    static FloatingActionButton fabBtn;
    View fabShadow;
    LinearLayout mRevealView;
    boolean hidden = true;
    ImageView header;
   static int x;
    String name,id;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appbarlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tabs_header, mContentFrame);
        toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
     appbarlayout= (AppBarLayout) findViewById(R.id.htab_appbar);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(1).setChecked(true);


         toolbar2=(Toolbar)findViewById(R.id.toolbar);
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
        //FloatingActionButton faby=(FloatingActionButton)findViewById(R.id.faby);
//faby.setVisibility(View.GONE);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabButton);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        Picasso.with(getApplicationContext())
                .load("http://www.breadalbane.pkc.sch.uk/BA/wp-content/uploads/2014/05/Technical-Drawings2.jpg")
                .into(header);
        Picasso.with(this).load("http://www.breadalbane.pkc.sch.uk/BA/wp-content/uploads/2014/05/Technical-Drawings2.jpg").into(target);

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
                if (position == 2) {



                    header = (ImageView) findViewById(R.id.htab_header);

                    Picasso.with(getApplicationContext())
                            .load("https://www.unityworldwideministries.org/sites/unityministries.oneeach.org/files/handsSeaFeature.jpg")
                            .into(header);
                    Picasso.with(getApplicationContext())
                            .load("https://www.unityworldwideministries.org/sites/unityministries.oneeach.org/files/handsSeaFeature.jpg")
                            .into(target);

                }
                else if(position==1) {


                    header = (ImageView) findViewById(R.id.htab_header);

                    Picasso.with(getApplicationContext())
                            .load("http://saudinibras.com/images/portfolio/bg01.jpg")
                            .into(header);
                    Picasso.with(getApplicationContext()).load("http://saudinibras.com/images/portfolio/bg01.jpg").into(target);


                }
                else if(position==3) {


                    header = (ImageView) findViewById(R.id.htab_header);

                    Picasso.with(getApplicationContext())
                            .load("http://www.tsinghua.edu.cn/publish/iren/2150/20101227102749979735148/20101227102834028810628.jpg")
                            .into(header);

                    Picasso.with(getApplicationContext())
                            .load("http://www.tsinghua.edu.cn/publish/iren/2150/20101227102749979735148/20101227102834028810628.jpg")
                            .into(target);


                }
                else if(position==0) {


                    header = (ImageView) findViewById(R.id.htab_header);

                    Picasso.with(getApplicationContext())
                            .load("http://www.breadalbane.pkc.sch.uk/BA/wp-content/uploads/2014/05/Technical-Drawings2.jpg")
                            .into(header);
                    Picasso.with(getApplicationContext())
                            .load("http://www.breadalbane.pkc.sch.uk/BA/wp-content/uploads/2014/05/Technical-Drawings2.jpg")
                            .into(target);


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
                        toolbar.setTitle("Technical");
                        break;
                    case 1:
                        toolbar.setTitle("Entrepreneurial");
                        break;
                    case 2:
                        toolbar.setTitle("Social");
                        break;
                    case 3:
                        toolbar.setTitle("International");
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
                    appbarlayout.setBackgroundColor(vibrantColor);
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
        adapter.addFrag(new DummyFragment1(getResources().getColor(R.color.cardview_light_background)), "Technical");
        adapter.addFrag(new DummyFragment2(getResources().getColor(R.color.cardview_light_background)), "Entrepreneurial");
        adapter.addFrag(new DummyFragment3(getResources().getColor(R.color.cardview_light_background)), "Social");
        adapter.addFrag(new DummyFragment4(getResources().getColor(R.color.cardview_light_background)), "International");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

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

    public static class DummyFragment3 extends Fragment {
        int color;
      int x=1;
        SimpleRecyclerAdaptern adapter;
        Context context;
        Intent intent;
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

            List<String> list = new ArrayList<String>();

            for (int i = 0; i < competitions_data.data.length; i++) {
                list.add(competitions_data.data[i]);
            }
            adapter = new SimpleRecyclerAdaptern(list);
            recyclerView.setAdapter(adapter);
            recyclerView.addOnScrollListener(new MyRecyclerScroll() {
                @Override
                public void show() {
                    toolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                    toolbarContainer.animate().alpha(1).setInterpolator(new DecelerateInterpolator(1)).start();
                    fabBtn.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                }

                @Override
                public void hide() {
                    toolbarContainer.animate().translationY(-toolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
                    toolbarContainer.animate().alpha(0).setInterpolator(new AccelerateInterpolator(1)).start();
                    fabBtn.animate().translationY(fabBtn.getHeight() + fabMargin).setInterpolator(new AccelerateInterpolator(2)).start();
                }
            });
            adapter.SetOnItemClickListener(new SimpleRecyclerAdaptern.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    switch (position) {
                        case 0:
                            intent = new Intent(view.getContext(), TabsHeaderActivity.class);
                             intent.putExtra("id",position);
                            intent.putExtra("Title", competitions_data.data[position]);
                            intent.putExtra("tabs",6);
                            intent.putExtra("activity",x);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2","Challenge Areas").putExtra("tabstr3","Event Structure").putExtra("tabstr4","Rules").putExtra("tabstr6","Contacts").putExtra("tabstr5","FAQs");
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(view.getContext(), TabsHeaderActivity.class);
                            intent.putExtra("id",position);
                            intent.putExtra("tabs",6);
                            intent.putExtra("Title", competitions_data.data[position]);
                            intent.putExtra("activity",x);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2","Abstract").putExtra("tabstr3","Event Structure").putExtra("tabstr4","Rules").putExtra("tabstr6","Contacts").putExtra("tabstr5", "Theme");
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(view.getContext(), TabsHeaderActivity.class);
                            intent.putExtra("id",position);
                            intent.putExtra("tabs",6);
                            intent.putExtra("Title", competitions_data.data[position]);
                            intent.putExtra("activity",x);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2","Topics").putExtra("tabstr3","Rules").putExtra("tabstr4","Checklist").putExtra("tabstr5","Declaration").putExtra("tabstr6", "Contacts");
                            startActivity(intent);
                            break;
                        default:

                    }
                }
            });
            return view;
        }
    }
    public static class DummyFragment4 extends Fragment {
        int color;
        SimpleRecyclerAdaptern adapter;
        Context context;
        Intent intent;
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

            final String sr="International_data";
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);

            List<String> list = new ArrayList<String>();


            for (int i = 0; i < competitions_data.International_data.data.length; i++) {
                list.add(competitions_data.International_data.data[i]);
            }

            adapter = new SimpleRecyclerAdaptern(list);
            recyclerView.setAdapter(adapter);
            adapter.SetOnItemClickListener(new SimpleRecyclerAdaptern.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position1) {
                    switch (position1) {
                        case 0:
                            intent = new Intent(view.getContext(), TabsHeaderActivity1.class);
                            intent.putExtra("Title", competitions_data.International_data.data[position1]);
                            intent.putExtra("id",position1);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Rules").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Event Structure").putExtra("tabstr5", "Abstract").putExtra("tabstr6","Robot Controls").putExtra("tabstr7","Bot Specifications").putExtra("tabstr8", "Point System").putExtra("tabstr9","Contacts").putExtra("tabstr10", "Mission Objective");
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(view.getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title", competitions_data.International_data.data[position1]);
                            intent.putExtra("id",position1);
                            intent.putExtra("tabs", 9);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2", "Abstract").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Model Specifications").putExtra("tabstr5", "Rules and Regulations").putExtra("tabstr6", "Judging Criteria").putExtra("tabstr7", "Useful Links").putExtra("tabstr8", "FAQs").putExtra("tabstr9", "Contacts");

                            startActivity(intent);
                            break;
                        case 2:
                            intent=new Intent(getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title", competitions_data.International_data.data[position1]);
                            intent.putExtra("id",position1);
                            intent.putExtra("tabs", 8);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Bot Specifications").putExtra("tabstr3", "Arena").putExtra("tabstr4","Gameplay").putExtra("tabstr5", "Abstract").putExtra("tabstr6", "Rules").putExtra("tabstr7","Point").putExtra("tabstr8", "Contacts");
                            startActivity(intent);

                            break;
                        case 3:
                            intent=new Intent(getContext(),TabsHeaderActivity2.class);
                            intent.putExtra("Title", competitions_data.International_data.data[position1]);
                            intent.putExtra("id",position1);
                            intent.putExtra("tabs", 3);
                            intent.putExtra("tabstr1", "Home").putExtra("tabstr2","Rules").putExtra("tabstr3","Contacts");
                            startActivity(intent);
                            break;

                        case 4:
                            intent=new Intent(getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title", competitions_data.International_data.data[position1]);
                            intent.putExtra("id",position1);
                            intent.putExtra("tabs", 8);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2","Event Structure").putExtra("tabstr3","Arena").putExtra("tabstr4","Problem Statement").putExtra("tabstr5", "Machine Specification").putExtra("tabstr6", "Rules").putExtra("tabstr7","Abstract").putExtra("tabstr8","Contacts");
                            getContext().startActivity(intent);
                            break;
                        case 5:
                            intent=new Intent(getContext(),TabsHeaderActivity1.class);
                            intent.putExtra("Title", competitions_data.International_data.data[position1]);
                            intent.putExtra("id", position1);
                            intent.putExtra("tabs", 10);
                            intent.putExtra("tabstr1","Home").putExtra("tabstr2", "Objective").putExtra("tabstr3", "Arena").putExtra("tabstr4", "Rules").putExtra("tabstr5", "Bot Specifications").putExtra("tabstr6", "Gameplay").putExtra("tabstr7", "Point System").putExtra("tabstr8", "Event Structure").putExtra("tabstr9", "Abstract").putExtra("tabstr10","Contacts");
                            getContext().startActivity(intent);
                            break;
                        default:

                    }
                }
            });

            return view;



        }
    }
    public static class DummyFragment1 extends Fragment {
        int color;
        SimpleRecyclerAdapter adapter;

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
            List<com.suleiman.techkriti.activities.ExpandableListAdapter.Item> list=new ArrayList<>();
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item design = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "Design Events");
            design.invisibleChildren = new ArrayList<>();
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Bridge Design Challenge"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Junkyard Wars"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Scientoon"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Techkriti Grand Prix"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Concatenate"));
            list.add(design);
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item places = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "ECDC");
            places.invisibleChildren = new ArrayList<>();
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Electromania"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Embedded"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "FPGA"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Electrade"));
            list.add(places);
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item robotics = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "Robotics");
            robotics.invisibleChildren = new ArrayList<>();
            robotics.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Wild Soccer"));
            robotics.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "IARC"));
            robotics.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Manoeuvre"));
            robotics.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "IRGT"));
            list.add(robotics);
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item software = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "Software Corner");
            software.invisibleChildren = new ArrayList<>();
           software.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Battle City"));
            software.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Chaos"));
            software.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "IOPC"));
            software.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Appathon"));
            software.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Crypto"));
            software.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "IHPC"));
            list.add(software);
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item takeoff = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "TakeOff");
           takeoff.invisibleChildren = new ArrayList<>();
            takeoff.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Hoverush"));
            takeoff.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Impulse"));
            takeoff.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Multirotor"));
           takeoff.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Rule The Sky"));
            takeoff.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Sky Sparks"));
            list.add(takeoff);
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item mixedbowl = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "Mixed Bowl");
            mixedbowl.invisibleChildren = new ArrayList<>();
            mixedbowl.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "WhatsUp"));
            mixedbowl.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "AstroQuiz"));
            mixedbowl.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Astro Treasure"));
            mixedbowl.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Nutcracker"));
            mixedbowl.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "CrimeRun"));
            mixedbowl.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "IORC"));
            list.add(mixedbowl);
            recyclerView.setAdapter(new com.suleiman.techkriti.activities.ExpandableListAdapter(list,linearLayoutManager));

            return view;
        }
    }
    public static class DummyFragment2 extends Fragment {
        int color;
        SimpleRecyclerAdapter adapter;

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

            recyclerView.addOnScrollListener(new MyRecyclerScroll() {

                @Override
                public void show() {
                  fabBtn  .animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                }

                @Override
                public void hide() {

                    fabBtn.animate().translationY(fabBtn.getHeight() + fabMargin).setInterpolator(new AccelerateInterpolator(2)).start();
                }
            });
            List<com.suleiman.techkriti.activities.ExpandableListAdapter.Item> list=new ArrayList<>();


            com.suleiman.techkriti.activities.ExpandableListAdapter.Item places = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "Buisness");
            places.invisibleChildren = new ArrayList<>();
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Battlefield"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Marketing Villa"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Be the Tycoon"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Countdown"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Stocksim"));
            places.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Best Manager"));
            list.add(places);
            com.suleiman.techkriti.activities.ExpandableListAdapter.Item design = new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.HEADER, "Entrepreneurial Ideas");
            design.invisibleChildren = new ArrayList<>();
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Buisness Venture"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Social Track"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "Elevator Pitch"));
         //   design.invisibleChildren.add(new com.suleiman.com.techkriti.beyondplanet.activities.ExpandableListAdapter.Item(com.suleiman.com.techkriti.beyondplanet.activities.ExpandableListAdapter.CHILD, "Manmohan Gill Bio Buisness"));
            design.invisibleChildren.add(new com.suleiman.techkriti.activities.ExpandableListAdapter.Item(com.suleiman.techkriti.activities.ExpandableListAdapter.CHILD, "IOT"));
            list.add(design);
            recyclerView.setAdapter(new com.suleiman.techkriti.activities.ExpandableListAdapter(list,linearLayoutManager));

            return view;
        }
    }



}
