package com.applikable.Schools.Classes;

/**
 * Created by Alhusban on 06/12/2015.
 */
public class Notification {
    String title;
    String message;
    String link;

    public Notification() {
    }

    public Notification(String title, String message, String link) {
        this.title = title;
        this.message = message;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
