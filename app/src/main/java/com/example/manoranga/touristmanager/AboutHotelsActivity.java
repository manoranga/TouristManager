package com.example.manoranga.touristmanager;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutHotelsActivity extends AppCompatActivity implements View.OnClickListener {


    private Button location;
    private Button comment;
    private Button rate;
    private RatingBar ratingBar;
    private String rateValue;
    private TextView Charge10,Charge20,Charge30;
    private TextView Available10,Available20,Available30;
    private TextView noOfPerson10,noOfPerson20,noOfPerson30;
    String hotelComment;
    RequestQueue requestQueue;
    RequestQueue requestQueue1;
    String hotelCommentUrl = "http://teampro.azurewebsites.net/api/Hotel/AddHotelComment/";
    String hotelRateUrl ="http://teampro.azurewebsites.net/api/Hotel/RateHotel/";
    String URL_DATA  ="http://teampro.azurewebsites.net/api/RoomDetails/";
    String response1;
    String Id;

    private TextView title;
    final Context context = this;

    HotelDetails hotelDetails;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_hotels);


        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        title = (TextView) findViewById(R.id.title);
        comment = (Button) findViewById(R.id.btnComment);
        location = (Button) findViewById(R.id.mapView);
        rate    = (Button)findViewById(R.id.RateHoteSubmit);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar123);
        Charge10  = (TextView)findViewById(R.id.charge1);
        Charge20  = (TextView)findViewById(R.id.charge2);
        Charge30  = (TextView)findViewById(R.id.charge3);
        Available10 = (TextView)findViewById(R.id.avalibility1);
        Available20 = (TextView)findViewById(R.id.avalibility2);
        Available30 = (TextView)findViewById(R.id.avalibility3);
        noOfPerson10 =(TextView)findViewById(R.id.noOfPerson11);
        noOfPerson20 =(TextView)findViewById(R.id.noOfPerson12);
        noOfPerson30 =(TextView)findViewById(R.id.noOfPerson13);
//        String charge1,Charge2,charge3;
//       String available1,available2,available3;
//       String p1,p2,p3;


        Intent intent = getIntent();
        hotelDetails = (HotelDetails) intent.getSerializableExtra("HotelInfo");
        userLocalStore = new UserLocalStore(getApplicationContext());
        title.setText(hotelDetails.getmUserName());
        Picasso.with(this).load(hotelDetails.getmImageUrl()).into(imageView);
        Id = String.valueOf(hotelDetails.getmHotelId());


        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);



        userLocalStore = new UserLocalStore(getApplicationContext());


        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA+Id , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //  JSONObject jsonObject=new JSONObject(response);
                    //  JSONArray array=jsonObject.getJSONArray("UserDetails_Result");

                    JSONArray array=new JSONArray(response);


                        JSONObject object = array.getJSONObject(0);


                        final String charge1      =   String.valueOf(object.getInt("roomCharge"));
                        final String availability1=      String.valueOf(object.getInt("availability"));
                        final String person1      =        String.valueOf( object.getInt("persons"));
                    Charge10.setText(charge1);
                    Available10.setText(availability1);
                    noOfPerson10.setText(person1);

                    JSONObject object1 = array.getJSONObject(1);


                    final String charge2         =  String.valueOf(object1.getInt("roomCharge"));
                    final String availability2   =   String.valueOf(object1.getInt("availability")) ;
                    final String person2         =        String.valueOf(object1.getInt("persons"));
                    Charge20.setText(charge2);
                    Available20.setText(availability2);
                    noOfPerson20.setText(person2);

                    JSONObject object2 = array.getJSONObject(2);


                    final String charge3         =  String.valueOf(object2.getInt("roomCharge"));
                    final String availability3   =   String.valueOf(object2.getInt("availability")) ;
                    final String person3         =        String.valueOf(object2.getInt("persons"));
                    Charge30.setText(charge3);
                    Available30.setText(availability3);
                    noOfPerson30.setText(person3);

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







        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                map.putExtra("HotelInfo", hotelDetails);
                startActivity(map);
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.user_comment, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);


                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.userinput);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                        hotelComment = userInput.getText().toString();
                                        if(hotelComment!=null) {
                                            submitHotelComment();
                                            resetComment();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"Enter Your Comment",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                AlertDialog alertDialog = alertDialogBuilder.create();


                alertDialog.show();


            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                 rateValue = String.valueOf(value);

            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*  if(response1.equals("You have no payment made this hotel")){
                    Toast.makeText(getApplicationContext(),response1, Toast.LENGTH_SHORT).show();
                    rate.setEnabled(false);
              }else if (response1.equals("Rating Success")){
                        submitHotelRate();
                  Toast.makeText(getApplicationContext(),response1, Toast.LENGTH_SHORT).show();
                }else if(response1.equals("Rating Updated")){
                        submitHotelRate();
                  Toast.makeText(getApplicationContext(),response1, Toast.LENGTH_SHORT).show();
              }*/
                // public void submitHotelRate() {




            }


        });



    }
    public void resetComment() {
        hotelComment ="";
    }




    @Override
    public void onClick(View view) {

    }

    public void submitHotelComment() {


        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("userName", userLocalStore.getUserDetails().getName());
        jsonParams.put("hotelComment1", hotelComment);
        jsonParams.put("hotelId", String.valueOf(hotelDetails.getmHotelId()));


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, hotelCommentUrl, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  Toast.makeText(getApplicationContext(), "Comment Submited", Toast.LENGTH_SHORT).show();

                    }
                }


        );
        requestQueue = Volley.newRequestQueue(AboutHotelsActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

    public void Rate() {

        Map<String, String> jsonParams = new HashMap<>();
        jsonParams.put("touristId", String.valueOf(1));
        jsonParams.put("hotelId", String.valueOf(hotelDetails.getmHotelId()));
        jsonParams.put("ratingValue", (rateValue));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,hotelRateUrl, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Rate Submited" + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }


        );
        requestQueue = Volley.newRequestQueue(AboutHotelsActivity.this);
        requestQueue.add(jsonObjectRequest);

    }


    }





