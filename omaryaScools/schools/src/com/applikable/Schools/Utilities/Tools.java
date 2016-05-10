package com.applikable.Schools.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.applikable.Schools.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class Tools {

    private static String url;

    public static void startActivty(Context context, Class<?> theClass,
                                    boolean isFinish) {
        Intent intent = new Intent(context, theClass);
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    public static String getShared(Context context, String name, String defValue) {
        SharedPreferences data = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return data.getString(name, defValue);

    }

    public static void setShared(Context context, String name, String value) {
        SharedPreferences data = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = data.edit();
        edit.putString(name, value);
        edit.commit();
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    public static String getDate() {
        SimpleDateFormat sdate = new SimpleDateFormat("MM-dd-yyyy");
        String myDate = null;
        myDate = sdate.format(Calendar
                .getInstance().getTimeInMillis());
        return myDate;
    }

    //	static ViewGroup.LayoutParams params;
    public static void makeToast(final Context context, String txt, int icon) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        final View layout = inflater.inflate(R.layout.custom_toast, (RelativeLayout) ((Activity) context).findViewById(R.id.toast_layout_root), false);


        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(icon);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTextColor(Color.WHITE);
        text.setText(txt);

        Toast toast = new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);

        toast.setMargin(0, (float) 0.1);

        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

//		final View v=toast.getView();
//		params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,80);
//		params.width=20;
//		params.height=20;
//		toast.setView(v);
//		v.setLayoutParams(params);
//
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
////				params= layout.getLayoutParams();
//				v.setLayoutParams(params);
//				final int cWidth=params.width;
//				params.width=80;
//				((Activity) context).runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						params.width=cWidth/20;
//						v.setLayoutParams(params);
//					}
//				});
//
//				while (cWidth>params.width)
//				{
//					((Activity) context).runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							params.width+=(cWidth/20);
//							v.setLayoutParams(params);
//						}
//					});
//				}
//			}
//		}).start();
//	}
//


    public static Bitmap getBitmapFromURL(final String src) {
        try {
            return new AsyncTask<Bitmap, Bitmap, Bitmap>() {

                @Override
                protected Bitmap doInBackground(Bitmap... params) {
                    try {
                        URL url = new URL(src);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
                        return myBitmap;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }.execute().get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;


    }


    public static String get_network(Context context) {
        String network_type = "59";// maybe usb reverse tethering
        NetworkInfo active_network = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (active_network != null && active_network.isConnectedOrConnecting()) {
            if (active_network.getType() == ConnectivityManager.TYPE_WIFI) {
                network_type = "58";
            } else if (active_network.getType() == ConnectivityManager.TYPE_MOBILE) {
                network_type = "59";
            }
        }
        return network_type;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


}
