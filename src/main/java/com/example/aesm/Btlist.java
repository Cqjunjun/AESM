package com.example.aesm;

public class Btlist {
    private  String name;
    private  String address;
    public Btlist(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
    public  String getBtName(){
        return name;
    }
    public  String getBtAddress(){
        return address;
    }
}
