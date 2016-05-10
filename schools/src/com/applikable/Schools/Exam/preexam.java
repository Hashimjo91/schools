package com.applikable.Schools.Exam;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.Tools;

/**
 * Created by Hashim on 11/03/2015.
 */
public class preexam extends Activity {
    int section = 1;
    int classID = 1;
    int Gender = 1;
    int ExamType = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presexam);


        setActionbar();


        final Spinner classspin = ((Spinner) findViewById(R.id.classnameSpinner));

        final Spinner secSpin = ((Spinner) findViewById(R.id.SectionNameSpinner));
        final Spinner exSpin = ((Spinner) findViewById(R.id.examTypeSpinner));

        final Spinner genspin = ((Spinner) findViewById(R.id.GenderSpinner));

        int schedC = Integer.parseInt(Tools.getShared(this, "examC", "0"));
        int schedS = Integer.parseInt(Tools.getShared(this, "examS", "0"));
        int schedG = Integer.parseInt(Tools.getShared(this, "examG", "0"));
        int schedT = Integer.parseInt(Tools.getShared(this, "examT", "0"));

        classspin.setSelection(schedC);
        secSpin.setSelection(schedS);
        genspin.setSelection(schedG);
        exSpin.setSelection(schedT);

        ((Button) findViewById(R.id.Confirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i2 = genspin.getSelectedItemPosition();
                int i1 = secSpin.getSelectedItemPosition();
                int i = classspin.getSelectedItemPosition();
                int i3 = exSpin.getSelectedItemPosition();

                Tools.setShared(preexam.this, "examC", i + "");
                Tools.setShared(preexam.this, "examS", i1 + "");
                Tools.setShared(preexam.this, "examG", i2 + "");
                Tools.setShared(preexam.this, "examT", i3 + "");

                onItemClick1(i1);
                onItemClick2(i2);
                onItemClick(i);
                onItemClick3(i3);
                if (classID == -1 || section == -1 || Gender == -1 || ExamType == -1)
                    return;
                startActivity(new Intent(preexam.this, exams.class).putExtra("IDs", classID + "_" + section + "_" + Gender + "_" + ExamType));
            }
        });


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

    public void onItemClick1(int i) {
        switch (i) {

            case 0:
                section = 79;
                break;
            case 1:
                section = 80;
                break;
            case 2:
                section = 81;
                break;
            case 3:
                section = 94;
                break;
            case 4:
                section = 95;
                break;
            default:
                section = 79;
        }

    }

    public void onItemClick3(int i) {
        switch (i) {
            case 0:
                ExamType = 96;
                break;
            case 1:
                ExamType = 97;

                break;
            case 2:
                ExamType = 98;

                break;
            case 3:
                ExamType = 99;

                break;


        }
    }

    public void onItemClick2(int i) {
        switch (i) {
            case 0:
                Gender = 1;
                break;
            case 1:
                Gender = 2;
                break;
            case 2:
                Gender = 102;
                break;
        }
    }

    public void onItemClick(int i) {

        switch (i) {
            case 0:
                classID = 82;
                break;
            case 1:
                classID = 83;

                break;
            case 2:
                classID = 84;

                break;
            case 3:
                classID = 85;

                break;
            case 4:
                classID = 86;

                break;
            case 5:
                classID = 87;

                break;
            case 6:
                classID = 88;

                break;
            case 7:
                classID = 89;

                break;
            case 8:
                classID = 90;

                break;
            case 9:

                classID = 91;

                break;
            case 10:
                classID = 92;

                break;
            default:
                classID = 85;
        }


    }

}