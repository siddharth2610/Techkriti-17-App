package com.suleiman.techkriti.adapter;
import java.util.ArrayList;
import java.util.List;

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

public class FlipAdapter6 extends BaseAdapter  {

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

    public FlipAdapter6(Context context) {
        inflater = LayoutInflater.from(context);
        for(int i = 0 ; i<1 ; i++){
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
            convertView = inflater.inflate(R.layout.page, parent, false);

            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.image=(ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //TODO set a text with the id as well
        if (position==0){
            Spanned tex= Html.fromHtml("<b>Ankit</b><br> +91-8090620838 <br> ankit@techkriti.org");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/A/images/team/ankit.jpg")
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
