package com.example.bobby.trafficsubscriber.utility;
public class ticketutility {

    private String title, genre, time,location,issue,id;

    public ticketutility() {
    }

    public ticketutility(String title, String genre, String time, String location, String issue,String id) {
        this.title = title;
        this.genre = genre;
        this.time = time;
        this.location=location;
        this.issue=issue;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
