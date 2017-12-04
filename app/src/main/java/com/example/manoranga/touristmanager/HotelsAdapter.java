package com.example.manoranga.touristmanager;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manoranga on 11/30/2017.
 */

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.ViewHolder>{
private List<Thotels_items> thotels_items;

    public HotelsAdapter(List<Thotels_items> thotels_items, Context context) {
        this.thotels_items = thotels_items;
        this.context = context;
    }

    private Context context;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.thotels_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Thotels_items thotels_item = thotels_items.get(position);
        holder.Titie.setText(thotels_item.getTitle());
        holder.shordescription.setText(thotels_item.getShordescription());
        holder.rate.setText((int) thotels_item.getRate());
        holder.price.setText(thotels_item.getPrice());
        Picasso.with(context).load(thotels_item.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return thotels_items.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Titie;
        public TextView shordescription;
        public TextView rate;
        public TextView price;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            Titie           =(TextView)itemView.findViewById(R.id.textViewTitle);
            shordescription = (TextView)itemView.findViewById(R.id.textViewShortDesc);
            rate            =(TextView)itemView.findViewById(R.id.textViewRating);
            price           =(TextView)itemView.findViewById(R.id.textViewPrice);
            img             =(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}