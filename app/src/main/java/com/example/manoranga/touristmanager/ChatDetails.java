package com.example.manoranga.touristmanager;



public class ChatDetails {
    private String senderName;
    private String message;
    private boolean seen;
    private long time;
    private String from;

    public ChatDetails(){}

    public String getFrom() {
        return from;
    }

    public ChatDetails(String senderName, String message, boolean seen, long time, String from) {
        this.senderName = senderName;
        this.message = message;
        this.seen = seen;
        this.time = time;
        this.from = from;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSeen() {
        return seen;
    }

    public long getTime() {
        return time;
    }




}
