package com.applikable.Schools.Results;

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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.applikable.Schools.Classes.Marks;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;
import com.applikable.Schools.Utilities.UniDialog;

import java.util.List;


public class results extends FragmentActivity {

    String[] ids;

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("نتائج المواد");
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ids = getIntent().getExtras().getString("ids").split("_");
        mAsync mAsync = new mAsync();
        if (Tools.isOnline(results.this)) {

            mAsync.execute();

        } else {
            Toast.makeText(this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
        }
        setActionbar();
        final String[] a1 = {"عربي", "دين", "علوم", "English", "رياضيات", "رياضه", "اجتماعيات", "مهني", "فن"};
        final String[] a2 = {"80", "87", "79", "97", "75", "95", "78", "94", "97"};


    }

    class mAsync extends AsyncTask<Void, Void, Void> {
        private ProgressDialog mProgressDialog;

        @Override
        protected Void doInBackground(Void... voids) {
            SchoolParser.getMarks(results.this, ids[0], ids[1]);


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            DataBase db = new DataBase(results.this);
            db.delete("Marks");
            mProgressDialog = new ProgressDialog(results.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {


                ListView lv = (ListView) findViewById(R.id.marksLV);
                TextView tv1 = (TextView) findViewById(R.id.SName);
                TextView tv2 = (TextView) findViewById(R.id.Sclass);


                DataBase dataBase = new DataBase(results.this);
                final List<Marks> markses = dataBase.getMarks(ids[0], ids[2]);

                tv1.setText(markses.get(0).getSName());
                tv2.setText(markses.get(0).getSClass() + markses.get(0).getSSection());
                if (markses.isEmpty()) {
                    android.support.v4.app.FragmentManager fm = results.this.getSupportFragmentManager();

                    UniDialog u = new UniDialog("لا يوجد نتائج");
                    u.show(fm, "fragment_edit_name");
                } else
                    lv.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return markses.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return markses.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View v = getLayoutInflater().inflate(R.layout.marks_lv, parent, false);

                            TextView courseName = (TextView) v.findViewById(R.id.coursename);
                            TextView f = (TextView) v.findViewById(R.id.firstEx);
                            TextView s = (TextView) v.findViewById(R.id.second);
                            TextView t = (TextView) v.findViewById(R.id.third);
                            TextView fo = (TextView) v.findViewById(R.id.fourth);
                            TextView fi = (TextView) v.findViewById(R.id.finalex);


                            if (position % 2 == 0) {
                                v.setBackgroundColor(Color.parseColor("#7ad6ed"));
                                courseName.setText(markses.get(position).getCourse());
                                courseName.setTextColor(Color.BLACK);
                                f.setText(markses.get(position).getFirst());
                                f.setTextColor(Color.BLACK);
                                s.setText(markses.get(position).getSecond());
                                s.setTextColor(Color.BLACK);
                                t.setText(markses.get(position).getThird());
                                t.setTextColor(Color.BLACK);
                                fo.setText(markses.get(position).getFourth());
                                fo.setTextColor(Color.BLACK);
                                fi.setText(markses.get(position).getAll());
                                fi.setTextColor(Color.BLACK);
                            } else {
                                v.setBackgroundColor(Color.parseColor("#aedeea"));
                                courseName.setText(markses.get(position).getCourse());
                                courseName.setTextColor(Color.BLACK);
                                f.setText(markses.get(position).getFirst());
                                f.setTextColor(Color.BLACK);
                                s.setText(markses.get(position).getSecond());
                                s.setTextColor(Color.BLACK);
                                t.setText(markses.get(position).getThird());
                                t.setTextColor(Color.BLACK);
                                fo.setText(markses.get(position).getFourth());
                                fo.setTextColor(Color.BLACK);
                                fi.setText(markses.get(position).getAll());
                                fi.setTextColor(Color.BLACK);

                            }


                            return v;


                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(results.this, "اعد المحاولة في وقت لاحق", Toast.LENGTH_SHORT).show();
            }
            mProgressDialog.dismiss();
        }
    }

}
