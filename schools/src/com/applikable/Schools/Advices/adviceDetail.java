package com.applikable.Schools.Advices;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.applikable.Schools.Classes.Advices;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hashim on 23/03/2015.
 */
public class adviceDetail extends FragmentActivity {
    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("القيم التربوية ");
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.advicedetail);
        setActionbar();
        DataBase db = new DataBase(this);

        List<Advices> adviceses = new ArrayList<Advices>();
        adviceses = db.getAdvices();
        Collections.reverse(adviceses);

        String pos = getIntent().getExtras().getString("advice");
        int position = Integer.parseInt(pos);


        TextView tv = (TextView) findViewById(R.id.textView);
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);


        tv.setText(adviceses.get(position).getTitle());
        if (adviceses.get(position).getImg().isEmpty() || !adviceses.get(position).getImg().contains("jpeg"))
            Picasso.with(this).load(R.mipmap.logonew).into(iv);
        else
            Picasso.with(this).load(adviceses.get(position).getImg()).into(iv);
        tv2.setText(adviceses.get(position).getDescription());


    }
}