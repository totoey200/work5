package com.example.lg.work5;

/**
 * Created by LG on 2017-03-30.
 */

public class Data {
    private String name, time, mem;
    private int spa, piz, price;
    public Data(String name, String time, int spa, int piz, String mem, int price){
        this.name = name;
        this.time = time;
        this.spa = spa;
        this.piz = piz;
        this.mem = mem;
        this.price = price;
    }
    String getName(){
        return this.name;
    }
    String getTime(){
        return this.time;
    }
    String getMem(){
        return this.mem;
    }
    int getSpa(){
        return this.spa;
    }
    int getPiz(){
        return this.piz;
    }
    int getPrice(){
        return this.price;
    }
}
