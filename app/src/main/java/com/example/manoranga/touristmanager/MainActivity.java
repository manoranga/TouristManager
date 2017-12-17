package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    String url = "http://10.0.2.2:15794/api/user/";
    EditText userName;
    EditText password;
    Button btnRegister;
    Button btnLogin;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = (Button) findViewById(R.id.button5);
        btnLogin = (Button) findViewById(R.id.button3);
        userName = (EditText) findViewById(R.id.editText3);
        password = (EditText) findViewById(R.id.editText4);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOtherFiledValid()==true){
                    login();
                }


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Role.class);
                startActivity(intent);
            }
        });
    }

    private boolean isOtherFiledValid() {

        String name = userName.getText().toString();
        String pw = password.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pw)) {
            userName.setError("Faild Required");
            userName.requestFocus();
            password.setError("Faild Required");
            password.requestFocus();
            return false;
        }

        return true;


    }


    public void login() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("loging success")) {
                    //Toast.makeText(getApplicationContext(), "Registation Successs", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(getApplicationContext(), Tourist_Area.class);
                    startActivity(in);
                }
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "eeeeeeeeeeee", Toast.LENGTH_LONG).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> login = new HashMap<String, String>();

                login.put("username", userName.getText().toString());
                login.put("password", password.getText().toString());
                return login;

            }




        };
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}