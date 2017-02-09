package com.suleiman.techkriti.activities;

/**
 * Created by GUNDA ABHISHEK on 01-12-2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.suleiman.techkriti.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;
    // Keep all Images in array
    String mThumbIds[];

    public ImageAdapter(Context mContext,String url[]) {
        mThumbIds=url;
        this.mContext = mContext;
        layoutInflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View grid, ViewGroup parent){

        ImageView imageView;
        if(grid == null) {
            grid = layoutInflater.inflate(R.layout.grid_item1, null);
            imageView =  (ImageView)grid.findViewById(R.id.img);

            grid.setTag(imageView);

        } else {
            imageView = (ImageView)grid.getTag();
        }
        Picasso.with(mContext)
                .load(mThumbIds[position]).resize(512,400)
                .into(imageView);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return grid;

    }

}
