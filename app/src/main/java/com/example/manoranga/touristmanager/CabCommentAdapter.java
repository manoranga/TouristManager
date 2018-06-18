package com.example.manoranga.touristmanager;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Manoranga on 1/28/2018.
 */

public class CabCommentAdapter extends RecyclerView.Adapter<CabCommentAdapter.ViewHolder>{
    private List<CabCommentDetails> commentDetails;
    private Context context;

    public CabCommentAdapter(List<CabCommentDetails> commentDetails, Context context) {
        this.commentDetails = commentDetails;
        this.context = context;
    }

    @Override
    public CabCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cab_comment_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CabCommentDetails data = commentDetails.get(position);
        holder.uName.setText(data.getComment());
        holder.counntry.setText(data.getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("description",data.getName());
                intent.putExtra("head",data.getComment());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return commentDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView uName;
        TextView counntry;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            uName = (TextView)itemView.findViewById(R.id.commentHead);
            counntry   = (TextView)itemView.findViewById(R.id.commentDescription);
            relativeLayout =(RelativeLayout)itemView.findViewById(R.id.relative);

        }
    }
}
