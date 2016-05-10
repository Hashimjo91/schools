package com.applikable.Schools.Bus;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;
import android.widget.Toast;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.SchoolParser;

/**
 * Created by Alhusban on 15/11/2015.
 */
public class BusPage extends FragmentActivity {
    public static FragmentManager fragmentManager;
    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(getString(R.string.BusTitle));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#6d19a2")));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitybusmain);
        setActionbar();


    }


}