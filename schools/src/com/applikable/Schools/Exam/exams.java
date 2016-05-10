package com.applikable.Schools.Exam;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.applikable.Schools.Classes.Exams;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;

import java.util.ArrayList;
import java.util.List;


public class exams extends FragmentActivity {
    String[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        ids = getIntent().getExtras().getString("IDs").split("_");
        setActionbar();
        if (Tools.isOnline(exams.this)) {
            mAsync mAsync = new mAsync();
            mAsync.execute();
        } else {
            Toast.makeText(exams.this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
        }


    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("الامتحانات");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#6d19a2")));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    class obj {
        String day;
        String date;
        String course;
        String mat;

        obj(String day, String date, String course, String mat) {
            this.day = day;
            this.date = date;
            this.course = course;
            this.mat = mat;
        }
    }

    class mAsync extends AsyncTask<Void, Void, Void> {
        DataBase db = new DataBase(exams.this);
        private ProgressDialog mProgressDialog;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                if (Tools.isOnline(exams.this)) {
                    db.delete("Exam");
                    SchoolParser.getExam(exams.this, ids[0], ids[1], ids[2], ids[3]);
                } else {
                    Toast.makeText(exams.this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(exams.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            final List<Exams> examses = db.getExam();
            final List<obj> exams = new ArrayList<obj>();
            for (Exams exams1 : examses) {
                exams.add(new obj(exams1.getGenderName(), exams1.getExamDate(), exams1.getExamTypeName(), exams1.getTutorial()));
            }
            if (examses.isEmpty()) {
                android.support.v4.app.FragmentManager fm = exams.this.getSupportFragmentManager();

                Toast.makeText(exams.this, "لا يوجد امتحانات", Toast.LENGTH_LONG).show();

                com.applikable.Schools.Exam.exams.this.finish();
            } else
                ((ListView) findViewById(R.id.listViewexams)).setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return exams.size();
                    }

                    @Override
                    public obj getItem(int position) {
                        return exams.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {


                        View v = getLayoutInflater().inflate(R.layout.examlayinf, parent, false);
                        ((TextView) v.findViewById(R.id.day)).setText(exams.get(position).day);
                        ((TextView) v.findViewById(R.id.date)).setText(exams.get(position).date);
                        ((TextView) v.findViewById(R.id.course)).setText(exams.get(position).course);
                        ((TextView) v.findViewById(R.id.matt)).setText(exams.get(position).mat);
                        v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));

                        return v;
                    }
                });
            mProgressDialog.dismiss();
        }
    }

}
