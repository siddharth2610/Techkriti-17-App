package com.suleiman.techkriti.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
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
public class SimpleRecycleAdapter2 extends RecyclerView.Adapter<SimpleRecycleAdapter2.VersionViewHolder> {
    List<Spanned> versionModels;
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

    public SimpleRecycleAdapter2(Context context) {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(context);
    }


    public SimpleRecycleAdapter2(List<Spanned> versionModels) {
        isHomeList = false;
        this.versionModels = versionModels;


    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        if (isHomeList) {
            versionViewHolder.title.setText(homeActivitiesList.get(i));
        } else {
            versionViewHolder.title.setText(versionModels.get(i));
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

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.textView);
            title.setMovementMethod(LinkMovementMethod.getInstance());
            title.setLinksClickable(true);
            if (isHomeList) {
                itemView.setOnClickListener(this);
                title.setOnClickListener(this);
            } else {

                title.setOnClickListener(this);
                itemView.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View v) {

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
