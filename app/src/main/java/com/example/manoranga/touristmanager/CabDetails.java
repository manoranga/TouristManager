package com.example.manoranga.touristmanager;

import java.io.Serializable;

/**
 * Created by Manoranga on 12/9/2017.
 */

public class CabDetails implements Serializable{
   private  int cabId;
  // private int preference;
 //  private int status;
   private String userName;
   private String description;
 //  private String currentLocation;
   private  String email;
   private  String contactNum;
   private  String url_Cab;

    public CabDetails(int cabId,/* int preference, int status,*/ String userName, String description, /*String currentLocation,*/ String email, String contactNum) {
        this.cabId = cabId;
      //  this.preference = preference;
      //  this.status = status;
        this.userName = userName;
        this.description = description;
       // this.currentLocation = currentLocation;
        this.email = email;
        this.contactNum = contactNum;
        url_Cab  ="http://yellowcabutah.com/assets/images/cabmap5.png";
    }

    public int getCabId() {
        return cabId;
    }

  /*  public int getPreference() {
        return preference;
    }*/

  /*  public int getStatus() {
        return status;
    }*/

    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return description;
    }



  /*  public String getCurrentLocation() {
        return currentLocation;
    }*/

    public String getEmail() {
        return email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getUrl_Cab() {
        return url_Cab;
    }
}
