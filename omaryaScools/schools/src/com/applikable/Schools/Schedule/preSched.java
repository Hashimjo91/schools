package com.applikable.Schools.Schedule;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.Tools;

/**
 * Created by Hashim on 09/03/2015.
 */
public class preSched extends Activity {
    int section = 1;
    int classID = 1;
    int Gender = 1;
    Spinner secSpin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presched);

        setActionbar();


        final Spinner classspin = ((Spinner) findViewById(R.id.classnameSpinner));


        secSpin = ((Spinner) findViewById(R.id.SectionNameSpinner));

        final Spinner genspin = ((Spinner) findViewById(R.id.GenderSpinner));

        int schedC = Integer.parseInt(Tools.getShared(this, "schedC", "0"));
        int schedS = Integer.parseInt(Tools.getShared(this, "schedS", "0"));
        int schedG = Integer.parseInt(Tools.getShared(this, "schedG", "0"));

        classspin.setSelection(schedC);
        secSpin.setSelection(schedS);
        genspin.setSelection(schedG);
        classspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        classID = 82;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 1:
                        classID = 83;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 2:
                        classID = 84;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 3:
                        classID = 85;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 4:
                        classID = 86;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 5:
                        classID = 87;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 6:
                        classID = 88;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 7:
                        classID = 89;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 8:
                        classID = 90;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 9:

                        classID = 91;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.section)));

                        break;
                    case 10:
                        classID = 92;
                        secSpin.setAdapter(new ArrayAdapter<String>(preSched.this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.sections)));
                        Log.e("awwal thanawe", "done");
                        break;
                    default:

                        classID = 85;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        secSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (classID == 92) {
                    switch (position) {

                        case 0:
                            section = 118;
                            break;
                        case 1:
                            section = 119;
                            break;
                        default:
                            break;
                    }
                } else
                    switch (position) {

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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        genspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ((Button) findViewById(R.id.Confirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i2 = genspin.getSelectedItemPosition();
                int i1 = secSpin.getSelectedItemPosition();
                int i = classspin.getSelectedItemPosition();

                Tools.setShared(preSched.this, "schedC", i + "");
                Tools.setShared(preSched.this, "schedS", i1 + "");
                Tools.setShared(preSched.this, "schedG", i2 + "");


                if (classID == -1 || section == -1 || Gender == -1) {
                    Toast.makeText(preSched.this, "الرجاء تعبئة كامل البيانات", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(preSched.this, sched.class).putExtra("IDs", classID + "_" + section + "_" + Gender));
            }
        });
    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("الجدول");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#5E6F13")));
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