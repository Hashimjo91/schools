package com.applikable.Schools;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.*;
import com.applikable.Schools.AboutUs.aboutus;
import com.applikable.Schools.Advices.advices;
import com.applikable.Schools.Bus.BusPage;
import com.applikable.Schools.Bus.BusesList;
import com.applikable.Schools.Complains.complains;
import com.applikable.Schools.Exam.preexam;
import com.applikable.Schools.NewsAndActivities.NewsandAnnouncement;
import com.applikable.Schools.RegisterUser.registerUser;
import com.applikable.Schools.Results.preresults;
import com.applikable.Schools.Schedule.preSched;
import com.applikable.Schools.Utilities.Tools;

import java.util.ArrayList;
import java.util.List;


public class mainpage extends Activity {
    boolean purchased = false;
    int back = 0;
    int i = 0;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (back == 1) {
            finish();
        } else {
            back = 1;
            Toast.makeText(this, "اضغط رجوع مرة اخرى لاغلاق البرنامج", Toast.LENGTH_SHORT).show();
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    back = 0;
                }
            }, 3000);


        }


    }
//
//        @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content
//        // view
//        return super.onPrepareOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    return true;
//    }
//
    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("الصفحة الرئيسية");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#5E6F13")));

//    actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        setActionbar();
        obj a = new obj("الجدول", R.mipmap.j);
        obj a1 = new obj("نتائج المواد", R.mipmap.c);
        obj a2 = new obj("الاخبار و النشاطات", R.mipmap.e);
        obj a4 = new obj("الاقتراحات و الشكاوى", R.mipmap.i);
        obj a5 = new obj("الباصات", R.mipmap.b);
        obj a6 = new obj("الامتحانات", R.mipmap.h);
        obj a7 = new obj("القيم التربوية", R.mipmap.advice);
        obj a8 = new obj("عن المدرسة", R.mipmap.aboutblack);


        String p = Tools.getShared(this, "purchased", "notACTIVE");
        active(p);
        (findViewById(R.id.activationText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(mainpage.this, registerUser.class));
            }
//                final EditText txtUrl = new EditText(mainpage.this);
//
//// Set the default text to a link of the Queen
//                txtUrl.setHint("ادخل الرمز هنا");
//
//                new AlertDialog.Builder(mainpage.this)
//                        .setTitle("التفعيل")
//                        .setMessage("ادخل رمز التفعيل")
//
//                        .setView(txtUrl)
//                        .setPositiveButton("تفعيل", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                String url = txtUrl.getText().toString();
//                                active(url);
//                            }
//                        })
//                        .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                            }
//                        })
//                        .show();
//            }
        });


//getActionBar().setBackgroundDrawable();

        final List<obj> imgs = new ArrayList<>();
        imgs.add(a);
        imgs.add(a1);
        imgs.add(a2);
        imgs.add(a6);
        imgs.add(a5);
        imgs.add(a4);
        imgs.add(a7);
        imgs.add(a8);
        ListAdapter ac = new BaseAdapter() {

            @Override
            public int getCount() {
                return imgs.size();
            }

            @Override
            public obj getItem(int position) {
                return imgs.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = getLayoutInflater().inflate(R.layout.gridviewitem, parent, false);

                LinearLayout lo = ((LinearLayout) v);
                lo.setOrientation(LinearLayout.VERTICAL);
                ImageView v1 = (ImageView) v.findViewById(R.id.imageView4);
                TextView tv = (TextView) v.findViewById(R.id.textView2);
//                v1.setLayoutParams(new ViewGroup.LayoutParams(200,200));
                v1.setImageResource(imgs.get(position).img);
                tv.setText(imgs.get(position).text);


                return lo;
            }
        };


        ((GridView) findViewById(R.id.gridView)).setAdapter(ac);
        ((GridView) findViewById(R.id.gridView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(purchased)
//                {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mainpage.this, preSched.class));
                        break;
                    case 1:
                        startActivity(new Intent(mainpage.this, preresults.class));
                        break;
                    case 2:
                        startActivity(new Intent(mainpage.this, NewsandAnnouncement.class));
                        break;
                    case 3:
                        startActivity(new Intent(mainpage.this, preexam.class));
                        break;
                    case 4:
                        startActivity(new Intent(mainpage.this, BusesList.class));
                        break;
                    case 5:
                        startActivity(new Intent(mainpage.this, complains.class));

                        break;
                    case 6:
                        startActivity(new Intent(mainpage.this, advices.class));
                        break;
                    case 7:
                        startActivity(new Intent(mainpage.this, aboutus.class));
                        break;


                }
//            }else
//                    switch (position) {
//
//                case 7:
//                    startActivity(new Intent(mainpage.this, aboutus.class));
//                    break;
//                case 2:
//                    startActivity(new Intent(mainpage.this, NewsandAnnouncement.class));
//                    break;
//                default:
//                    Toast.makeText(mainpage.this," يرجى مراجعة المدرسة للحصول على رمز التفعيل",Toast.LENGTH_SHORT).show();
//                    attention();
//                    break;
//            }
            }
        });
    }

    private void active(String code) {
        if (code.equals("ACTIVE")) {
            purchased = true;
            ((TextView) findViewById(R.id.activationText)).setVisibility(View.INVISIBLE);
            Tools.setShared(this, "purchased", code);
        }

    }

    public void attention() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (i = 0; i <= 5; i++) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (i % 2 == 0)

                                ((TextView) findViewById(R.id.activationText)).setTextColor(Color.RED);
                            else
                                ((TextView) findViewById(R.id.activationText)).setTextColor(Color.WHITE);


                        }
                    });

                    try {
                        Thread.sleep(300);
                    } catch (Exception e) {
                    }

                }


            }
        }).start();
    }

    class obj {
        public int img;
        public String text;

        public obj(String text, int img) {
            this.img = img;
            this.text = text;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String p = Tools.getShared(this, "purchased", "notACTIVE");
        active(p);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String p = Tools.getShared(this, "purchased", "notACTIVE");
        active(p);
    }
}
