package com.applikable.Schools.Classes;

import org.simpleframework.xml.Element;

/**
 * @author Mohammad Al-Najjar
 * @since 10/03/2015
 */
@org.simpleframework.xml.Root(name = "SchedualCourseObj")
public class SchedualCourse {

    private static final boolean REQUIRES = false;

    @Element(name = "SchedualCourseId", required = REQUIRES)
    private String schedualCourseId;

    @Element(name = "ClassId", required = REQUIRES)
    private String classId;

    @Element(name = "SectionId", required = REQUIRES)
    private String sectionId;

    @Element(name = "GenderId", required = REQUIRES)
    private String genderId;

    @Element(name = "DateFrom", required = REQUIRES)
    private String dateFrom;

    @Element(name = "DateTo", required = REQUIRES)
    private String dateTo;

    @Element(name = "GenderName", required = REQUIRES)
    private String genderName;

    @Element(name = "ClassName", required = REQUIRES)
    private String className;

    @Element(name = "SectionName", required = REQUIRES)
    private String sectionName;

    @Element(name = "GeneralNote", required = REQUIRES)
    private String generalNote;

    @Element(name = "StudyPlan", required = REQUIRES)
    private String studyPlan;

    @Element(name = "SundaySchedual", required = REQUIRES)
    private Schedual sundaySchedual;

    @Element(name = "MondaySchedual", required = REQUIRES)
    private Schedual mondaySchedual;

    @Element(name = "TuesdaySchedual", required = REQUIRES)
    private Schedual tuesdaySchedual;

    @Element(name = "WednesdaySchedual", required = REQUIRES)
    private Schedual wednesdaySchedual;

    @Element(name = "ThursdaySchedual", required = REQUIRES)
    private Schedual thursdaySchedual;

    public String getSchedualCourseId() {
        return schedualCourseId;
    }

    public void setSchedualCourseId(String schedualCourseId) {
        this.schedualCourseId = schedualCourseId;
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

    public String getGeneralNote() {
        return generalNote;
    }

    public void setGeneralNote(String generalNote) {
        this.generalNote = generalNote;
    }

    public String getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(String studyPlan) {
        this.studyPlan = studyPlan;
    }

    public Schedual getSundaySchedual() {
        return sundaySchedual;
    }

    public void setSundaySchedual(Schedual sundaySchedual) {
        this.sundaySchedual = sundaySchedual;
    }

    public Schedual getMondaySchedual() {
        return mondaySchedual;
    }

    public void setMondaySchedual(Schedual mondaySchedual) {
        this.mondaySchedual = mondaySchedual;
    }

    public Schedual getTuesdaySchedual() {
        return tuesdaySchedual;
    }

    public void setTuesdaySchedual(Schedual tuesdaySchedual) {
        this.tuesdaySchedual = tuesdaySchedual;
    }

    public Schedual getWednesdaySchedual() {
        return wednesdaySchedual;
    }

    public void setWednesdaySchedual(Schedual wednesdaySchedual) {
        this.wednesdaySchedual = wednesdaySchedual;
    }

    public Schedual getThursdaySchedual() {
        return thursdaySchedual;
    }

    public void setThursdaySchedual(Schedual thursdaySchedual) {
        this.thursdaySchedual = thursdaySchedual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedualCourse that = (SchedualCourse) o;

        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (dateFrom != null ? !dateFrom.equals(that.dateFrom) : that.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(that.dateTo) : that.dateTo != null) return false;
        if (genderId != null ? !genderId.equals(that.genderId) : that.genderId != null) return false;
        if (genderName != null ? !genderName.equals(that.genderName) : that.genderName != null) return false;
        if (generalNote != null ? !generalNote.equals(that.generalNote) : that.generalNote != null) return false;
        if (mondaySchedual != null ? !mondaySchedual.equals(that.mondaySchedual) : that.mondaySchedual != null)
            return false;
        if (schedualCourseId != null ? !schedualCourseId.equals(that.schedualCourseId) : that.schedualCourseId != null)
            return false;
        if (sectionId != null ? !sectionId.equals(that.sectionId) : that.sectionId != null) return false;
        if (sectionName != null ? !sectionName.equals(that.sectionName) : that.sectionName != null) return false;
        if (studyPlan != null ? !studyPlan.equals(that.studyPlan) : that.studyPlan != null) return false;
        if (sundaySchedual != null ? !sundaySchedual.equals(that.sundaySchedual) : that.sundaySchedual != null)
            return false;
        if (thursdaySchedual != null ? !thursdaySchedual.equals(that.thursdaySchedual) : that.thursdaySchedual != null)
            return false;
        if (tuesdaySchedual != null ? !tuesdaySchedual.equals(that.tuesdaySchedual) : that.tuesdaySchedual != null)
            return false;
        if (wednesdaySchedual != null ? !wednesdaySchedual.equals(that.wednesdaySchedual) : that.wednesdaySchedual != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schedualCourseId != null ? schedualCourseId.hashCode() : 0;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + (sectionId != null ? sectionId.hashCode() : 0);
        result = 31 * result + (genderId != null ? genderId.hashCode() : 0);
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (genderName != null ? genderName.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (sectionName != null ? sectionName.hashCode() : 0);
        result = 31 * result + (generalNote != null ? generalNote.hashCode() : 0);
        result = 31 * result + (studyPlan != null ? studyPlan.hashCode() : 0);
        result = 31 * result + (sundaySchedual != null ? sundaySchedual.hashCode() : 0);
        result = 31 * result + (mondaySchedual != null ? mondaySchedual.hashCode() : 0);
        result = 31 * result + (tuesdaySchedual != null ? tuesdaySchedual.hashCode() : 0);
        result = 31 * result + (wednesdaySchedual != null ? wednesdaySchedual.hashCode() : 0);
        result = 31 * result + (thursdaySchedual != null ? thursdaySchedual.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SchedualCourse{" +
                "schedualCourseId='" + schedualCourseId + '\'' +
                ", classId='" + classId + '\'' +
                ", sectionId='" + sectionId + '\'' +
                ", genderId='" + genderId + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", genderName='" + genderName + '\'' +
                ", className='" + className + '\'' +
                ", sectionName='" + sectionName + '\'' +
                ", generalNote='" + generalNote + '\'' +
                ", studyPlan='" + studyPlan + '\'' +
                ", sundaySchedual=" + sundaySchedual +
                ", mondaySchedual=" + mondaySchedual +
                ", tuesdaySchedual=" + tuesdaySchedual +
                ", wednesdaySchedual=" + wednesdaySchedual +
                ", thursdaySchedual=" + thursdaySchedual +
                '}';
    }
}
