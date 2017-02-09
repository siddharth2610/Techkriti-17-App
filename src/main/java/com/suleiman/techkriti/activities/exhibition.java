package com.suleiman.techkriti.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.suleiman.techkriti.R;
import com.suleiman.techkriti.adapter.FlipAdaptere.Callback;
import com.suleiman.techkriti.adapter.FlipAdaptere;

import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.FlipView.OnFlipListener;
import se.emilsjolander.flipview.FlipView.OnOverFlipListener;
import se.emilsjolander.flipview.OverFlipMode;

public class exhibition extends NavDrawerActivity implements Callback, OnFlipListener, OnOverFlipListener {

    private FlipView mFlipView;
    private FlipAdaptere mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_talks, mContentFrame);



        Menu menu = mNavigationView.getMenu();
        menu.getItem(4).setChecked(true);

        mFlipView = (FlipView) findViewById(R.id.flip_view);
        mAdapter = new FlipAdaptere(this);
        mAdapter.setCallback(this);
        mFlipView.setAdapter(mAdapter);
        mFlipView.setOnFlipListener(this);
        mFlipView.peakNext(false);
        mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
        mFlipView.setEmptyView(findViewById(R.id.empty_view));
        mFlipView.setOnOverFlipListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fab_hide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageRequested(int page) {
        mFlipView.smoothFlipTo(page);
    }

    @Override
    public void onFlippedToPage(FlipView v, int position, long id) {
        if(position > mFlipView.getPageCount()-3 && mFlipView.getPageCount()<30){
            mAdapter.addItems(0);
        }
    }

    @Override
    public void onOverFlip(FlipView v, OverFlipMode mode,
                           boolean overFlippingPrevious, float overFlipDistance,
                           float flipDistancePerPage) {

    }


}
