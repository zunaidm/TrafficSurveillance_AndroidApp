package com.example.orvi.mobileapp.model;

public class transaction {
    private String date;
    private String time;
    private String amount;
    private boolean fine;
    private boolean toll;

    public transaction(String date,String time,String amount,boolean fine,boolean toll){
        setDate(date);
        setTime(time);
        setAmount(amount);
        setFine(fine);
        setToll(toll);
    }
    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public String getAmount() { return amount; }
    public void setAmount(String value) { this.amount = value; }

    public boolean getFine() { return fine; }
    public void setFine(boolean value) { this.fine = value; }

    public boolean getToll() { return toll; }
    public void setToll(boolean value) { this.toll = value; }
}
