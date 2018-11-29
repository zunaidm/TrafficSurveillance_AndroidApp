package com.example.orvi.mobileapp.model;

public class Park {
    private String id;
    private String s1;
    private String s2;

    public Park(String id, String s1, String s2){
        setID(id);
        setS1(s1);
        setS2(s2);
    }

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getS1() { return s1; }
    public void setS1(String value) { this.s1 = value; }

    public String getS2() { return s2; }
    public void setS2(String value) { this.s2 = value; }
}