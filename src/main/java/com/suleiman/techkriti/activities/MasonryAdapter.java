package com.suleiman.techkriti.activities;


        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import com.squareup.picasso.Picasso;
        import com.suleiman.techkriti.R;

/**
 * Created by Suleiman on 26-07-2015.
 */
public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {

    private Context context;

    String[] nameList = {"One", "Two", "Three", "Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","","","","","","","",""};
    public MasonryAdapter(Context context) {
        this.context = context;
    }
String imgList[]={"https://2016.techkriti.org/A/images/gallery/1.jpg","https://2016.techkriti.org/A/images/gallery/2.jpg","https://2016.techkriti.org/A/images/gallery/3.jpg","https://2016.techkriti.org/A/images/gallery/4.jpg","https://2016.techkriti.org/A/images/gallery/5.jpg","https://2016.techkriti.org/A/images/gallery/6.jpg","https://2016.techkriti.org/A/images/gallery/7.jpg","https://2016.techkriti.org/A/images/gallery/8.jpg","https://2016.techkriti.org/A/images/gallery/9.jpg","https://2016.techkriti.org/A/images/gallery/10.jpg","https://2016.techkriti.org/A/images/gallery/11.jpg","https://2016.techkriti.org/A/images/gallery/12.jpg","https://2016.techkriti.org/A/images/gallery/13.jpg","https://2016.techkriti.org/A/images/gallery/14.jpg","https://2016.techkriti.org/A/images/gallery/15.jpg","https://2016.techkriti.org/A/images/gallery/16.jpg","https://2016.techkriti.org/A/images/gallery/17.jpg","https://2016.techkriti.org/A/images/gallery/18.jpg","https://2016.techkriti.org/A/images/gallery/19.jpg","https://2016.techkriti.org/A/images/gallery/20.jpg"};
    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item1, parent, false);

        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),FullImageActivity.class);
              int[] screenLocation = new int[2];
                int position=(Integer)v.getTag();

               v.getLocationOnScreen(screenLocation);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", v.getWidth()).
                        putExtra("height", v.getHeight()).
                        putExtra("id", position);
                v.getContext().startActivity(intent);


            }
        });
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        Picasso.with(context)
                .load(imgList[position]).resize(400,300)
                .into(holder.imageView);

        final int pos=position;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),FullImageActivity.class);
                int[] screenLocation = new int[2];


                v.getLocationOnScreen(screenLocation);
                intent.putExtra("left", screenLocation[0]).
                        putExtra("top", screenLocation[1]).
                        putExtra("width", v.getWidth()).
                        putExtra("height", v.getHeight()).
                        putExtra("id", pos).putExtra("Arr",imgList);
                v.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return imgList.length;
    }

    class MasonryView extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MasonryView(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
          //  textView = (TextView) itemView.findViewById(R.id.img_name);

        }
    }
}
