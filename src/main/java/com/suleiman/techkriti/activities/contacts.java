package com.suleiman.techkriti.activities;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.suleiman.techkriti.R;

import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

import com.suleiman.techkriti.adapter.FlipAdapter9;
import com.suleiman.techkriti.adapter.FlipAdapter9.Callback;
import se.emilsjolander.flipview.FlipView.OnFlipListener;
import se.emilsjolander.flipview.FlipView.OnOverFlipListener;

import android.os.Bundle;

/**
 * Created by GUNDA ABHISHEK on 09-01-2016.
 */
public class contacts extends Activity implements Callback, OnFlipListener, OnOverFlipListener{

    private FlipView mFlipView;
    private FlipAdapter9 mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlipView = (FlipView) findViewById(R.id.flip_view);
        mAdapter = new FlipAdapter9(this);
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
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
