package com.example.orvi.mobileapp.model;

public class Fine {
    private String date;
    private String time;
    private String amount;
    private String reason;

    public Fine (String date,String time,String amount,String reason){
        setAmount(amount);
        setDate(date);
        setReason(reason);
        setTime(time);
    }

    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public String getAmount() { return amount; }
    public void setAmount(String value) { this.amount = value; }

    public String getReason() { return reason; }
    public void setReason(String value) { this.reason = value; }
}