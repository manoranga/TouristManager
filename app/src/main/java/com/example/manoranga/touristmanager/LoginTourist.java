package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginTourist extends AppCompatActivity {
    String url = "http://teampro.azurewebsites.net/api/Tourist/Loging";
    EditText userName;
    EditText password;
    Button btnRegister;
    Button btnLogin;
    RequestQueue requestQueue;
    UserLocalStore muserLocalStore;
    User user;


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
                if (isOtherFiledValid() == true) {
                    login();
                }


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TTouristAArea.class);
                startActivity(intent);
            }
        });

        muserLocalStore = new UserLocalStore(getApplicationContext());
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


        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("userName", userName.getText().toString());
        jsonParams.put("password", password.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                        try {
                            int uId = response.getInt("touristId");
                            String email = response.getString("email");
                            String password = response.getString("password");
                            String uname = response.getString("userName");
                            user = new User(uId, email, password, uname);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }


                        muserLocalStore = new UserLocalStore(getApplicationContext());
                        muserLocalStore.setUserDetails(user);
                        muserLocalStore.setUserLoggedIn(true);


                        Intent in = new Intent(getApplicationContext(), TTouristAArea.class);
                        startActivity(in);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                }


        );
        requestQueue = Volley.newRequestQueue(LoginTourist.this);
        requestQueue.add(jsonObjectRequest);



    }

}