package com.applikable.Schools.Classes;

import org.simpleframework.xml.Element;

/**
 * @author Mohammad Al-Najjar
 * @since 10/03/2015
 */
public class Schedual {

    private static final boolean REQUIRES = false;

    @Element(name = "Session1", required = REQUIRES)
    private String session1;

    @Element(name = "Session2", required = REQUIRES)
    private String session2;

    @Element(name = "Session3", required = REQUIRES)
    private String session3;

    @Element(name = "Session4", required = REQUIRES)
    private String session4;

    @Element(name = "Session5", required = REQUIRES)
    private String session5;

    @Element(name = "Session6", required = REQUIRES)
    private String session6;

    @Element(name = "Session7", required = REQUIRES)
    private String session7;

    @Element(name = "Session8", required = REQUIRES)
    private String session8;

    @Element(name = "Note1", required = REQUIRES)
    private String note1;

    @Element(name = "Note2", required = REQUIRES)
    private String note2;

    @Element(name = "Note3", required = REQUIRES)
    private String note3;

    @Element(name = "Note4", required = REQUIRES)
    private String note4;

    @Element(name = "Note5", required = REQUIRES)
    private String note5;

    @Element(name = "Note6", required = REQUIRES)
    private String note6;

    @Element(name = "Note7", required = REQUIRES)
    private String note7;

    @Element(name = "Note8", required = REQUIRES)
    private String note8;

    public String getSession1() {
        return session1;
    }

    public void setSession1(String session1) {
        this.session1 = session1;
    }

    public String getSession2() {
        return session2;
    }

    public void setSession2(String session2) {
        this.session2 = session2;
    }

    public String getSession3() {
        return session3;
    }

    public void setSession3(String session3) {
        this.session3 = session3;
    }

    public String getSession4() {
        return session4;
    }

    public void setSession4(String session4) {
        this.session4 = session4;
    }

    public String getSession5() {
        return session5;
    }

    public void setSession5(String session5) {
        this.session5 = session5;
    }

    public String getSession6() {
        return session6;
    }

    public void setSession6(String session6) {
        this.session6 = session6;
    }

    public String getSession7() {
        return session7;
    }

    public void setSession7(String session7) {
        this.session7 = session7;
    }

    public String getSession8() {
        return session8;
    }

    public void setSession8(String session8) {
        this.session8 = session8;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getNote4() {
        return note4;
    }

    public void setNote4(String note4) {
        this.note4 = note4;
    }

    public String getNote5() {
        return note5;
    }

    public void setNote5(String note5) {
        this.note5 = note5;
    }

    public String getNote6() {
        return note6;
    }

    public void setNote6(String note6) {
        this.note6 = note6;
    }

    public String getNote7() {
        return note7;
    }

    public void setNote7(String note7) {
        this.note7 = note7;
    }

    public String getNote8() {
        return note8;
    }

    public void setNote8(String note8) {
        this.note8 = note8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedual schedual = (Schedual) o;

        if (note1 != null ? !note1.equals(schedual.note1) : schedual.note1 != null) return false;
        if (note2 != null ? !note2.equals(schedual.note2) : schedual.note2 != null) return false;
        if (note3 != null ? !note3.equals(schedual.note3) : schedual.note3 != null) return false;
        if (note4 != null ? !note4.equals(schedual.note4) : schedual.note4 != null) return false;
        if (note5 != null ? !note5.equals(schedual.note5) : schedual.note5 != null) return false;
        if (note6 != null ? !note6.equals(schedual.note6) : schedual.note6 != null) return false;
        if (note7 != null ? !note7.equals(schedual.note7) : schedual.note7 != null) return false;
        if (note8 != null ? !note8.equals(schedual.note8) : schedual.note8 != null) return false;
        if (session1 != null ? !session1.equals(schedual.session1) : schedual.session1 != null) return false;
        if (session2 != null ? !session2.equals(schedual.session2) : schedual.session2 != null) return false;
        if (session3 != null ? !session3.equals(schedual.session3) : schedual.session3 != null) return false;
        if (session4 != null ? !session4.equals(schedual.session4) : schedual.session4 != null) return false;
        if (session5 != null ? !session5.equals(schedual.session5) : schedual.session5 != null) return false;
        if (session6 != null ? !session6.equals(schedual.session6) : schedual.session6 != null) return false;
        if (session7 != null ? !session7.equals(schedual.session7) : schedual.session7 != null) return false;
        if (session8 != null ? !session8.equals(schedual.session8) : schedual.session8 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = session1 != null ? session1.hashCode() : 0;
        result = 31 * result + (session2 != null ? session2.hashCode() : 0);
        result = 31 * result + (session3 != null ? session3.hashCode() : 0);
        result = 31 * result + (session4 != null ? session4.hashCode() : 0);
        result = 31 * result + (session5 != null ? session5.hashCode() : 0);
        result = 31 * result + (session6 != null ? session6.hashCode() : 0);
        result = 31 * result + (session7 != null ? session7.hashCode() : 0);
        result = 31 * result + (session8 != null ? session8.hashCode() : 0);
        result = 31 * result + (note1 != null ? note1.hashCode() : 0);
        result = 31 * result + (note2 != null ? note2.hashCode() : 0);
        result = 31 * result + (note3 != null ? note3.hashCode() : 0);
        result = 31 * result + (note4 != null ? note4.hashCode() : 0);
        result = 31 * result + (note5 != null ? note5.hashCode() : 0);
        result = 31 * result + (note6 != null ? note6.hashCode() : 0);
        result = 31 * result + (note7 != null ? note7.hashCode() : 0);
        result = 31 * result + (note8 != null ? note8.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Schedual{" +
                "session1='" + session1 + '\'' +
                ", session2='" + session2 + '\'' +
                ", session3='" + session3 + '\'' +
                ", session4='" + session4 + '\'' +
                ", session5='" + session5 + '\'' +
                ", session6='" + session6 + '\'' +
                ", session7='" + session7 + '\'' +
                ", session8='" + session8 + '\'' +
                ", note1='" + note1 + '\'' +
                ", note2='" + note2 + '\'' +
                ", note3='" + note3 + '\'' +
                ", note4='" + note4 + '\'' +
                ", note5='" + note5 + '\'' +
                ", note6='" + note6 + '\'' +
                ", note7='" + note7 + '\'' +
                ", note8='" + note8 + '\'' +
                '}';
    }
}
