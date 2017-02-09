package com.suleiman.techkriti.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suleiman.techkriti.R;

import java.util.ArrayList;
import java.util.List;

public class FlipAdaptere extends BaseAdapter  {

    public interface Callback{
        public void onPageRequested(int page);
    }

    static class Item {
        static long id = 0;

        long mId;

        public Item() {
            mId = id++;
        }

        long getId(){
            return mId;
        }
    }

    private LayoutInflater inflater;
    private Callback callback;
    private List<Item> items = new ArrayList<Item>();

    public FlipAdaptere(Context context) {
        inflater = LayoutInflater.from(context);
        for(int i = 0 ; i<8 ; i++){
            items.add(new Item());
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.page2, parent, false);

            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.image=(ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if (position==0){
            Spanned tex= Html.fromHtml("TechPlanet Exhibition#1 <br> <b>Gesture Controlled Gaming</b>");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/gesture%20controlled%20gaming.jpg").resize(600,500)
                    .into(holder.image);
        }
        else  if(position ==1)  {
            Spanned tex1 = Html.fromHtml("TechPlanet Exhibition#2 <br> <b>Smart Vehicle Expo</b>");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/expo2.jpg").resize(600, 500)
                    .into(holder.image);
        }
        else  if(position ==2)  {
            Spanned tex1 = Html.fromHtml("TechPlanet Exhibition#3 <br> <b>Super Bike Expo</b>");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/super%20bikes%20expo.jpg").resize(600,500)
                    .into(holder.image);
        }
        else  if(position ==3)  {
            Spanned tex1 = Html.fromHtml("TechPlanet Exhibition#4 <br> <b>Smart Technology</b>");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/SMART%20TECHNOLOGY.jpg").resize(600, 500)
                    .into(holder.image);
        }

        //TODO set a text with the id as well
        else  if (position==4){
            Spanned tex= Html.fromHtml("Tech-Planet Exhibition#5 <br> <b>Smart Technologies</b>");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/SMART%20TECHNOLOGies.jpg").resize(600, 500)
                    .into(holder.image);
        }


        else if(position ==5)  {
            Spanned tex1 = Html.fromHtml("Tech-Planet Exhibition#6 <br> <b>Automated Chess</b>");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/exhibition%206.jpg").resize(600, 550)
                    .into(holder.image);
        }
        else if(position ==6)  {
            Spanned tex1 = Html.fromHtml("Techplanet Exhibition#7 <br<b> Golf Simulator</b> " );
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/golf%20simulator.jpg").resize(600, 500)
                    .into(holder.image);
        }

        else if(position ==7)  {
            Spanned tex1 = Html.fromHtml("Tech-Planet Exhibition#8 <br> <b>Vintage Car Expo</b>");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/exhibition/vintage%20cars.jpg").resize(600, 500)
                    .into(holder.image);
        }

        return convertView;
    }

    static class ViewHolder{
        TextView text;
        ImageView image;
    }

    public void addItems(int amount) {
        for(int i = 0 ; i<amount ; i++){
            items.add(new Item());
        }
        notifyDataSetChanged();
    }

    public void addItemsBefore(int amount) {
        for(int i = 0 ; i<amount ; i++){
            items.add(0, new Item());
        }
        notifyDataSetChanged();
    }

}
