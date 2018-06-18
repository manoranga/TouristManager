package com.example.manoranga.touristmanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class Chat_TouristFragment extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<MessageDetails> msgDetails;
    String URL_DATA = "http://teampro.azurewebsites.net/api/Tourist";
    UserLocalStore userLocalStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_chat__cab_service, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycleChatCab);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        msgDetails = new ArrayList<>();
        loadDeatils();
        return rootView;
    }
    public void loadDeatils(){
        userLocalStore = new UserLocalStore(getContext());

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading............");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    //JSONObject jsonObject = new JSONObject(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray();
                    JSONArray ja = new JSONArray(response);
                    //Toast.makeText(getApplicationContext(), ja.toString(), Toast.LENGTH_LONG).show();


                    for (int i=0;i<ja.length();i++) {

                        JSONObject object = ja.getJSONObject(i);
                        if (!userLocalStore.getUserDetails().getName().equals(object.getString("userName"))) {
                            MessageDetails items = new MessageDetails(
                                    // object.getString("country"),
                                    object.getString("userName"),
                                    object.getString("email")
                            );
                            msgDetails.add(items);

                        }
                    }
                    adapter = new MessageAdapter(msgDetails,getContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
