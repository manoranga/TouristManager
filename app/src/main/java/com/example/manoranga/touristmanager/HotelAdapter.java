package com.example.manoranga.touristmanager;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<HotelDetails> hotelDetails;
    private Context context;


    public HotelAdapter(List<HotelDetails> hotelDetails, Context context) {
        this.hotelDetails = hotelDetails;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclecard,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position ) {
        final HotelDetails hotelInfo = hotelDetails.get(position);
        holder.head.setText(hotelInfo.getmUserName());
        holder.des.setText(hotelInfo.getmDescription());
        holder.rate.setText(String.valueOf(hotelInfo.getEmail()));
        holder.price.setText(hotelInfo.getmContactNum());
        Picasso.with(context).load(hotelInfo.getmImageUrl()).into(holder.img);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AboutHotelsActivity.class);
                intent.putExtra("HotelInfo",hotelInfo ); //second param is Serializable
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return hotelDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView head;
        public TextView des;
        public TextView rate;
        public TextView price;
        public ImageView img;
        public  ConstraintLayout constraintLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            head    = (TextView)itemView.findViewById(R.id.head);
            des     = (TextView)itemView.findViewById(R.id.description);
            rate    = (TextView)itemView.findViewById(R.id.email);
            price   = (TextView)itemView.findViewById(R.id.tpNo);
            img     = (ImageView) itemView.findViewById(R.id.profile_image);
            constraintLayout    =(ConstraintLayout)itemView.findViewById(R.id.layout1);


        }

    }
}
