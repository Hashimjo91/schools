package com.applikable.Schools.NewsAndActivities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;
import com.applikable.Schools.R;

/**
 * Created by Hashim on 07/03/2015.
 */
public class NewsandAnnouncement extends Activity {
    TabHost th;
    LocalActivityManager mLocalActivityManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsandannouncement);
        setActionbar();

        th = ((TabHost) findViewById(R.id.mytabhostSched));

        mLocalActivityManager = new LocalActivityManager(this, false);

        mLocalActivityManager.dispatchCreate(savedInstanceState);


        th.setup(mLocalActivityManager);


        TabHost.TabSpec tab1 = th.newTabSpec("اخبار");
        TabHost.TabSpec tab2 = th.newTabSpec("نشاطات");


        tab1.setIndicator("اخبار");
        tab2.setIndicator("نشاطات");

        tab1.setContent(new Intent(this, news.class));
        tab2.setContent(new Intent(this, activity.class));

        th.addTab(tab1);
        th.addTab(tab2);
        th.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab1);
        th.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab1);

        for (int i = 0; i < th.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) th.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(15);
        }
        for (int i = 0; i < th.getTabWidget().getTabCount(); i++) {
            th.getTabWidget().getChildAt(i).getLayoutParams().height = 100;
        }

        th.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int selected = th.getCurrentTab();
                th.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.tab1);
                th.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.tab1);


                th.getTabWidget().getChildAt(selected).setBackgroundColor(Color.parseColor("#6d19a2"));


            }
        });

    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("الاخبار و النشاطات");
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

}