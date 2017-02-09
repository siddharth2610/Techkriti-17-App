package com.suleiman.techkriti.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suleiman.techkriti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class SimpleRecyclerAdapternotif extends RecyclerView.Adapter<SimpleRecyclerAdapternotif.VersionViewHolder> {
    List<String> versionModels;
    List<String> versionModels1;
    List<String> versionModels2;

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

    public SimpleRecyclerAdapternotif(Context context) {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(context);
    }


    public SimpleRecyclerAdapternotif(List<String> versionModels,List<String> versionModels1,List<String> versionModels2) {
        isHomeList = false;
        this.versionModels = versionModels;
        this.versionModels1 = versionModels1;
        this.versionModels2 = versionModels2;



    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notif, viewGroup, false);
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
            versionViewHolder.subTitle.setText(versionModels1.get(i));
            versionViewHolder.times.setText(versionModels2.get(i));


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
TextView times;

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_name);
            subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);
            times = (TextView) itemView.findViewById(R.id.times);


            //     subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);

            if (isHomeList) {
                itemView.setOnClickListener(this);
                title.setOnClickListener(this);

            } else {
                //  subTitle.setVisibility(View.GONE);
                title.setOnClickListener(this);
                subTitle.setOnClickListener(this);
                times.setOnClickListener(this);

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
