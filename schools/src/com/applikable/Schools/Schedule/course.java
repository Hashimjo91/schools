package com.applikable.Schools.Schedule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.applikable.Schools.Classes.SchedDetails;
import com.applikable.Schools.Classes.Schedule;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.Tools;

import java.util.List;


public class course extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedulebaselayout);
        TextView classid = (TextView) findViewById(R.id.textView5);

        TextView Dates = (TextView) findViewById(R.id.textView6);
        final String[] array = new String[10];
        Intent i = getIntent();
        Bundle b = i.getExtras();
        int day = b.getInt("Day");

        // final String []array=new String[5];
        array[0] = "الحصة الاولى";
        array[1] = "الحصة الثانية";
        array[2] = "الحصة الثالثة";
        array[3] = "الحصة الرابعة";
        array[4] = "وقت الاستراحة";
        array[5] = "الحصة الخامسة";
        array[6] = "الحصة السادسة";
        array[7] = "الحصة السابعة";
        array[8] = "وقت الصلاه";
        array[9] = "الحصة الثامنة";

        final String[] classNo;
        final String[] classMatt;
        DataBase db = new DataBase(this);


        SchedDetails schedDetails = db.getSchedDetail();

        try {
            classid.setText("الصف" + " " + schedDetails.getClassName() + " " + schedDetails.getSectionName());
        } catch (Exception e) {
            classid.setText("");
        }

        try {
            Dates.setText(schedDetails.getDateFrom().replace("12:00:00 AM", "") + "/" + schedDetails.getDateTo().replace("12:00:00 AM", ""));
        } catch (Exception e) {
            Dates.setText("");
        }
        final List<Schedule> schedules = db.getSchedNotebyDay(day + "");
        final List<Schedule> schedules1 = db.getSchedSessionbyDay(day + "");

        ImageView v = new ImageView(this);

        ListView z = (ListView) findViewById(R.id.scheduleList);
        if (!Tools.isOnline(this))
            Toast.makeText(this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
        else if (schedules.isEmpty() && schedules1.isEmpty()) {
            LinearLayout loh = (LinearLayout) findViewById(R.id.lineartohide);
            loh.setVisibility(View.INVISIBLE);
//            android.support.v4.app.FragmentManager fm = course.this.getSupportFragmentManager();
            Toast.makeText(this, "الجدول غير موجود", Toast.LENGTH_SHORT).show();

            finish();
//            UniDialog u = new UniDialog("الجدول غير موجود");
//            u.show(fm, "fragment_edit_name");
        } else {

            try {

                z.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return array.length;
                    }

                    @Override
                    public String getItem(int position) {
                        return array[position];
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v;


                        int po = position;


                        switch (position) {
                            case 4:
                                v = getLayoutInflater().inflate(R.layout.activity_bus, parent, false);
                                (v.findViewById(R.id.busic)).setVisibility(View.GONE);
                                (v.findViewById(R.id.arrow)).setVisibility(View.INVISIBLE);
                                ((TextView) v.findViewById(R.id.bustext)).setText(array[position]);
                                ((TextView) v.findViewById(R.id.bustext)).setTextColor(Color.RED);

                                break;
                            case 8:
                                v = getLayoutInflater().inflate(R.layout.activity_bus, parent, false);
                                (v.findViewById(R.id.arrow)).setVisibility(View.INVISIBLE);

                                (v.findViewById(R.id.busic)).setVisibility(View.GONE);
                                ((TextView) v.findViewById(R.id.bustext)).setText(array[position]);
                                ((TextView) v.findViewById(R.id.bustext)).setTextColor(Color.RED);

                                break;


                            default:

                                v = getLayoutInflater().inflate(R.layout.schedule, parent, false);
                                ((TextView) v.findViewById(R.id.classNo)).setText(array[position]);
                                String className = !schedules1.get(po).getTitle().contains("null") ? schedules1.get(po).getTitle() : "---";
                                String classMate = !schedules.get(po).getTitle().contains("null") ? schedules.get(po).getTitle() : "---";
                                ((TextView) v.findViewById(R.id.className)).setText(className);
                                ((TextView) v.findViewById(R.id.classMatt)).setText(classMate);
                                break;
                        }
                        return v;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
