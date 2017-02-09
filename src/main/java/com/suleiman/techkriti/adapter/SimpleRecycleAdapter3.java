package com.suleiman.techkriti.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
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
 * Created by me on 14-04-2015.
 */
public class SimpleRecycleAdapter3 extends RecyclerView.Adapter<SimpleRecycleAdapter3.VersionViewHolder> {
    List<String> versionModels11;
    List<String> versionModels12;
    List<String> versionModels13;
    List<String> versionModels14;
    List<String> versionModels15;

    List<Spanned> versionModels1;

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

    public SimpleRecycleAdapter3(Context context) {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(context);
    }


    public SimpleRecycleAdapter3(List<String> versionModels11,List<String> versionModels12,List<String> versionModels13,List<String> versionModels14,List<String> versionModels15,List<Spanned> versionModels1) {
        isHomeList = false;
        this.versionModels11 = versionModels11;
        this.versionModels12 = versionModels12;
        this.versionModels13 = versionModels13;
        this.versionModels14 = versionModels14;
        this.versionModels15 = versionModels15;
        this.versionModels1 = versionModels1;



    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.imageandlist, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        if (isHomeList) {
            versionViewHolder.title.setText(homeActivitiesList.get(i));
        } else {
if(versionModels11.get(i)!="")
            Picasso.with(context)
                    .load(versionModels11.get(i))
                    .into(versionViewHolder.img1);
            if(versionModels12.get(i)!="")
            Picasso.with(context)
                    .load(versionModels12.get(i))
                    .into(versionViewHolder.img2);
            if(versionModels13.get(i)!="")
            Picasso.with(context)
                    .load(versionModels13.get(i))
                    .into(versionViewHolder.img3);
            if(versionModels14.get(i)!="")
            Picasso.with(context)
                    .load(versionModels14.get(i))
                    .into(versionViewHolder.img4);
            if(versionModels15.get(i)!="")
            Picasso.with(context)
                    .load(versionModels15.get(i))
                    .into(versionViewHolder.img5)
                    ;

            versionViewHolder.title.setText(versionModels1.get(i));
        }
    }
    @Override
    public int getItemCount() {
        if (isHomeList)
            return homeActivitiesList == null ? 0 : homeActivitiesList.size();
        else
            return versionModels11 == null ? 0 : versionModels11.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView title;
        ImageView img1,img3,img4,img5;
        ImageView img2;


        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.textView2);

            img1 = (ImageView) itemView.findViewById(R.id.imageView2);
            img2 = (ImageView) itemView.findViewById(R.id.imageView3);
            img3 = (ImageView) itemView.findViewById(R.id.imageView4);
            img4 = (ImageView) itemView.findViewById(R.id.imageView5);
            img5 = (ImageView) itemView.findViewById(R.id.imageView6);




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
           ;
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
