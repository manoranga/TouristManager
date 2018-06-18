package com.example.manoranga.touristmanager;

import android.content.Context;
import android.content.SharedPreferences;



public class UserLocalStore {
    public  final static String spName = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(spName,0);
    }

    public void setUserDetails(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("id", user.getId());
        spEditor.putString("email", user.getEmail());
        spEditor.putString("password", user.getPassword());
        spEditor.putString("username",user.getName());
        spEditor.commit();
    }

    public User getUserDetails(){
        int userId = userLocalDatabase.getInt("id",-1);
        String email = userLocalDatabase.getString("email","");
        String password = userLocalDatabase.getString("password","");
        String name = userLocalDatabase.getString("username","");

        User user =new User(userId, email, password,name);
        return  user;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false))
            return true;
        else
            return false;
    }

    public void clearUser(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        
    }
}
