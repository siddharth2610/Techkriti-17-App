package com.suleiman.techkriti.model;

/**
 * Created by GUNDA ABHISHEK on 29-11-2015.
 */
public class    competitions_data {
    public String name;
    public static final String[] data = {"Soccon", "29 States", "Innovator"};
   competitions_data(String name){
        this.name=name;
    }
   public static class International_data{
        public String name;
        public static final String[] data={"IARC","Multirotor","Wild Soccer","IOPC","Techkriti Grand Prix","IRGT"};
        International_data(String name){this.name=name;}
    }
}

