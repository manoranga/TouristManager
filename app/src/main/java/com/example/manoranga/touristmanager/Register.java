package com.example.manoranga.touristmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class Register extends AppCompatActivity {

    RequestQueue requestQueue;
    String url = "http://10.0.2.2:15794/api/user/";
    EditText userName;
    EditText email;
    EditText country;
    EditText password;
    EditText repassword;
    Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText) findViewById(R.id.etusername);
        email = (EditText) findViewById(R.id.etemail);
        country = (EditText) findViewById(R.id.etcountry);
        password = (EditText) findViewById(R.id.etpasswword);
        repassword = (EditText) findViewById(R.id.editText7);
        regist =(Button)findViewById(R.id.button);
        setClearErrorsListeners();
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    private boolean isPasswordValid() {
        String p1 = password.getText().toString();
        String p2 = repassword.getText().toString();

        if (TextUtils.isEmpty(p1)) {
            password.setError("field required");
            password.requestFocus();
            return false;


        } else if (TextUtils.isEmpty(p2)) {
            repassword.setError("field required");
            repassword.requestFocus();
            return false;
        } else if (!p1.equals(p2)) {
            repassword.setError("re enter right password");
            repassword.requestFocus();
            Toast.makeText(this, "PASSWORDS ARE NOT MACHING", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isEmailValid() {
        String emailadd = email.getText().toString();
        if (TextUtils.isEmpty(emailadd)) {
            email.setError("Enter Email");
            email.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailadd).matches()) {
            email.setError("Enter Valid Email");
            email.requestFocus();
            return false;

        }
        return true;
    }

    private boolean isOtherFiledValid() {

        String name = userName.getText().toString();
        String con = country.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(con)) {
            userName.setError("Faild Required");
            userName.requestFocus();
            country.setError("Faild Required");
            country.requestFocus();
            return false;
        }
        return true;
    }

    private void validation() {
        boolean okIsOther = isOtherFiledValid();
        if (!okIsOther)
            return;

        boolean okIsEmail = isEmailValid();
        if (!okIsEmail)
            return;

        boolean okIsPassword = isPasswordValid();
        if (!okIsPassword)
            return;

        boolean allOk = okIsEmail && okIsOther && okIsPassword;

        if (allOk) {
            registation();
        }
    }

    public void registation() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("successful")){
                    Toast.makeText(getApplicationContext(),"Registation Successs",Toast.LENGTH_LONG).show();
                    Intent in = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(in);
                } Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            protected  Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> jsonParams = new HashMap<String, String>();

                jsonParams.put("username", userName.getText().toString());
                jsonParams.put("email", email.getText().toString());
                jsonParams.put("password", password.getText().toString());
                jsonParams.put("country", country.getText().toString());
                return  jsonParams;
            };




        };
        requestQueue = Volley.newRequestQueue(Register.this);
        requestQueue.add(stringRequest);
    }

    private void setClearErrorsListeners() {
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName.setError(null);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.setError(null);
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password.setError(null);
            }
        });

        repassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repassword.setError(null);
            }
        });

        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country.setError(null);
            }
        });

    }
}

