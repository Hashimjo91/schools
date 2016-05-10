package com.applikable.Schools.Classes;

/**
 * Created by Hashim on 07/03/2015.
 */
public class SchedDetails {
    String className;
    String sectionName;
    String dateFrom;
    String dateTo;
    String genderName;
    String genderId;
    String GenNote;
    String StudyPlan;

    public SchedDetails(String className, String sectionName, String dateFrom, String dateTo, String genderName, String genderId, String string, String cString) {

        this.className = className;
        this.sectionName = sectionName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.genderName = genderName;
        this.genderId = genderId;
        this.GenNote = string;
        this.StudyPlan = cString;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getGenNote() {
        return GenNote;
    }

    public void setGenNote(String genNote) {
        GenNote = genNote;
    }

    public String getStudyPlan() {
        return StudyPlan;
    }

    public void setStudyPlan(String studyPlan) {
        StudyPlan = studyPlan;
    }
}
