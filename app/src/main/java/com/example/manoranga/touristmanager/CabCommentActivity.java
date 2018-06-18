package com.example.manoranga.touristmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CabCommentActivity extends AppCompatActivity {


    private static final String url_data="http://teampro.azurewebsites.net/api/Tourist";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CabCommentDetails> list;
    UserLocalStore userLocalStore;
    Button logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_comment);

        logOut = (Button)findViewById(R.id.cablogout);
        recyclerView=(RecyclerView)findViewById(R.id.recycleComment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadRecycleViewData();

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLocalStore = new UserLocalStore(getApplicationContext());
                userLocalStore.clearUser();
                userLocalStore.setUserLoggedIn(false);
                Intent LogOut = new Intent(getApplicationContext(),SelectRoleActivity.class);
                startActivity(LogOut);
                finish();
            }
        });


    }

    private void loadRecycleViewData() {
        userLocalStore = new UserLocalStore(getApplicationContext());


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //  JSONObject jsonObject=new JSONObject(response);
                    //  JSONArray array=jsonObject.getJSONArray("UserDetails_Result");

                    JSONArray array=new JSONArray(response);

                    for (int i=0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);

                            CabCommentDetails item = new CabCommentDetails(
                                    object.getString("userName"),
                                    object.getString("nationality")

                            );
                            list.add(item);

                    }
                    adapter=new CabCommentAdapter(list,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}






