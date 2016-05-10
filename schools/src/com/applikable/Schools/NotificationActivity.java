package com.applikable.Schools;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Alhusban on 05/12/2015.
 */
public class NotificationActivity extends Activity {
    String link;
    String message;
    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(getString(R.string.newNotificationTitle));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#6d19a2")));

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setActionbar();
        link = GCMIntentService.Link;
        message = GCMIntentService.Massage;
        getIntent().getExtras().clear();
        ((TextView)findViewById(R.id.NotificationLink)).setText(Html.fromHtml("<u>"+link+"</u>"));
        (findViewById(R.id.NotificationLink)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = link;
                    if (url.startsWith("www")) {
                        url = "http://" + link;
                    }
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ((TextView)findViewById(R.id.NotificationText)).setText(message);





    }

    @Override
    public void onBackPressed() {
        NotificationActivity.this.finish();
        startActivity(new Intent(this, mainpage.class));
    }
}