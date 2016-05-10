package com.applikable.Schools.Utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.applikable.Schools.Classes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hashim on 05/03/2015.
 */
public class DataBase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "SchoolDB";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_TABLE_ADVICES = "CREATE TABLE Advices ( id INTEGER ,title TEXT,Description TEXT ,Image TEXT)";
        String CREATE_TABLE_Notification = "CREATE TABLE Notification ( id INTEGER ,title TEXT,message TEXT ,link TEXT,flag Integer)";
        String CREATE_TABLE_STUD = "CREATE TABLE STUD (una TEXT primary key ,pas TEXT,ToRemember Text)";
        String CREATE_TABLE_NA = "CREATE TABLE NewsAnnouncement ( id INTEGER ,title TEXT,Description TEXT ,Image TEXT,TypeID TEXT,TypeName TEXT)";
        String CREATE_TABLE_SCHEDS = "CREATE TABLE Sessions ( id TEXT ,title TEXT,Description TEXT ,Image TEXT,TypeID TEXT,TypeName TEXT,Day TEXT)";
        String CREATE_TABLE_SCHEDN = "CREATE TABLE Note ( id TEXT ,title TEXT,Description TEXT ,Image TEXT,TypeID TEXT,TypeName TEXT,Day TEXT)";
        String CREATE_TABLE_MARKS = "CREATE TABLE Marks ( id TEXT ,course TEXT,first TEXT ,second TEXT,third TEXT,fourth TEXT,final TEXT,type TEXT,Name TEXT ,ClassName TEXT,SectionName TEXT)";
        String CREATE_TABLE_SCHEDDETAILS = "CREATE TABLE ScheDetails (  className TEXT,  sectionName TEXT,dateFrom TEXT, dateTo TEXT, genderName TEXT,  genderId TEXT,genNote TEXT,studyplan TEXT)";
        String CREATE_TABLE_EXAM = "CREATE TABLE Exam (examSchedualId TEXT,classId TEXT,sectionId TEXT,genderId TEXT,examTypeId TEXT, examDate TEXT,genderName TEXT,className TEXT,sectionName TEXT,examTypeName TEXT,tutorial TEXT)";

        // create books table
        db.execSQL(CREATE_TABLE_ADVICES);
        db.execSQL(CREATE_TABLE_Notification);
        db.execSQL(CREATE_TABLE_STUD);
        db.execSQL(CREATE_TABLE_NA);
        db.execSQL(CREATE_TABLE_SCHEDS);
        db.execSQL(CREATE_TABLE_SCHEDN);
        db.execSQL(CREATE_TABLE_MARKS);
        db.execSQL(CREATE_TABLE_SCHEDDETAILS);
        db.execSQL(CREATE_TABLE_EXAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS Advices");
        db.execSQL("DROP TABLE IF EXISTS NewsAnnouncement");

        // create fresh books table
        this.onCreate(db);
    }

    public void delete(String a) {
        try {
            getWritableDatabase().execSQL("delete from " + a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNews(String a1, String a2, String a3, String a4, String a5, String a6) {
        try {


            getWritableDatabase().execSQL("insert into NewsAnnouncement Values('" + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "','" + a5 + "','" + a6 + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStud(String a1, String a2, String a3) {
        try {


            getWritableDatabase().execSQL("insert into STUD Values('" + a1 + "','" + a2 + "','" + a3 + "' )");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void insertNotification(String a1, String a2, String a3) {
        try {


            getWritableDatabase().execSQL("insert into Notification Values('" + a1 + "','" + a2 + "','" + a3 + "' )");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getNotifications() {
        List<Notification> news = new ArrayList<Notification>();
        try {

            String sql = "select * from STUD ";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                news.add(new Notification(c.getString(0), c.getString(1), c.getString(2)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<Advices> getSTUD() {
        List<Advices> news = new ArrayList<Advices>();
        try {

            String sql = "select * from STUD ";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                news.add(new Advices(c.getString(0), c.getString(1), c.getString(2), ""));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<News> getNews() {
        List<News> news = new ArrayList<News>();
        try {

            String sql = "select * from NewsAnnouncement where TypeID='100'";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                news.add(new News(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<News> getAnna() {
        List<News> news = new ArrayList<News>();
        try {

            String sql = "select * from NewsAnnouncement where TypeID='101'";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                news.add(new News(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<Advices> getAdvices() {
        List<Advices> adviceses = new ArrayList<Advices>();
        try {

            String sql = "select * from Advices ";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                adviceses.add(new Advices(c.getString(0), c.getString(1), c.getString(2), c.getString(3)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return adviceses;
    }

    public List<Schedule> getSchedSessionbyDay(String day) {
        List<Schedule> Schedules = new ArrayList<Schedule>();
        try {

            String sql = "select * from Sessions where day='" + day + "'";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                Schedules.add(new Schedule(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Schedules;
    }

    public List<Schedule> getSchedNotebyDay(String day) {
        List<Schedule> Schedules = new ArrayList<Schedule>();
        try {

            String sql = "select * from Note where day='" + day + "'";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                Schedules.add(new Schedule(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Schedules;
    }

    public SchedDetails getSchedDetail() {
        SchedDetails Schedules = null;
        try {

            String sql = "select * from ScheDetails ";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                Schedules = new SchedDetails(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Schedules;
    }

    public List<Exams> getExam() {
        List<Exams> Schedules = new ArrayList<Exams>();
        try {

            String sql = "select * from Exam ";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                Schedules.add(new Exams(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Schedules;
    }

    public void insertAdvice(String a1, String a2, String a3, String a4) {
        try {


            getWritableDatabase().execSQL("insert into Advices Values('" + a1 + "','" + a2 + "','" + a3 + "','" + a4 + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertSession(String SchedualSession, String session, String classId, String sectionId, String genderId, String dateFrom, String day) {
        try {


            getWritableDatabase().execSQL("insert into Sessions Values('" + SchedualSession + "','" + session + "','" + classId + "','" + sectionId + "','" + genderId + "','" + dateFrom + "','" + day + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertNote(String s, String note, String classId, String sectionId, String genderId, String dateFrom, String Day) {
        try {


            getWritableDatabase().execSQL("insert into Note Values('" + s + "','" + note + "','" + classId + "','" + sectionId + "','" + genderId + "','" + dateFrom + "','" + Day + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertSchedDetails(String className, String sectionName, String dateFrom, String dateTo, String genderName, String genderId, String generalNote, String studyPlan) {
        try {


            getWritableDatabase().execSQL("insert into ScheDetails Values('" + className + "','" + sectionName + "','" + dateFrom + "','" + dateTo + "','" + genderName + "','" + genderId + "','" + generalNote + "','" + studyPlan + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertExam(String examSchedualId, String classId, String sectionId, String genderId, String examTypeId, String examDate, String genderName, String className, String sectionName, String examTypeName, String tutorial) {
        try {


            getWritableDatabase().execSQL("insert into Exam Values('" + examSchedualId + "','" + classId + "','" + sectionId + "','" + genderId + "','" + examTypeId + "','" + examDate + "','" + genderName + "','" + className + "','" + sectionName + "','" + examTypeName + "','" + tutorial + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertMarks(String i, String text, String text1, String text2, String text3, String text4, String text5, String type, String name, String classname, String SectionName) {
        try {
            getWritableDatabase().execSQL("Insert into Marks values ('" + i + "','" + text + "','" + text1 + "','" + text2 + "','" + text3 + "','" + text4 + "','" + text5 + "','" + type + "','" + name + "','" + classname + "','" + SectionName + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Marks> getMarks(String id, String type) {


        List<Marks> Schedules = new ArrayList<Marks>();
        try {

            String sql = "select * from Marks where id='" + id + "' AND type='" + type + "' ";
            Cursor c = getReadableDatabase().rawQuery(sql, null);
            while (c.moveToNext()) {
                Schedules.add(new Marks(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(8), c.getString(9), c.getString(10)));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Schedules;
    }
}



