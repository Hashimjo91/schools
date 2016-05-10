package com.applikable.Schools.NewsAndActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.applikable.Schools.Classes.News;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;
import com.applikable.Schools.Utilities.UniDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class news extends FragmentActivity {
    ListView lv;
    String[] news = new String[]{"مدارس الإحتراف تعقد دورة (الحاجات النفسيه عند الطفل)", "انطلاق رحلة عمره", "ملتقى الأسرة الأول يوم السبت 16/11/2013"};
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout lo = new LinearLayout(this);

        lv = new ListView(this);
//setActionbar();

//        lv.setDividerHeight(50);
        lo.addView(lv);

        setContentView(lo);
        mAsyncTask mAsyncTask = new mAsyncTask();
        mAsyncTask.execute();


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(this, mainpage.class));
        finish();
    }

    class mAsyncTask extends AsyncTask<Void, Void, Void> {
        List<News> newses = new ArrayList<News>();
        DataBase db = new DataBase(news.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(news.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(news.this, ClickedNews.class).putExtra("news", newses.get(i).getDescription()));
                }
            });
            super.onPostExecute(aVoid);
            newses = db.getNews();
            if (!Tools.isOnline(news.this)) {
                Toast.makeText(news.this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
            } else if (newses.isEmpty()) {
                android.support.v4.app.FragmentManager fm = news.this.getSupportFragmentManager();

                UniDialog u = new UniDialog("لا يوجد اخبار");
                u.show(fm, "fragment_edit_name");
            } else
                lv.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return newses.size();
                    }

                    @Override
                    public String getItem(int position) {
                        return newses.get(position).getTitle();
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View v = getLayoutInflater().inflate(R.layout.news_item, parent, false);

                        ((TextView) v.findViewById(R.id.textView1)).setText(getItem(position));
                        if (!newses.get(position).getImg().equals(""))
                            Picasso.with(news.this).load(newses.get(position).getImg()).placeholder(R.mipmap.logonew).into(((ImageView) v.findViewById(R.id.imageView1)));
//                v.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,900));

                        return v;
                    }
                });

            mProgressDialog.dismiss();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.delete("NewsAnnouncement");
            String l = SchoolParser.getParsedNews(news.this);
            return null;
        }
    }
}