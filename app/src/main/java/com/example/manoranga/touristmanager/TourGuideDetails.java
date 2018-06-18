package com.example.manoranga.touristmanager;

import java.io.Serializable;

/**
 * Created by Manoranga on 12/10/2017.
 */

public class TourGuideDetails implements Serializable{

    private  String uname;
    private  String gender;
    private  String description;
    private  String email;
    private  String nationality;
    private  int    chargePerDay;
   // private  int    preference ;
    private  int    status;
    private  int    rank;
    private  int   guideId;
    private  String url;

    public TourGuideDetails(String uname, String gender, String description, String email, String nationality, int chargePerDay /*int preference*/, int status, int rank, int guideId) {
        this.uname = uname;
        this.gender = gender;
        this.description = description;
        this.email = email;
        this.nationality = nationality;
        this.chargePerDay = chargePerDay;
      //  this.preference = preference;
        this.status = status;
        this.rank = rank;
        this.guideId = guideId;
         url = "https://i0.wp.com/www.englishspectrum.com/wp-content/uploads/2016/10/How-to-Become-a-Tour-Guide.jpg";
    }

    public String getEmail() {
        return email;
    }

    public String getNationality() {
        return nationality;
    }

    public int getChargePerDay() {
        return chargePerDay;
    }

  /*  public int getPreference() {
        return preference;
    }*/

    public int getStatus() {
        return status;
    }



    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getUname() {
        return uname;
    }

    public String getGender() {
        return gender;
    }

    public int getRank() {
        return rank;
    }

    public int getGuideId() {
        return guideId;
    }
}
