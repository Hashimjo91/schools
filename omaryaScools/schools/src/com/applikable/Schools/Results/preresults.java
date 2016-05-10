package com.applikable.Schools.Results;

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
import com.applikable.Schools.Classes.Advices;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.DataBase;

import java.util.List;

/**
 * Created by Hashim on 10/03/2015.
 */
public class preresults extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preresult);
        setActionbar();


        String[] names;
        final DataBase db = new DataBase(this);
        final List<Advices> adviceses = db.getSTUD();
        String[] nameses = {"1111", "2222", "3333", "44444", "55555"};

        if (!adviceses.isEmpty()) {
            for (Advices advices : adviceses) {
                Log.e("SQL :-", advices.getId() + "//" + advices.getDescription() + "//" + advices.getTitle());
            }
            names = new String[adviceses.size()];
            int i = 0;
            for (Advices advice : adviceses) {
                names[i] = advice.getId();
                i++;
            }

            ((AutoCompleteTextView) findViewById(R.id.editText1)).setThreshold(0);
            ((AutoCompleteTextView) findViewById(R.id.editText1)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names));
            ((AutoCompleteTextView) findViewById(R.id.editText1)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int toRemember = Integer.parseInt(adviceses.get(i).getDescription());
                    if (toRemember == 1)
                        ((EditText) findViewById(R.id.editText)).setText(adviceses.get(i).getTitle());
                }
            });
        } else
            Log.e("SQL :-", "Empty");


        ((Button) findViewById(R.id.StudyPlan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = ((Spinner) findViewById(R.id.spinner2)).getSelectedItemPosition();
                if (i == 0) {

                    String Uname = ((AutoCompleteTextView) findViewById(R.id.editText1)).getText().toString();
                    String pass = ((EditText) findViewById(R.id.editText)).getText().toString();
                    if (((CheckBox) findViewById(R.id.checkBox)).isChecked())
                        db.insertStud(Uname, pass, "1");
                    else
                        db.insertStud(Uname, pass, "0");

                    if (Uname.isEmpty() || pass.isEmpty())
                        return;
                    startActivity(new Intent(preresults.this, results.class).putExtra("ids", Uname + "_" + pass + "_" + "first"));
                } else if (i == 1) {

                    String Uname = ((AutoCompleteTextView) findViewById(R.id.editText1)).getText().toString();
                    String pass = ((EditText) findViewById(R.id.editText1)).getText().toString();
                    if (((CheckBox) findViewById(R.id.checkBox)).isChecked())
                        db.insertStud(Uname, pass, "1");
                    else
                        db.insertStud(Uname, pass, "0");
                    if (Uname.isEmpty() || pass.isEmpty())
                        return;
                    startActivity(new Intent(preresults.this, results.class).putExtra("ids", Uname + "_" + pass + "_" + "sec"));
                }
            }
        });

    }

    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle("نتائج المواد");
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