package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manoranga.touristmanager.ChatAdapter;
import com.example.manoranga.touristmanager.ChatDetails;
import com.example.manoranga.touristmanager.R;
import com.example.manoranga.touristmanager.UserLocalStore;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private String mReceiverName;
    private String mSenderName ;
    private TextView mReceiver;
    private ImageButton mImageBtnSend;
    private EditText mEtMessage;
    DatabaseReference mDatabaseReference;
    UserLocalStore userLocalStore;
    User user;

    private RecyclerView mRecyclerView;
    private List<ChatDetails> mChatdetails;
    private RecyclerView.Adapter mChatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userLocalStore = new UserLocalStore(getApplicationContext());

        mReceiver = (TextView) findViewById(R.id.chatUserName);
        Intent intent = getIntent();
        mReceiverName = intent.getExtras().getString("head");
        mReceiver.setText(mReceiverName);
        mSenderName = userLocalStore.getUserDetails().getName();


        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mEtMessage = (EditText) findViewById(R.id.etMessage);
        mImageBtnSend = (ImageButton) findViewById(R.id.imageButtonSend);
        mImageBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   sendMessage();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewMessages);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChatdetails = new ArrayList<>();
        mChatAdapter = new ChatAdapter(mChatdetails, this);
        mRecyclerView.setAdapter(mChatAdapter);



      loadMessages();

    }

    public void sendMessage() {
        mImageBtnSend.setClickable(false);
        mImageBtnSend.setBackgroundColor(Color.parseColor("#7f060026"));
        String message = mEtMessage.getText().toString();
       // Intent intent = getIntent();
       // String mReceiverName = intent.getExtras().getString("head");

        if (!TextUtils.isEmpty(message)) {
            String senderRef = "messages/" + mSenderName + "/" + mReceiverName;
            String receiverRef = "messages/" + mReceiverName + "/" + mSenderName;

            String pushId = mDatabaseReference.child("messages").child(mSenderName).child(mReceiverName)
                    .push().getKey();
/*
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("copyrhgffight");

            mRef.setValue("6 androidhive. All rights Reserved");
*/
            Map messageMap = new HashMap();
            messageMap.put("senderName", mSenderName);
            messageMap.put("message", message);
            messageMap.put("seen", false);
            messageMap.put("time", ServerValue.TIMESTAMP);
            messageMap.put("from", mReceiverName);

            Map messageUserMap = new HashMap();
            messageUserMap.put(senderRef + "/" + pushId, messageMap);
            messageUserMap.put(receiverRef + "/" + pushId, messageMap);

            mDatabaseReference.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    Toast.makeText(getApplicationContext(),"message sent", Toast.LENGTH_LONG).show();

                    if (databaseError != null) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        mEtMessage.setText("");
                    }
                    mImageBtnSend.setClickable(true);
                    mImageBtnSend.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            });


        }
    }

    private void loadMessages() {
       // Intent intent = getIntent();
       // String mReceiverName = intent.getExtras().getString("head");
                  mDatabaseReference.child("messages").child(mSenderName).child(mReceiverName)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        ChatDetails message = dataSnapshot.getValue(ChatDetails.class);
                        mChatdetails.add(message);
                        mChatAdapter.notifyDataSetChanged();
                        mRecyclerView.scrollToPosition(mChatAdapter.getItemCount() - 1);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
