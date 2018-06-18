package com.example.manoranga.touristmanager;

import java.io.Serializable;



public class User implements Serializable{
    public int id;
    public String email;
    public String password;
    public String name;
    public int phoneNum;



    public User(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public User(int uId, String email, int phoneNum, String uname) {
         this.id = uId;
         this.email = email;
         this.phoneNum = phoneNum;
         this.name =uname;

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
