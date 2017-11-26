package com.example.manoranga.touristmanager;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class Register extends AppCompatActivity {
    RequestQueue requestQueue;
    String url ="";
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
        userName = (EditText)findViewById(R.id.etusername);
        email = (EditText)findViewById(R.id.etemail);
        country = (EditText)findViewById(R.id.etcountry);
        password = (EditText)findViewById(R.id.etpasswword);
        repassword = (EditText)findViewById(R.id.editText7);

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


        }else if (TextUtils.isEmpty(p2)){
            repassword.setError("field required");
            repassword.requestFocus();
            return false;
        }else if(!p1.equals(p2)){
            repassword.setError("re enter right password");
            repassword.requestFocus();
            Toast.makeText(this,"PASSWORDS ARE NOT MACHING",Toast.LENGTH_LONG).show();
            return false;
        }
            return true;
    }

    private boolean isEmailValid(){
        String emailadd = email.getText().toString();
     if(TextUtils.isEmpty(emailadd)){
         email.setError("Enter Email");
         email.requestFocus();
         return false;
     }   else if(!Patterns.EMAIL_ADDRESS.matcher(emailadd).matches()){
         email.setError("Enter Valid Email");
         email.requestFocus();
         return false;

     }return true;
    }

    private boolean isOtherFiledValid(){

        String name = userName.getText().toString();
        String con  = country.getText().toString();

        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(con)){
            userName.setError("Faild Required");
            userName.requestFocus();
            country.setError("Faild Required");
            country.requestFocus();
            return false;
        }return true;
    }
    private void validation(){
        boolean okIsOther = isOtherFiledValid();
        if(!okIsOther)
        return;

        boolean okIsEmail = isEmailValid();
        if(!okIsEmail)
        return;

        boolean okIsPassword = isPasswordValid();
        if(!okIsPassword)
            return;

        boolean allOk = okIsEmail && okIsOther && okIsPassword;

        if(allOk){
            registation();
        }
    }
    public void registation(){
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("username",userName.getText().toString());
        jsonParams.put("email",email.getText().toString());
        jsonParams.put("password",password.getText().toString());
        jsonParams.put("country",country.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String msg = response.getString("msg");
                    if(msg.contains("successfull")){
                        Toast.makeText(getApplicationContext(),"Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(Register.this,Role.class);
                        startActivity(in);
                    }else Toast.makeText(getApplicationContext(),"Registation Error", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Connection Fail", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}

