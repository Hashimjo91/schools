package com.applikable.Schools.Classes;

import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * @author Mohammad Al-Najjar
 * @since 10/03/2015
 */
@org.simpleframework.xml.Root(name = "ArrayOfSchedualCourseObj")
public class Root {

    @ElementList(name = "SchedualCourseObj", inline = true)
    private List<SchedualCourse> schedualCourseList;

    public List<SchedualCourse> getSchedualCourseList() {
        return schedualCourseList;
    }

    public void setSchedualCourseList(List<SchedualCourse> schedualCourseList) {
        this.schedualCourseList = schedualCourseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Root root = (Root) o;

        if (schedualCourseList != null ? !schedualCourseList.equals(root.schedualCourseList) : root.schedualCourseList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return schedualCourseList != null ? schedualCourseList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Root{" +
                "schedualCourseList=" + schedualCourseList +
                '}';
    }
}
