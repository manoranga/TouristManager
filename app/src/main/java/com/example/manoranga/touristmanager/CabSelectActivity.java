package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CabSelectActivity extends AppCompatActivity {

    Button comment;
    Button map1;
    Button chat;
    User mUser;
    UserLocalStore mUserLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_select);

        comment = (Button) findViewById(R.id.btnComment);
        map1 = (Button) findViewById(R.id.btnCabMap);


        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CabCommentActivity.class);
                startActivity(intent);
            }
        });
      /*  chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Chat_AreaActivity.class);
                startActivity(intent1);

            }

        });*/

    }



    }


