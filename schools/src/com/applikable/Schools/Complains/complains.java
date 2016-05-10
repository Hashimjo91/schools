package com.applikable.Schools.Complains;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;


public class complains extends Activity {
    String email;
    String name;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complains);
        setActionbar();
    }

    public void send(View v) {
        email = ((EditText) findViewById(R.id.editText3)).getText().toString();
        name = ((EditText) findViewById(R.id.editText2)).getText().toString();
        desc = ((EditText) findViewById(R.id.editText)).getText().toString();
        if (Tools.isOnline(this)) {
            if (email != null && name != null && desc != null && email.contains("@")) {
                mAsync mAsync = new mAsync();
                mAsync.execute();
            }
        } else
            Toast.makeText(this, "لا يوجد اتصال في الانترنت", Toast.LENGTH_SHORT).show();
    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("الاقتراحات و الشكاوى");
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

    class mAsync extends AsyncTask<Void, Void, Void> {
        private ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(complains.this, ProgressDialog.THEME_HOLO_LIGHT);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String param = "PersonName=" + name + "&suggestion=" + desc + "&email=" + email;

            try {
                SchoolParser.postComplain(name, desc, email);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressDialog.dismiss();

            ((EditText) findViewById(R.id.editText3)).setText("");
            ((EditText) findViewById(R.id.editText2)).setText("");
            ((EditText) findViewById(R.id.editText)).setText("");


        }
    }

}
