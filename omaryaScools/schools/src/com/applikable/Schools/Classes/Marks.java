package com.applikable.Schools.Classes;

/**
 * Created by Hashim on 10/03/2015.
 */
public class Marks {

    private String course;
    private String first;
    private String second;
    private String third;
    private String fourth;
    private String all;
    private String SName;
    private String SClass;
    private String SSection;

    public Marks(String course, String first, String second, String third, String fourth, String all, String SName, String SClass, String SSection) {
        this.course = course;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.all = all;
        this.SName = SName;
        this.SClass = SClass;
        this.SSection = SSection;
    }

    public String getSSection() {
        return SSection;
    }

    public void setSSection(String SSection) {
        this.SSection = SSection;
    }

    public String getSClass() {
        return SClass;
    }

    public void setSClass(String SClass) {
        this.SClass = SClass;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
