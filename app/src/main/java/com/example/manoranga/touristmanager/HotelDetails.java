package com.example.manoranga.touristmanager;

import java.io.Serializable;

/**
 * Created by Manoranga on 12/5/2017.
 */

public class HotelDetails implements Serializable {
   private int    mHotelId;
   private int    mRank;
   private String mUserName;
   private String mDescription;
   private String mContactNum;
   private String mImageUrl;
   private double latitude;
   private double longtitude;
   private String email;

    public HotelDetails(int mHotelId, int mRank, String mUserName, String mDescription, String mContactNum, double latitude, double longtitude, String email) {
        this.mHotelId = mHotelId;
        this.mRank = mRank;
        this.mUserName = mUserName;
        this.mDescription = mDescription;
        this.mContactNum = mContactNum;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.email = email;
        mImageUrl = "http://localupdates.in/wp-content/uploads/2017/06/ce126a175458bbcd06c1bdad6e88adef.png";
    }

    public int getmHotelId() {
        return mHotelId;
    }

    public int getmRank() {
        return mRank;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmContactNum() {
        return mContactNum;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public String getEmail() {
        return email;
    }
}
