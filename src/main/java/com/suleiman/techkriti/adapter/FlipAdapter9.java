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

public class FlipAdapter9 extends BaseAdapter  {

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

    public FlipAdapter9(Context context) {
        inflater = LayoutInflater.from(context);
        for(int i = 0 ; i<2 ; i++){
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
            Spanned tex= Html.fromHtml("<b>Prakhar Jain</b><br> +91-9807885652 <br> prakhar@techkriti.org");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/A/images/team/prakhar.jpg")
                    .into(holder.image);
        }

        else  {
            Spanned tex1 = Html.fromHtml("<b>Rishi Gupta</b><br> +91-8953434295 <br> rishig@techkriti.org");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/A/images/team/rishi.jpg")
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
