package com.suleiman.techkriti.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suleiman.techkriti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class SimpleRecyclerAdapterstart extends RecyclerView.Adapter<SimpleRecyclerAdapterstart.VersionViewHolder> {
    List<String> versionModels;
    List<Integer> versionModels1;
    Boolean isHomeList = false;
    private  int lastPosition=-1;
    public static List<String> homeActivitiesList = new ArrayList<String>();
    public static List<String> homeActivitiesSubList = new ArrayList<String>();
    Context context;
    OnItemClickListener clickListener;


    public void setHomeActivitiesList(Context context) {
        String[] listArray = context.getResources().getStringArray(R.array.home_activities);
        String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
        for (int i = 0; i < listArray.length; ++i) {
            homeActivitiesList.add(listArray[i]);
            homeActivitiesSubList.add(subTitleArray[i]);
        }
    }

    public SimpleRecyclerAdapterstart(Context context) {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(context);
    }


    public SimpleRecyclerAdapterstart(List<String> versionModels,List<Integer> versionModels1,Context context) {
        isHomeList = false;
        this.versionModels = versionModels;
        this.versionModels1 = versionModels1;
        this.context = context;


    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_listing, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        if (isHomeList) {
            versionViewHolder.title.setText(homeActivitiesList.get(i));

            versionViewHolder.subTitle.setText(homeActivitiesSubList.get(i));
            versionViewHolder.subTitle.setText(homeActivitiesSubList.get(i));
        } else {
            versionViewHolder.title.setText(versionModels.get(i));
            Picasso.with(context)
                    .load(versionModels1.get(i)).resize(512,400)
                    .into(versionViewHolder.imageView);
        }
    }
    @Override
    public int getItemCount() {
        if (isHomeList)
            return homeActivitiesList == null ? 0 : homeActivitiesList.size();
        else
            return versionModels == null ? 0 : versionModels.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView title;
        TextView subTitle;
        ImageView imageView;


        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_name);
            imageView=(ImageView)itemView.findViewById(R.id.img);
            //     subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);

            if (isHomeList) {
                itemView.setOnClickListener(this);
                title.setOnClickListener(this);

            } else {
                //  subTitle.setVisibility(View.GONE);
                title.setOnClickListener(this);
                imageView.setOnClickListener(this);
                itemView.setOnClickListener(this);

            }

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
