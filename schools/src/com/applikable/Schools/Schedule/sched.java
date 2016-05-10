package com.applikable.Schools.Schedule;

import android.app.ActionBar;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.applikable.Schools.NewsAndActivities.ClickedNews;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;


public class sched extends FragmentActivity {
    TabHost th;
    LocalActivityManager mLocalActivityManager;
    String[] ids;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sched);

        ids = this.getIntent().getExtras().getString("IDs").split("_");
        Button z = (Button) findViewById(R.id.genInfo);
        Button SchedPlan = (Button) findViewById(R.id.StudyPlan);


        z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sched.this, ClickedNews.class).putExtra("news", "2"));
            }
        });

        SchedPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sched.this, ClickedNews.class).putExtra("news", "1"));
            }
        });
        setActionbar();
        mAsyncTask mAsyncTask = new mAsyncTask();

        if (Tools.isOnline(this))
            mAsyncTask.execute();
        else
            Toast.makeText(this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();


        th = ((TabHost) findViewById(R.id.mytabhostSched));

        mLocalActivityManager = new LocalActivityManager(this, false);

        mLocalActivityManager.dispatchCreate(savedInstanceState);


        th.setup(mLocalActivityManager);


    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("الجدول");
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

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

    class mAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(sched.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            DataBase db = new DataBase(sched.this);
            db.delete("Sessions");
            db.delete("Note");
           SchoolParser.getshced(sched.this, ids[0], ids[1], ids[2]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            Toast.makeText(sched.this, "Done", Toast.LENGTH_SHORT).show();

            mProgressDialog.dismiss();

            TabHost.TabSpec tab1 = th.newTabSpec("احد");
            TabHost.TabSpec tab2 = th.newTabSpec("اثنين");
            TabHost.TabSpec tab3 = th.newTabSpec("ثلاثاء");
            TabHost.TabSpec tab4 = th.newTabSpec("اربعاء");
            TabHost.TabSpec tab5 = th.newTabSpec("خميس");

            tab1.setIndicator("احد");
            tab2.setIndicator("اثنين");
            tab3.setIndicator("ثلاثاء");
            tab4.setIndicator("اربعاء");
            tab5.setIndicator("خميس");

            tab1.setContent(new Intent(sched.this, course.class).putExtra("Day", 1));
            tab2.setContent(new Intent(sched.this, course.class).putExtra("Day", 2));
            tab3.setContent(new Intent(sched.this, course.class).putExtra("Day", 3));
            tab4.setContent(new Intent(sched.this, course.class).putExtra("Day", 4));
            tab5.setContent(new Intent(sched.this, course.class).putExtra("Day", 5));

            th.addTab(tab1);
            th.addTab(tab2);
            th.addTab(tab3);
            th.addTab(tab4);
            th.addTab(tab5);

            th.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#6d19a2"));
            th.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab1);
            th.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab1);
            th.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab1);
            th.getTabWidget().getChildAt(4).setBackgroundResource(R.drawable.tab1);
            for (int i = 0; i < th.getTabWidget().getChildCount(); i++) {
                TextView tv = (TextView) th.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(12);
            }
            for (int i = 0; i < th.getTabWidget().getTabCount(); i++) {
                th.getTabWidget().getChildAt(i).getLayoutParams().height = 90;
            }
            th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String s) {
                    int selected = th.getCurrentTab();
                    th.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab1);
                    th.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab1);
                    th.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.tab1);
                    th.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.tab1);
                    th.getTabWidget().getChildAt(4).setBackgroundResource(R.drawable.tab1);


                    th.getTabWidget().getChildAt(selected).setBackgroundColor(Color.parseColor("#6d19a2"));


                }
            });
        }
    }

}
