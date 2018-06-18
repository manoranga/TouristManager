package com.example.manoranga.touristmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<MessageDetails> messageDetails;
    private Context context;

    public MessageAdapter(List<MessageDetails> messageDetails, Context context) {
        this.messageDetails = messageDetails;
        this.context = context;
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_card,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        final MessageDetails chatItem = messageDetails.get(position);
        holder.head.setText(chatItem.getMessageHead());
        holder.des.setText(chatItem.getMessageDes());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ChatActivity.class);
                //send the id of clicked user as an extra
                intent.putExtra("head",chatItem.getMessageHead());
                intent.putExtra("description",chatItem.getMessageDes());
                context.startActivity(intent);
                Toast.makeText(context,chatItem.getMessageHead(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return messageDetails.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView head;
        public TextView des;
        public LinearLayout linearLayout;

        public ViewHolder( View itemView) {
            super(itemView);

            head    = (TextView)itemView.findViewById(R.id.messageHead);
            des     = (TextView)itemView.findViewById(R.id.messageDescription);
            linearLayout =(LinearLayout)itemView.findViewById(R.id.message_item);
        }
    }
}
