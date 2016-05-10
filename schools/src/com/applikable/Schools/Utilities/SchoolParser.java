package com.applikable.Schools.Utilities;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.applikable.Schools.Classes.Bus;
import com.applikable.Schools.Classes.Root;
import com.applikable.Schools.Classes.Schedual;
import com.applikable.Schools.Classes.SchedualCourse;
import com.applikable.Schools.LogIn.MyLogin;
import com.applikable.Schools.R;
import com.google.android.gms.maps.model.LatLng;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SchoolParser {
    public final static String HOST = "digitaltouch.cloudapp.net";
    public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";
    public final static String CONTENT_TYPE = "application/x-www-form-urlencoded";

    /**
     * @return AccessToken
     */
    public static String BusLogin(){
        String link="http://81.28.112.42/MobileService/token?grant_type=password&username=academy&password=academy332&server=2";
        try {
            String response= null;
            try {
                response = sendLogInPost("http://81.28.112.42/MobileService/token","grant_type=password&username=academy&password=academy332&server=2");
            } catch (IOException e) {
                e.printStackTrace();
            }




            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://81.28.112.42/MobileService/token");



            List<NameValuePair> id = new ArrayList<NameValuePair>();
            id.add(new BasicNameValuePair("grant_type","password"));
            id.add(new BasicNameValuePair("username","academy"));
            id.add(new BasicNameValuePair("password","academy332"));
            id.add(new BasicNameValuePair("server","2"));


            try {
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(id, "utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
                try {
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    InputStream inputStream = httpResponse.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String BufferedString = null;
                    while ((BufferedString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(BufferedString);
                    }

                    response = stringBuilder.toString();
                    Log.e("responce=",response);
                } catch (Exception e) {

                }
            } catch (Exception e) {

            }


            response = response.toString().replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
            JSONObject jsonObj = new JSONObject(response);
            return jsonObj.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void GetBusListLL(String AccessToken) {


    }

    public static List<Bus> GetBusList(String AccessToken){
        String link="http://81.28.112.42/MobileService/api/Vehicles";
        List<Bus> buses=new ArrayList<>();
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(link);
            httpPost.setHeader("Authorization", "Bearer "+AccessToken);
            HttpResponse httpResponse = httpClient.execute(httpPost);
                InputStream inputStream = httpResponse.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String BufferedString = null;
                while ((BufferedString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(BufferedString);
                }

                String response3 = stringBuilder.toString();



                    response3 = response3.toString().replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
            JSONArray jsonObj = new JSONArray(response3);
            for (int i = 0; i < jsonObj.length(); i++) {
                JSONObject cjsonObject=jsonObj.getJSONObject(i);
                Bus cBus=new Bus();

                cBus.setnID(cjsonObject.getString("nID"));
                cBus.setStrTEID(cjsonObject.getString("strTEID"));
                cBus.setStrCarNum(cjsonObject.getString("strCarNum"));
                cBus.setStrTESim(cjsonObject.getString("strTESim"));
                cBus.setStrTEID(cjsonObject.getString("strTEID"));
                cBus.setnTEType(cjsonObject.getString("nTEType"));
                cBus.setStrGroupName(cjsonObject.getString("strGroupName"));
                cBus.setStrOwnerName(cjsonObject.getString("strOwnerName"));
                cBus.setStrOwnerTel(cjsonObject.getString("strOwnerTel"));
                cBus.setStrOwnerAddress(cjsonObject.getString("strOwnerAddress"));
                cBus.setStrRemark(cjsonObject.getString("strRemark"));
                cBus.setStrRemark(cjsonObject.getString("strRemark"));
                cBus.setStrIconID(cjsonObject.getString("strIconID"));
                cBus.setnFuelBoxSize(cjsonObject.getString("nFuelBoxSize"));
                cBus.setnMileageInit(cjsonObject.getString("nMileageInit"));
                cBus.setnOverSpeed(cjsonObject.getString("nOverSpeed"));
                cBus.setnSMSNoticeState(cjsonObject.getString("nSMSNoticeState"));

                buses.add(cBus);

            }
            return buses;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buses;
    }
    public static String registerUser(Context con, String MobileNumber, String FirstName, String LastName, String PrimaryEmail, String DateOfBirth, String GenderId, String Operator, String RegKey, List<childList> list) {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://digitaltouch.cloudapp.net/School/CMS/API/MobileAPIWebService.asmx/RegisterUser");
        httpPost.addHeader("content-type", "Application/x-www-form-urlencoded");
        int ii = Integer.parseInt(GenderId);

        List<NameValuePair> id = new ArrayList<NameValuePair>();

        JSONArray ja = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("MobileNumber", Integer.parseInt(MobileNumber.trim()));
            jsonObject.put("Password", "pass");
            jsonObject.put("FirstName", FirstName);
            jsonObject.put("LastName", LastName);
            jsonObject.put("PrimaryEmail", PrimaryEmail);
            jsonObject.put("CityOfRedince", 73);
            jsonObject.put("DateOfBirth", DateOfBirth);
            jsonObject.put("GenderId", (ii + 1));
            jsonObject.put("Faculty", 5);
            jsonObject.put("PlatformId", 11);
            jsonObject.put("Status", 2);
            jsonObject.put("DeviceType", "ddddddddddd");
            jsonObject.put("Operator", Operator);
            jsonObject.put("RegKey", RegKey.equals("")?"regKeyHere":RegKey);
            jsonObject.put("ActivationCode", "FW-311-JZ");
            jsonObject.put("SchoolName", "IPAS");
            for (int i = 0; i < list.size(); i++) {
                JSONObject child = new JSONObject();
                child.put("STDName", list.get(i).getName());
                child.put("ClassId", list.get(i).getSClass());
                ja.put(child);
            }
            jsonObject.put("ChildrenList", ja);


            BasicNameValuePair email1 = new BasicNameValuePair("data", jsonObject.toString());
            id.add(email1);
        } catch (Exception e) {
        }
        String DataTest=id.toString();
        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(id, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            if (RegKey.equals("")) {
                Tools.setShared(con,"urlpost",id.toString());
            }else{
                try {
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    InputStream inputStream = httpResponse.getEntity().getContent();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    String BufferedString = null;
                    while ((BufferedString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(BufferedString);
                    }

                    response = stringBuilder.toString();
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public static String postComplain(String name, String suggestion, String email) {
        String response = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://digitaltouch.cloudapp.net/School/CMS/API/MobileAPIWebService.asmx/SaveSuggestionAndComplaint");
        httpPost.addHeader("content-type", "Application/x-www-form-urlencoded");
        BasicNameValuePair username = new BasicNameValuePair("PersonName", name);
        BasicNameValuePair suggestion1 = new BasicNameValuePair("suggestion", suggestion);
        BasicNameValuePair email1 = new BasicNameValuePair("email", email);
        List<NameValuePair> id = new ArrayList<NameValuePair>();
        id.add(username);
        id.add(suggestion1);
        id.add(email1);


        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(id, "utf-8");
            httpPost.setEntity(urlEncodedFormEntity);
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                InputStream inputStream = httpResponse.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String BufferedString = null;
                while ((BufferedString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(BufferedString);
                }

                response = stringBuilder.toString();
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
        return response;
    }

    public static String getExam(Context context, String cID, String sID, String gID, String etID) {
        String isDone = "true";
        try {
            DataBase db = new DataBase(context);
            String params = "classId=" + cID + "&sectionId=" + sID + "&studentGenderId=" + gID + "&examTypeId=" + etID;
            String Data = sendLogInPost("http://digitaltouch.cloudapp.net/School/CMS/API/MobileAPIWebService.asmx/GetExamsSchedule?", params);

            Data = Data.toString().replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
            JSONObject jsonObj = new JSONObject(Data);
            JSONArray contacts = jsonObj.getJSONArray("data");
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);
                String ExamSchedualId = c.getString("ExamSchedualId");
                String ClassId = c.getString("ClassId");
                String SectionId = c.getString("SectionId");
                String GenderId = c.getString("GenderId");
                String ExamTypeId = c.getString("ExamTypeId");
                String ExamDate = c.getString("ExamDate");
                String GenderName = c.getString("GenderName");
                String DayName = c.getString("DayName");
                String ClassName = c.getString("ClassName");
                String SectionName = c.getString("SectionName");
                String Course = c.getString("Course");
                String Tutorial = c.getString("Tutorial");


                db.insertExam(ExamSchedualId, ClassId, SectionId, GenderId, ExamTypeId, ExamDate, DayName, ClassName, SectionName, Course, Tutorial);
            }
        } catch (Exception e) {

        }
        return "";
    }

    public static String getMarks(Context context, String user, String pass) {
        String isDone = "true";
        DataBase db = new DataBase(context);
        try {
            MyLogin myLogin = new MyLogin("", "", "");
            String html = myLogin.loginjsoup(user, pass);
//          = myLogin.getPageContentFirst("http://eduwave.elearning.jo/Eduwave/eduwave_sms/studentmarks.aspx");
            Document document = Jsoup.parse(html);
            Elements elements = document.select("table[id=dgFirstTerm] > tbody > tr");
            Elements StudentInfo = document.select("td[id=tdStudentInfo] tr");

            String SNAME = StudentInfo.get(0).text().split(":")[1];
            String SCLASS = StudentInfo.get(3).text().split(":")[1];
            String SSECTION = StudentInfo.get(4).text().split(":")[1];
            int i = 0;
            for (Element element : elements) {
                if (i != 0) {
                    Elements tds = element.select("td");
                    db.insertMarks(user, tds.get(0).text(), tds.get(1).text(), tds.get(2).text(), tds.get(3).text(), tds.get(4).text(), tds.get(5).text(), "first", SNAME, SCLASS, SSECTION);

                } else {
                    i++;
                }
            }

            Elements elements1 = document.select("table[id=dgSecondTerm] > tbody > tr");
            int i1 = 0;
            for (Element element : elements1) {
                element.text().contains(context.getString(R.string.class1));
                if (i1 != 0) {
                    Elements tds = element.select("td");
                    db.insertMarks(user, tds.get(0).text(), tds.get(1).text(), tds.get(2).text(), tds.get(3).text(), tds.get(4).text(), tds.get(5).text(), "sec", SNAME, SCLASS, SSECTION);

                } else {
                    i1++;
                }
            }
        } catch (Exception e) {
            isDone = "error";
            e.printStackTrace();
        }


        return isDone;
    }

    public static String getPartialPage(Context context, String url) {
        String html = "null";
        try {
            Document d = Jsoup.connect(url).get();
            Elements elements = d.select("div[class=InnerPages]");
            html = elements.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return html;
    }

    public static String getParsedActivities(Context context) {
        String isDone = "true";

        try {
            DataBase db = new DataBase(context);
            Document document = Jsoup.connect("http://www.ipas-edu.com/Activities.aspx?Lng=2&page=2").get();
            Elements elements = document.select("div [class=sponsorListHolder]");
            Elements divs = elements.select("div[class=sponsorData]");
            Elements imgs = elements.select("div[class=sponsorFlip]").select("img");
            for (int i = 0; i < divs.size(); i++) {
                String text;
                text = divs.get(i).text();
                String img = imgs.get(i).attr("src");
                String link = divs.get(i).select("a").attr("href");


                db.insertNews(i + "", text, "http://www.ipas-edu.com/" + link, "http://www.ipas-edu.com/" + img, "101", "activities");
            }


        } catch (Exception e) {
            isDone = "error";
        }

        return isDone;
    }

    public static String getParsedNews(Context context) {

        String isDone = "true";

        try {
            String html = null;

            Document document = Jsoup.connect("http://www.ipas-edu.com/show.aspx?Lng=2&p=news").get();


            Elements elements = document.select("table[id=ctl00_ContentPlaceHolder1_ctl00_dlItems] > tbody");
            Elements tds = elements.select("td");
            DataBase db = new DataBase(context);
            int i = 0;
            for (Element e : tds) {
                String link = e.select("legend > a").attr("href");
                String text = e.select("a").text();
                String img = e.select("legend > a > img").attr("src");
                img = img.replace("100X100", "300X300");
                if (text.isEmpty())
                    continue;

                db.insertNews(i + "", text, "http://www.ipas-edu.com/" + link, "http://www.ipas-edu.com/" + img, "100", "news");
                i++;
            }


        } catch (Exception e) {
            isDone = "error";
        }


        return isDone;
    }

    public static String getshced(Context context, String cID, String sID, String gID) {
        String isDone = "true";
        DataBase db = new DataBase(context);
        try {


            String Data = sendLogInPost("http://digitaltouch.cloudapp.net/School/CMS/API/MobileAPIWebService.asmx/GetCourseSchedule?",
                    "classId=" + cID + "&sectionId=" + sID + "&studentGenderId=" + gID);
            if (Data.contains("<ArrayOfSchedualCourseObj xsi:nil=\"true\"/>")) {
                return "error";
            }
            Serializer serializer = new Persister();

            Root root = serializer.read(Root.class, Data);

            List<SchedualCourse> schedualCourses = root.getSchedualCourseList();
            for (SchedualCourse schedualCourse : schedualCourses) {


                String SchedualCourseId = schedualCourse.getSchedualCourseId();
                String ClassId = schedualCourse.getClassId();
                String SectionId = schedualCourse.getSectionId();
                String GenderId = schedualCourse.getGenderId();
                String DateFrom = schedualCourse.getDateFrom();
                String DateTo = schedualCourse.getDateTo();
                String GenderName = schedualCourse.getGenderName();
                String ClassName = schedualCourse.getClassName();
                String SectionName = schedualCourse.getSectionName();
                String GeneralNote = schedualCourse.getGeneralNote();
                String StudyPlan = schedualCourse.getStudyPlan();
                String Session = null;
                String Note = null;

                Schedual sunday = schedualCourse.getSundaySchedual();
                Schedual monday = schedualCourse.getMondaySchedual();
                Schedual tuesday = schedualCourse.getTuesdaySchedual();
                Schedual wednesday = schedualCourse.getWednesdaySchedual();
                Schedual thursday = schedualCourse.getThursdaySchedual();


                db.insertSession(1 + "Session", sunday.getSession1(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(2 + "Session", sunday.getSession2(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(3 + "Session", sunday.getSession3(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(4 + "Session", sunday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(4 + "Session", sunday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(5 + "Session", sunday.getSession5(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(6 + "Session", sunday.getSession6(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(7 + "Session", sunday.getSession7(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(8 + "Session", sunday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertSession(8 + "Session", sunday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "1");

                db.insertNote(1 + "Note", sunday.getNote1(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(2 + "Note", sunday.getNote2(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(3 + "Note", sunday.getNote3(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(4 + "Note", sunday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(4 + "Note", sunday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(5 + "Note", sunday.getNote5(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(6 + "Note", sunday.getNote6(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(7 + "Note", sunday.getNote7(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(8 + "Note", sunday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "1");
                db.insertNote(8 + "Note", sunday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "1");


                db.insertSession(1 + "Session", monday.getSession1(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(2 + "Session", monday.getSession2(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(3 + "Session", monday.getSession3(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(4 + "Session", monday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(4 + "Session", monday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(5 + "Session", monday.getSession5(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(6 + "Session", monday.getSession6(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(7 + "Session", monday.getSession7(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(8 + "Session", monday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertSession(8 + "Session", monday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "2");

                db.insertNote(1 + "Note", monday.getNote1(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(2 + "Note", monday.getNote2(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(3 + "Note", monday.getNote3(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(4 + "Note", monday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(4 + "Note", monday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(5 + "Note", monday.getNote5(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(6 + "Note", monday.getNote6(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(7 + "Note", monday.getNote7(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(8 + "Note", monday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "2");
                db.insertNote(8 + "Note", monday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "2");


                db.insertSession(1 + "Session", tuesday.getSession1(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(2 + "Session", tuesday.getSession2(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(3 + "Session", tuesday.getSession3(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(4 + "Session", tuesday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(4 + "Session", tuesday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(5 + "Session", tuesday.getSession5(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(6 + "Session", tuesday.getSession6(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(7 + "Session", tuesday.getSession7(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(8 + "Session", tuesday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertSession(8 + "Session", tuesday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "3");

                db.insertNote(1 + "Note", tuesday.getNote1(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(2 + "Note", tuesday.getNote2(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(3 + "Note", tuesday.getNote3(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(4 + "Note", tuesday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(4 + "Note", tuesday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(5 + "Note", tuesday.getNote5(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(6 + "Note", tuesday.getNote6(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(7 + "Note", tuesday.getNote7(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(8 + "Note", tuesday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "3");
                db.insertNote(8 + "Note", tuesday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "3");


                db.insertSession(1 + "Session", wednesday.getSession1(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(2 + "Session", wednesday.getSession2(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(3 + "Session", wednesday.getSession3(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(4 + "Session", wednesday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(4 + "Session", wednesday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(5 + "Session", wednesday.getSession5(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(6 + "Session", wednesday.getSession6(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(7 + "Session", wednesday.getSession7(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(8 + "Session", wednesday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertSession(8 + "Session", wednesday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "4");

                db.insertNote(1 + "Note", wednesday.getNote1(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(2 + "Note", wednesday.getNote2(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(3 + "Note", wednesday.getNote3(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(4 + "Note", wednesday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(4 + "Note", wednesday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(5 + "Note", wednesday.getNote5(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(6 + "Note", wednesday.getNote6(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(7 + "Note", wednesday.getNote7(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(8 + "Note", wednesday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "4");
                db.insertNote(8 + "Note", wednesday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "4");


                db.insertSession(1 + "Session", thursday.getSession1(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(2 + "Session", thursday.getSession2(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(3 + "Session", thursday.getSession3(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(4 + "Session", thursday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(4 + "Session", thursday.getSession4(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(5 + "Session", thursday.getSession5(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(6 + "Session", thursday.getSession6(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(7 + "Session", thursday.getSession7(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(8 + "Session", thursday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertSession(8 + "Session", thursday.getSession8(), ClassId, SectionId, GenderId, DateFrom, "5");

                db.insertNote(1 + "Note", thursday.getNote1(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(2 + "Note", thursday.getNote2(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(3 + "Note", thursday.getNote3(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(4 + "Note", thursday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(4 + "Note", thursday.getNote4(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(5 + "Note", thursday.getNote5(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(6 + "Note", thursday.getNote6(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(7 + "Note", thursday.getNote7(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(8 + "Note", thursday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "5");
                db.insertNote(8 + "Note", thursday.getNote8(), ClassId, SectionId, GenderId, DateFrom, "5");


                db.insertSchedDetails(ClassName, SectionName, DateFrom, DateTo, GenderName, GenderId, GeneralNote, StudyPlan);
            }


        } catch (Exception e) {
            isDone = "error";
            e.printStackTrace();

        }
        return isDone;
    }

    public static String getAdvices(Context context) {
        String isDone = "true";
        DataBase db = new DataBase(context);
        try {
            String Data = getpage("http://digitaltouch.cloudapp.net/School/CMS/API/MobileAPIWebService.asmx/GetAdvices");
            Data = Data.toString().replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
            JSONObject jsonObj = new JSONObject(Data);
            JSONArray contacts = jsonObj.getJSONArray("data");
            for (int i = 0; i < contacts.length(); i++) {
                JSONObject c = contacts.getJSONObject(i);
                String AdviceId = c.getString("AdviceId");
                String Name = c.getString("Name");
                String Description = c.getString("Description");
                String ImageLink = c.getString("ImageLink");


                db.insertAdvice(AdviceId, Name, Description, ImageLink);
            }

            isDone = "true";


        } catch (Exception e) {
            e.printStackTrace();
            isDone = "error";

        }
        return isDone;
    }

    private static String getpage(String url) throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setReadTimeout(0 /* milliseconds */);
        conn.setConnectTimeout(0 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();

        InputStream stream = conn.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);

        }
        stream.close();

        return response.toString();

    }
/*    private static String getPageContentFirst(String url) throws Exception {
        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Host",HOST);
//		if (cookies != null)
//			for (String cookie : cookies) {
//				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//
//			}

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code here : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);

        }
        in.close();

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

        return response.toString();

    }*/

    /**
     * for the cookie
     **/
  /*  public List<String> getCookies() {
        return cookies;
    }
*/
    public static void setCookies(List<String> cookies) {
        cookies = cookies;
    }
//    private static String sendGet(String url) throws Exception {
//        URL obj = new URL(url);
//        conn = (HttpsURLConnection) obj.openConnection();
//        conn.setChunkedStreamingMode(0);
//        conn.setRequestMethod("GET");
//        conn.setUseCaches(false);
//        conn.setRequestProperty("Host", HOST);
//        conn.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = conn.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code here : " + responseCode);
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                conn.getInputStream(), "UTF-8"));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//
//        }
//        in.close();
//
//        return response.toString();
//    }

    /**
     * to get page after logIn
     *
     * @throws IOException
     */

    public static String sendLogInPost(String url, String postParams)
            throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");

        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Host", HOST);

//		for (String cookie : cookies) {
//			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//
//		}

        conn.setDoOutput(true);
        conn.setDoInput(true);
        // Send post request

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();

        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).toString();

        }
        in.close();

        return response.toString();

    }
    public static String sendLogInPostWithHeader(String url, String header)
            throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setUseCaches(false);
        conn.setRequestMethod("GET");

        conn.setRequestProperty("Content-Type", CONTENT_TYPE);
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Host", HOST);
        conn.setRequestProperty("access_token", header);

//		for (String cookie : cookies) {
//			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//
//		}

        conn.setDoOutput(true);
        conn.setDoInput(true);
        // Send post request

        int responseCode = conn.getResponseCode();

        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Header parameters : " + "bearer "+ header);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).toString();

        }
        in.close();

        return response.toString();

    }

    public static LatLng getLocationOfBus(String bnID,String AccessToken) {
        String link="http://81.28.112.42/MobileService/api/Vehicles/"+bnID+"/Location";
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(link);
            httpPost.setHeader("Authorization", "Bearer "+AccessToken);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String BufferedString = null;
            while ((BufferedString = bufferedReader.readLine()) != null) {
                stringBuilder.append(BufferedString);
            }

            String response3 = stringBuilder.toString();



            response3 = response3.toString().replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">", "").replace("</string>", "");
            JSONObject jsonObj = new JSONObject(response3);

                Double Lon=Double.parseDouble( jsonObj.getString("Lon"));
                Double Lat=Double.parseDouble( jsonObj.getString("Lat"));
                LatLng latLng=new LatLng(Lat,Lon);

                return latLng;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
