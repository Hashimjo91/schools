package com.applikable.Schools.Classes;

/**
 * Created by Hashim on 08/03/2015.
 */
public class Exams {
    String examSchedualId;
    String classId;
    String sectionId;
    String genderId;
    String examTypeId;
    String examDate;
    String genderName;
    String className;
    String sectionName;
    String examTypeName;
    String tutorial;

    public Exams(String examSchedualId, String classId, String sectionId, String genderId, String examTypeId, String examDate, String genderName, String className, String sectionName, String examTypeName, String tutorial) {

        this.examSchedualId = examSchedualId;
        this.classId = classId;
        this.sectionId = sectionId;
        this.genderId = genderId;
        this.examTypeId = examTypeId;
        this.examDate = examDate;
        this.genderName = genderName;
        this.className = className;
        this.sectionName = sectionName;
        this.examTypeName = examTypeName;
        this.tutorial = tutorial;
    }

    public String getExamSchedualId() {
        return examSchedualId;
    }

    public void setExamSchedualId(String examSchedualId) {
        this.examSchedualId = examSchedualId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
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

    public String getExamTypeName() {
        return examTypeName;
    }

    public void setExamTypeName(String examTypeName) {
        this.examTypeName = examTypeName;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }
}
