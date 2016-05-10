package com.applikable.Schools.Bus;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.applikable.Schools.Classes.Bus;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;
import com.applikable.Schools.Utilities.childList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alhusban on 06/12/2015.
 */
public class BusesList extends Activity {
    String Response;
    List<Bus> buses;
    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(R.string.BusTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor(getString(R.string.SchoolPrimaryColor))));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buseslist_activity);
        setActionbar();
               /*-----------------------------------ListView & Adapter Initialization----------------------------------------*/
        final ListView lv = (ListView) findViewById(R.id.lvBuses);

        final lvAdapter adapter = new lvAdapter<Bus>() {
            @Override
            public View getMyView(int i, View view, ViewGroup viewGroup) {
                View v = getLayoutInflater().inflate(R.layout.activity_bus, null, false);
                ((TextView)v.findViewById(R.id.bustext)).setText("Bus:" + i);
                return v;
            }
        };

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tools.setShared(BusesList.this, "bnID", ((Bus) adapter.getItem(i)).getnID());
                startActivity(new Intent(BusesList.this, BusPage.class));

            }
        });

        AsyncTask<Void, Void, Void> at = new AsyncTask<Void, Void, Void>() {
            public ProgressDialog mProgressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog = new ProgressDialog(BusesList.this, ProgressDialog.THEME_HOLO_LIGHT);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

            }

            String data = "";

            @Override
            protected Void doInBackground(Void... voids) {
                data = SchoolParser.BusLogin();
                if(data==null||data=="")
                {

                    return null;
                }
                buses = SchoolParser.GetBusList(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);
                mProgressDialog.dismiss();
                if(data==null||data=="")
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(BusesList.this);
                    builder.setTitle(getString(R.string.Error));


// Set up the input
                    final TextView v =new TextView(BusesList.this);
                    v.setPadding(16,16,16,16);
                    v.setText(getString(R.string.buslisterror));
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    builder.setView(v);

// Set up the buttons
                    builder.setPositiveButton(getString(R.string.Close), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            BusesList.this.finish();
                        }
                    });


                    builder.show();

                }else{
                Response = data;

                for(Bus item:buses)
                    adapter.AddItem(item);
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
            }}
        };
        at.execute();



        /*---------------------------------------------------------------------------*/


    }
   /*-----------------------------------Adapter Implementation----------------------------------------*/

    abstract class lvAdapter<T> extends BaseAdapter {
        List<T> list;

        public lvAdapter(List<T> list) {
            this.list = list;
        }

        public lvAdapter() {
            list = new ArrayList<T>();
        }

        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }

        public void AddItem(T item) {
            this.list.add(item);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public T getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            return getMyView(i, view, viewGroup);
        }

        public abstract View getMyView(int i, View view, ViewGroup viewGroup);
    }
}