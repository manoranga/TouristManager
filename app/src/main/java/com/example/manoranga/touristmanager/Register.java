package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regist =(Button)findViewById(R.id.button);
        EditText userName = (EditText)findViewById(R.id.etusername);
        EditText email = (EditText)findViewById(R.id.etemail);
        EditText country = (EditText)findViewById(R.id.etcountry);
        EditText password = (EditText)findViewById(R.id.etpasswword);
        EditText repassword = (EditText)findViewById(R.id.editText7);


        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tourist_Area.class);
                startActivity(intent);
            }
        });
    }

}
