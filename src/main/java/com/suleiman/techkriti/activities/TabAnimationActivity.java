package com.suleiman.techkriti.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

import com.suleiman.techkriti.adapter.FlipAdapter9;
import com.suleiman.techkriti.adapter.FlipAdapter9.Callback;
import se.emilsjolander.flipview.FlipView.OnFlipListener;
import se.emilsjolander.flipview.FlipView.OnOverFlipListener;
import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.FlipAdapter1;
import com.suleiman.techkriti.adapter.FlipAdapter2;
import com.suleiman.techkriti.adapter.FlipAdapter3;
import com.suleiman.techkriti.adapter.FlipAdapter4;
import com.suleiman.techkriti.adapter.FlipAdapter5;
import com.suleiman.techkriti.adapter.FlipAdapter6;
import com.suleiman.techkriti.adapter.FlipAdapter7;
import com.suleiman.techkriti.adapter.FlipAdapter8;

import java.util.ArrayList;
import java.util.List;

public class TabAnimationActivity extends NavDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_tab_animation, mContentFrame);

        Menu menu = mNavigationView.getMenu();
        menu.getItem(6).setChecked(true);
       /* final Toolbar toolbar = (Toolbar) findViewById(R.id.tabanim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);*/

        final ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabanim_tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
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
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DummyFragment(getResources().getColor(R.color.cardview_light_background)), "Festival Coordinator");
        adapter.addFrag(new DummyFragmenta(getResources().getColor(R.color.cardview_light_background)), "Marketing");
        adapter.addFrag(new DummyFragmentb(getResources().getColor(R.color.cardview_light_background)), "Events");
        adapter.addFrag(new DummyFragmentc(getResources().getColor(R.color.cardview_light_background)), "Media and Publicity");
        adapter.addFrag(new DummyFragmentd(getResources().getColor(R.color.cardview_light_background)), "Web and Design");
        adapter.addFrag(new DummyFragmente(getResources().getColor(R.color.cardview_light_background)), "Public Relations");
        adapter.addFrag(new DummyFragmentf(getResources().getColor(R.color.cardview_light_background)), "Finance");
        adapter.addFrag(new DummyFragmentg(getResources().getColor(R.color.cardview_light_background)), "Show Management");
        adapter.addFrag(new DummyFragmenth(getResources().getColor(R.color.cardview_light_background)), "Security");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fab_hide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
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

    public static class DummyFragment extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter9 mAdapter;
        public DummyFragment() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragment(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter9(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.prepend:
                    mAdapter.addItemsBefore(0);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }

    }
    public static class DummyFragmenta extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter1 mAdapter;
        public DummyFragmenta() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmenta(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter1(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmentb extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter2 mAdapter;
        public DummyFragmentb() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmentb(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter2(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmentc extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter3 mAdapter;
        public DummyFragmentc() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmentc(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter3(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmentd extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter4 mAdapter;
        public DummyFragmentd() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmentd(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter4(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmente extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter5 mAdapter;
        public DummyFragmente() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmente(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter5(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmentf extends Fragment {
        int color;
        private FlipView mFlipView;
        private FlipAdapter6 mAdapter;
        public DummyFragmentf() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmentf(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter6(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmentg extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter7 mAdapter;
        public DummyFragmentg() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmentg(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter7(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }
    public static class DummyFragmenth extends Fragment implements Callback, OnFlipListener, OnOverFlipListener{
        int color;
        private FlipView mFlipView;
        private FlipAdapter8 mAdapter;
        public DummyFragmenth() {
        }
        @SuppressLint("ValidFragment")
        public DummyFragmenth(int color) {
            this.color = color;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            mFlipView = (FlipView) view.findViewById(R.id.flip_view);
            mAdapter = new FlipAdapter8(getActivity());
            mFlipView.setAdapter(mAdapter);
            mFlipView.peakNext(false);
            mFlipView.setEmptyView(view.findViewById(R.id.empty_view));
            return view;
        }
        public void onPageRequested(int page) {
            mFlipView.smoothFlipTo(page);
        }

        public void onFlippedToPage(FlipView v, int position, long id) {
            Log.i("pageflip", "Page: "+position);
            if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
                mAdapter.addItems(0);
            }
        }

        public void onOverFlip(FlipView v, OverFlipMode mode,
                               boolean overFlippingPrevious, float overFlipDistance,
                               float flipDistancePerPage) {
            Log.i("overflip", "overFlipDistance = " + overFlipDistance);
        }
    }

}
