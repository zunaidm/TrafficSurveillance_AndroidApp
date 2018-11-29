package com.example.orvi.mobileapp.model;

public class trace {
    private String road;
    private String time;
    private String date;

    public trace(String road, String time, String date){
        setDate(date);
        setRoad(road);
        setTime(time);
    }

    public String getRoad() { return road; }
    public void setRoad(String value) { this.road = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }
}