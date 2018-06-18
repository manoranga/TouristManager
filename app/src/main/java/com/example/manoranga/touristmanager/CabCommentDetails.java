package com.example.manoranga.touristmanager;

/**
 * Created by Manoranga on 1/28/2018.
 */

public class CabCommentDetails {
    private String comment;
    private String Name;
    private int id;

    public int getId() {
        return id;
    }

    public CabCommentDetails(int id) {

        this.id = id;
    }

    public CabCommentDetails(String comment, String name) {
        this.comment = comment;
        Name = name;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return Name;
    }
}
