package com.applikable.Schools.Classes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Hashim on 10/03/2015.
 */
@Root(name = "ArrayOfSchedualCourseObj")
public class ArrayOfSchedualCourseObj {
    @Element
    private List<SchedualCourseObj> SchedualCourseObj;

    public ArrayOfSchedualCourseObj() {
    }

    public List<SchedualCourseObj> getSchedualCourseObj() {
        return SchedualCourseObj;
    }

    @Root(name = "SchedualCourseObj")
    public static class SchedualCourseObj {

        @Element(name = "SchedualCourseId")
        private String SchedualCourseId;
        @Element(name = "ClassId")
        private String ClassId;
        @Element(name = "SectionId")
        private String SectionId;
        @Element(name = "GenderId")
        private String GenderId;
        @Element(name = "DateFrom")
        private String DateFrom;
        @Element(name = "DateTo")
        private String DateTo;
        @Element(name = "GenderName")
        private String GenderName;
        @Element(name = "ClassName")
        private String ClassName;
        @Element(name = "SectionName")
        private String SectionName;
        @Element(name = "GeneralNote")
        private String GeneralNote;
        @Element(name = "StudyPlan")
        private String StudyPlan;
        @Element(name = "SundaySchedual")
        private SundaySchedual SundaySchedual;
        @Element(name = "MondaySchedual")
        private MondaySchedual MondaySchedual;
        @Element(name = "TuesdaySchedual")
        private TuesdaySchedual TuesdaySchedual;
        @Element(name = "WednesdaySchedual")
        private WednesdaySchedual WednesdaySchedual;
        @Element(name = "ThursdaySchedual")
        private ThursdaySchedual ThursdaySchedual;

        public SchedualCourseObj() {
        }

        public String getSchedualCourseId() {
            return SchedualCourseId;
        }

        public String getClassId() {
            return ClassId;
        }

        public String getSectionId() {
            return SectionId;
        }

        public String getGenderId() {
            return GenderId;
        }

        public String getDateFrom() {
            return DateFrom;
        }

        public String getDateTo() {
            return DateTo;
        }

        public String getGenderName() {
            return GenderName;
        }

        public String getClassName() {
            return ClassName;
        }

        public String getSectionName() {
            return SectionName;
        }

        public String getGeneralNote() {
            return GeneralNote;
        }

        public String getStudyPlan() {
            return StudyPlan;
        }

        public SundaySchedual getSundaySchedual() {
            return SundaySchedual;
        }

        public MondaySchedual getMondaySchedual() {
            return MondaySchedual;
        }

        public TuesdaySchedual getTuesdaySchedual() {
            return TuesdaySchedual;
        }

        public WednesdaySchedual getWednesdaySchedual() {
            return WednesdaySchedual;
        }

        public ThursdaySchedual getThursdaySchedual() {
            return ThursdaySchedual;
        }


    }

    @Root(name = "SundaySchedual")
    public static class SundaySchedual {
        @Element(name = "Session1")
        private String Session1;
        @Element(name = "Session2")
        private String Session2;
        @Element(name = "Session3")
        private String Session3;
        @Element(name = "Session4")
        private String Session4;
        @Element(name = "Session5")
        private String Session5;
        @Element(name = "Session6")
        private String Session6;
        @Element(name = "Session7")
        private String Session7;
        @Element(name = "Session8")
        private String Session8;
        @Element(name = "Note1")
        private String Note1;
        @Element(name = "Note2")
        private String Note2;
        @Element(name = "Note3")
        private String Note3;
        @Element(name = "Note4")
        private String Note4;
        @Element(name = "Note5")
        private String Note5;
        @Element(name = "Note6")
        private String Note6;
        @Element(name = "Note7")
        private String Note7;
        @Element(name = "Note8")
        private String Note8;

        public SundaySchedual() {
        }

        public String getSession1() {
            return Session1;
        }

        public String getSession2() {
            return Session2;
        }

        public String getSession3() {
            return Session3;
        }

        public String getSession4() {
            return Session4;
        }

        public String getSession5() {
            return Session5;
        }

        public String getSession6() {
            return Session6;
        }

        public String getSession7() {
            return Session7;
        }

        public String getSession8() {
            return Session8;
        }

        public String getNote1() {
            return Note1;
        }

        public String getNote2() {
            return Note2;
        }

        public String getNote3() {
            return Note3;
        }

        public String getNote4() {
            return Note4;
        }

        public String getNote5() {
            return Note5;
        }

        public String getNote6() {
            return Note6;
        }

        public String getNote7() {
            return Note7;
        }

        public String getNote8() {
            return Note8;
        }
    }

    @Root(name = "MondaySchedual")
    public static class MondaySchedual {

        @Element(name = "Session1")
        private String Session1;
        @Element(name = "Session2")
        private String Session2;
        @Element(name = "Session3")
        private String Session3;
        @Element(name = "Session4")
        private String Session4;
        @Element(name = "Session5")
        private String Session5;
        @Element(name = "Session6")
        private String Session6;
        @Element(name = "Session7")
        private String Session7;
        @Element(name = "Session8")
        private String Session8;
        @Element(name = "Note1")
        private String Note1;
        @Element(name = "Note2")
        private String Note2;
        @Element(name = "Note3")
        private String Note3;
        @Element(name = "Note4")
        private String Note4;
        @Element(name = "Note5")
        private String Note5;
        @Element(name = "Note6")
        private String Note6;
        @Element(name = "Note7")
        private String Note7;
        @Element(name = "Note8")
        private String Note8;

        public MondaySchedual() {
        }

        public String getSession1() {
            return Session1;
        }

        public String getSession2() {
            return Session2;
        }

        public String getSession3() {
            return Session3;
        }

        public String getSession4() {
            return Session4;
        }

        public String getSession5() {
            return Session5;
        }

        public String getSession6() {
            return Session6;
        }

        public String getSession7() {
            return Session7;
        }

        public String getSession8() {
            return Session8;
        }

        public String getNote1() {
            return Note1;
        }

        public String getNote2() {
            return Note2;
        }

        public String getNote3() {
            return Note3;
        }

        public String getNote4() {
            return Note4;
        }

        public String getNote5() {
            return Note5;
        }

        public String getNote6() {
            return Note6;
        }

        public String getNote7() {
            return Note7;
        }

        public String getNote8() {
            return Note8;
        }


    }

    @Root(name = "ThursdaySchedual")
    public static class ThursdaySchedual {
        @Element(name = "Session1")
        private String Session1;
        @Element(name = "Session2")
        private String Session2;
        @Element(name = "Session3")
        private String Session3;
        @Element(name = "Session4")
        private String Session4;
        @Element(name = "Session5")
        private String Session5;
        @Element(name = "Session6")
        private String Session6;
        @Element(name = "Session7")
        private String Session7;
        @Element(name = "Session8")
        private String Session8;
        @Element(name = "Note1")
        private String Note1;
        @Element(name = "Note2")
        private String Note2;
        @Element(name = "Note3")
        private String Note3;
        @Element(name = "Note4")
        private String Note4;
        @Element(name = "Note5")
        private String Note5;
        @Element(name = "Note6")
        private String Note6;
        @Element(name = "Note7")
        private String Note7;
        @Element(name = "Note8")
        private String Note8;

        public ThursdaySchedual() {
        }

        public String getSession1() {
            return Session1;
        }

        public String getSession2() {
            return Session2;
        }

        public String getSession3() {
            return Session3;
        }

        public String getSession4() {
            return Session4;
        }

        public String getSession5() {
            return Session5;
        }

        public String getSession6() {
            return Session6;
        }

        public String getSession7() {
            return Session7;
        }

        public String getSession8() {
            return Session8;
        }

        public String getNote1() {
            return Note1;
        }

        public String getNote2() {
            return Note2;
        }

        public String getNote3() {
            return Note3;
        }

        public String getNote4() {
            return Note4;
        }

        public String getNote5() {
            return Note5;
        }

        public String getNote6() {
            return Note6;
        }

        public String getNote7() {
            return Note7;
        }

        public String getNote8() {
            return Note8;
        }


    }

    @Root(name = "TuesdaySchedual")
    public static class TuesdaySchedual {

        @Element(name = "Session1")
        private String Session1;
        @Element(name = "Session2")
        private String Session2;
        @Element(name = "Session3")
        private String Session3;
        @Element(name = "Session4")
        private String Session4;
        @Element(name = "Session5")
        private String Session5;
        @Element(name = "Session6")
        private String Session6;
        @Element(name = "Session7")
        private String Session7;
        @Element(name = "Session8")
        private String Session8;
        @Element(name = "Note1")
        private String Note1;
        @Element(name = "Note2")
        private String Note2;
        @Element(name = "Note3")
        private String Note3;
        @Element(name = "Note4")
        private String Note4;
        @Element(name = "Note5")
        private String Note5;
        @Element(name = "Note6")
        private String Note6;
        @Element(name = "Note7")
        private String Note7;
        @Element(name = "Note8")
        private String Note8;

        public TuesdaySchedual() {
        }

        public String getSession1() {
            return Session1;
        }

        public String getSession2() {
            return Session2;
        }

        public String getSession3() {
            return Session3;
        }

        public String getSession4() {
            return Session4;
        }

        public String getSession5() {
            return Session5;
        }

        public String getSession6() {
            return Session6;
        }

        public String getSession7() {
            return Session7;
        }

        public String getSession8() {
            return Session8;
        }

        public String getNote1() {
            return Note1;
        }

        public String getNote2() {
            return Note2;
        }

        public String getNote3() {
            return Note3;
        }

        public String getNote4() {
            return Note4;
        }

        public String getNote5() {
            return Note5;
        }

        public String getNote6() {
            return Note6;
        }

        public String getNote7() {
            return Note7;
        }

        public String getNote8() {
            return Note8;
        }


    }

    @Root(name = "WednesdaySchedual")
    public static class WednesdaySchedual {

        @Element(name = "Session1")
        private String Session1;
        @Element(name = "Session2")
        private String Session2;
        @Element(name = "Session3")
        private String Session3;
        @Element(name = "Session4")
        private String Session4;
        @Element(name = "Session5")
        private String Session5;
        @Element(name = "Session6")
        private String Session6;
        @Element(name = "Session7")
        private String Session7;
        @Element(name = "Session8")
        private String Session8;
        @Element(name = "Note1")
        private String Note1;
        @Element(name = "Note2")
        private String Note2;
        @Element(name = "Note3")
        private String Note3;
        @Element(name = "Note4")
        private String Note4;
        @Element(name = "Note5")
        private String Note5;
        @Element(name = "Note6")
        private String Note6;
        @Element(name = "Note7")
        private String Note7;
        @Element(name = "Note8")
        private String Note8;

        public WednesdaySchedual() {
        }

        public String getSession1() {
            return Session1;
        }

        public String getSession2() {
            return Session2;
        }

        public String getSession3() {
            return Session3;
        }

        public String getSession4() {
            return Session4;
        }

        public String getSession5() {
            return Session5;
        }

        public String getSession6() {
            return Session6;
        }

        public String getSession7() {
            return Session7;
        }

        public String getSession8() {
            return Session8;
        }

        public String getNote1() {
            return Note1;
        }

        public String getNote2() {
            return Note2;
        }

        public String getNote3() {
            return Note3;
        }

        public String getNote4() {
            return Note4;
        }

        public String getNote5() {
            return Note5;
        }

        public String getNote6() {
            return Note6;
        }

        public String getNote7() {
            return Note7;
        }

        public String getNote8() {
            return Note8;
        }


    }

}
