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

/**
 * Created by Manoranga on 12/5/2017.
 */

public class CabAdapter extends RecyclerView.Adapter<CabAdapter.ViewHolder> {

    private List<CabDetails> cabDetails;
    private Context context;

    public CabAdapter(List<CabDetails> cabDetails, Context context) {
        this.cabDetails = cabDetails;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclecard,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position ) {
        final CabDetails cabInfo = cabDetails.get(position);
        holder.owner.setText(cabInfo.getUserName());
        holder.des.setText(cabInfo.getDescription());
        holder.rate.setText(cabInfo.getEmail());
        holder.price.setText(cabInfo.getContactNum());
        Picasso.with(context).load(cabInfo.getUrl_Cab()).into(holder.img);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AboutCabsActivity.class);
                intent.putExtra("CabInfo",cabInfo ); //second param is Serializable
                context.startActivity(intent);

            }
        });
            }




    @Override
    public int getItemCount() {
        return cabDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView owner;
        public TextView des;
        public TextView rate;
        public TextView price;
        public ImageView img;
        public  ConstraintLayout constraintLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            owner    = (TextView)itemView.findViewById(R.id.head);
            des     = (TextView)itemView.findViewById(R.id.description);
            rate    = (TextView)itemView.findViewById(R.id.email);
            price   = (TextView)itemView.findViewById(R.id.tpNo);
            img     = (ImageView) itemView.findViewById(R.id.profile_image);
            constraintLayout    =(ConstraintLayout)itemView.findViewById(R.id.layout1);


        }

    }
}
