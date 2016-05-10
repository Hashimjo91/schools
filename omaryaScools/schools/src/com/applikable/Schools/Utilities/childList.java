package com.applikable.Schools.Utilities;

/**
 * Created by Alhusban on 26/11/2015.
 */
public class childList {
    private String Name;
    private int SClass;

    public childList(String name, int SClass) {
        this.Name = name;
        this.SClass = SClass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public int getSClass() {
        return SClass;
    }

    public void setSClass(int SClass) {
        this.SClass = SClass;
    }

    @Override
    public String toString() {
        return "{" +
                "\"STDName\":\"" + Name + "\", \"ClassId\":" + SClass +
                '}';
    }
}