package com.applikable.Schools.Classes;

/**
 * Created by Hashim on 07/03/2015.
 */
public class Schedule {
    String id;
    String title;
    String description;
    String img;
    String typeID;
    String TypeName;
    String Day;

    public Schedule(String id, String title, String description, String img, String typeID, String typeName, String day) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img = img;
        this.typeID = typeID;
        TypeName = typeName;
        Day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }
}
