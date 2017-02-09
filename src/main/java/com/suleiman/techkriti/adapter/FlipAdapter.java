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

public class FlipAdapter extends BaseAdapter  {

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

    public FlipAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        for(int i = 0 ; i<12     ; i++){
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
            convertView = inflater.inflate(R.layout.page1, parent, false);

            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.image=(ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if (position==0){
            Spanned tex= Html.fromHtml("Former President of Afghanistan <b>Hamid Karzai</b> served as <b>President of Afghanistan</b> for almost ten years, from 7 December 2004 to 29 September 2014.");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load(R.drawable.hamid).resize(600,500)
                    .into(holder.image);
        }
        //TODO set a text with the id as well
      else  if (position==4){
            Spanned tex= Html.fromHtml("<b>Sanjay Katkar</b>, <b>MD</b> and <b>CEO</b> of <b>Quick Heal Technologies Limited</b>, has redesigned the entire product portfolio and elevated the role of Quick Heal as a trustworthy IT security brand in more than 80 countries");
            holder.text.setText(tex);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image04.png").resize(600,500)
                    .into(holder.image);
        }

      else  if(position ==3)  {
            Spanned tex1 = Html.fromHtml("Amit Sethi\n" +"<b>CIO, AXIS BANK</b>, Winner of The Asian Banker Bank Technology Leadership Achievement Award for 2015. He re-engineered the processes and re-layered the IT architecture of the bank.\n");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image10.png")
                    .into(holder.image);
        }
      else  if(position ==2)  {
            Spanned tex1 = Html.fromHtml("<b>Netanel Raisch</b> developed <b>Lishtot</b>, Jerusalem-based startup won a place on CNBC’s list of the 20 hottest startups of 2015 for its device that takes about two seconds to tell you if water is pure or contaminated.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image02.jpg").resize(600,500)
                    .into(holder.image);
        }
       else if(position ==5)  {
            Spanned tex1 = Html.fromHtml("<b>Anurag Batra</b> is a first generation entrepreneur and currently serving as <b>Chairman</b> and <b>Editor-in-Chief</b> of exchange4media group.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image05.png").resize(600, 550)
                    .into(holder.image);
        }
       else if(position ==6)  {
            Spanned tex1 = Html.fromHtml("<b>Vikas Swarup</b> is the author of the <b>novel Q & A</b>, adapted in film as <b>Slumdog Millionaire</b>, the winner of Best Film for the year 2009 at the Academy Awards. Currently, he is the <b>Spokesperson, Ministry of External Affairs, India</b>.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image03.png").resize(600, 500)
                    .into(holder.image);
        }
      else  if(position ==1)  {
            Spanned tex1 = Html.fromHtml("<b>Lyn Evans</b> is the <b>Director</b> of the <b>Linear Collider collaboration at CERN</b>. He has also served as the project leader of Large Hadron Collider in the past.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image01.png")
                    .into(holder.image);
        }
       else if(position ==7)  {
            Spanned tex1 = Html.fromHtml("<b>Mike Libecki</b>\n" +
                    "A climber turned explorer with more than 45 expeditions to his name, searches out the last truly wild places on the planet.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image08.jpg")
                    .into(holder.image);
        }
      else  if(position ==8)  {
            Spanned tex1 = Html.fromHtml("<b>Pradeep Sindhu</b>, an alumnus of <b>IIT Kanpur</b> is an Indian American entrepreneur who is the co-founder and <b>Chief Technical Officer/Vice Chairman of the Board of Directors of Juniper Networks Inc</b>.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image09.png").resize(600, 500)
                    .into(holder.image);
        }
       else if(position ==9)  {
            Spanned tex1 = Html.fromHtml("<b>Jorge Gabriel Cham</b> is a<b> Chinese Panamanian cartoonist and roboticist</b> popularly known as the creator of \"<b>Piled Higher and Deeper Comics</b>\". He is also the co-founder of PHDtv.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image06.png").resize(600,500)
                    .into(holder.image);
        }
      else  if(position ==10)  {
            Spanned tex1 = Html.fromHtml("<b>RAVI  NAWAL</b>, an alumnus of IIM-A, has a keen interest in innovation which led to his first full-fledged book: <b>India Can</b>.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image07.jpg")
                    .into(holder.image);
        }
        else  {
            Spanned tex1 = Html.fromHtml("<b>Dr. Pawan Agarwal</b> runs <b>Mumbai Dabbawala Education</b> centre to give Education to Dabbawala and Deprived Children absolutely FREE. He is an International Motivational Speaker, Author and an Educationist.");
            holder.text.setText(tex1);
            Picasso.with(convertView.getContext())
                    .load("https://2016.techkriti.org/extras/talks/image00.jpg")
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
