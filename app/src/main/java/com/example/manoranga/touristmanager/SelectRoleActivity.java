package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectRoleActivity extends AppCompatActivity {
    Button tourist;
    Button cabservice;
    UserLocalStore mUserLocalStore;
    User muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role2);
        mUserLocalStore = new UserLocalStore(getApplicationContext());

        tourist = (Button) findViewById(R.id.button6);
        cabservice = (Button) findViewById(R.id.button8);

        tourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginTourist.class);
                startActivity(intent);
                finish();
            }
        });

        cabservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginGuide.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mUserLocalStore.getUserLoggedIn() == true) {
            muser = mUserLocalStore.getUserDetails();
            Intent intent = new Intent(getApplicationContext(), TTouristAArea.class);
            startActivity(intent);
            finish();
        }

    }
}
