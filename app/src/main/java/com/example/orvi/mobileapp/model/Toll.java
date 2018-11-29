package com.example.orvi.mobileapp.model;

public class Toll {
    private String date;
    private String time;
    private String amount;

    public Toll (String date,String time,String amount){
        setAmount(amount);
        setDate(date);
        setTime(time);
    }

    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public String getAmount() { return amount; }
    public void setAmount(String value) { this.amount = value; }
}
