package com.suleiman.techkriti.model;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class VersionModel {
    public String name;

    public static final String[] data = {"Competitions","Tech Talks","Shows","Exhibitions", "Gallery", "Contacts",
            "Maps"};

    VersionModel(String name){
        this.name=name;
    }
}

