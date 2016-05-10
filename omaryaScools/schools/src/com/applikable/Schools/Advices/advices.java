package com.applikable.Schools.Advices;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.applikable.Schools.Classes.Advices;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;
import com.applikable.Schools.Utilities.UniDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class advices extends FragmentActivity {
    List<Advices> adviceses = new ArrayList<Advices>();
    ListView lv;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout lo = new LinearLayout(this);
        setActionbar();
        lv = new ListView(this);
//        lv.setDividerHeight(50);
        lo.addView(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                startActivity(new Intent(advices.this, adviceDetail.class).putExtra("advice", "" + (i)));
            }
        });

        setContentView(lo);
        if (Tools.isOnline(this)) {
            mAsyncTask mAsyncTask = new mAsyncTask();
            mAsyncTask.execute();

        } else {
            Toast.makeText(this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("القيم التربوية");
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

    class mAsyncTask extends AsyncTask<Void, Void, Void> {
        DataBase db = new DataBase(advices.this);

        @Override
        protected Void doInBackground(Void... voids) {
            db.delete("Advices");
            String l = SchoolParser.getAdvices(advices.this);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(advices.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            adviceses = db.getAdvices();
            Collections.reverse(adviceses);
            if (adviceses.isEmpty()) {
                android.support.v4.app.FragmentManager fm = advices.this.getSupportFragmentManager();

                UniDialog u = new UniDialog("لا يوجد نصائح");
                u.show(fm, "fragment_edit_name");
            } else
                lv.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return adviceses.size();
                    }

                    @Override
                    public String getItem(int position) {
                        return adviceses.get(position).getTitle();
                    }

                    @Override
                    public long getItemId(int position) {
                        return 0;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View v = getLayoutInflater().inflate(R.layout.news_item, parent, false);

                        String currentImg = adviceses.get(position).getImg();
                        Log.e("img", currentImg);
                        if (currentImg.isEmpty() || !currentImg.contains("jpeg") || !currentImg.contains("digitaltouch"))
                            Picasso.with(advices.this).load(R.mipmap.logonew).into(((ImageView) v.findViewById(R.id.imageView1)));
                        else
                            Picasso.with(advices.this).load(currentImg).into(((ImageView) v.findViewById(R.id.imageView1)));
                        ((TextView) v.findViewById(R.id.textView1)).setText(adviceses.get(position).getTitle());

                        return v;
                    }
                });
            mProgressDialog.dismiss();

        }
    }
}
