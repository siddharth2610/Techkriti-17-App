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

public class FlipAdapters extends BaseAdapter  {

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

    public FlipAdapters(Context context) {
        inflater = LayoutInflater.from(context);
        for(int i = 0 ; i<4 ; i++){
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
            Spanned tex= Html.fromHtml("We present to you the most awaited event of Techkriti'16 - <b>Farhan Akhtar LIVE</b> on 6th March, 2016. grin emoticon With one of the most popular and energetic performers of the country in the festival, this would be a night to remember!");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/shows/shows0.jpg").resize(494,662)
                    .into(holder.image);
        }
        //TODO set a text with the id as well
        if (position==1){
            Spanned tex= Html.fromHtml("Performing bike stunts is an art. It's a combination of impact and notoriety, the element of danger and the skill of the stunt performer.\n" +
                    "Be ready to witness an enthralling performance this Techkriti by <b>freestyle stunt riding team,Edge Ryderz</b>.");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/shows/shows1.jpg").resize(600,500)
                    .into(holder.image);
        }
        if (position==2){
            Spanned tex= Html.fromHtml("Presenting <b>Fire & LED Show by The Artmosphere</b> performers on 4th of March to light up the stage and visually mesmerize the audience. A magical and highly unique choreographed show is what this troupe of acrobatic, juggling and circus trained entertainers do best.");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/shows/shows2.jpg").resize(600,500)
                    .into(holder.image);
        }
        if (position==3){
            Spanned tex= Html.fromHtml("Inspiration, entrepreneurs, doers, success, fun, etc. You can't limit them in words.\n" +
                    "Thus, Techkriti brings to you Interactive Session with the web/youtube sensations <b>The Viral Fever!</b> A fun filled night with everyone's favorite characters on 6th March, 2016."
                    );
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load(R.drawable.tvf).resize(600,500)
                    .into(holder.image);
        }
        if (position==4){
            Spanned tex= Html.fromHtml("<b>EDM</b>");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/android/shows/shows3.jpg").resize(600,500)
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
