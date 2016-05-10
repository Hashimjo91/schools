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
        switch (SClass) {

            case 0:
                SClass = 82;
                break;
            case 1:
                SClass = 83;
                break;
            case 2:
                SClass = 84;
                break;
            case 3:
                SClass = 85;
                break;
            case 4:
                SClass = 86;
                break;
            case 5:
                SClass = 87;
                break;
            case 6:
                SClass = 88;
                break;
            case 7:
                SClass = 89;
                break;
            case 8:
                SClass = 90;
                break;
            case 9:
                SClass = 91;
                break;
            case 10:
                SClass = 92;
                break;
            default:
                SClass = 85;
        }
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