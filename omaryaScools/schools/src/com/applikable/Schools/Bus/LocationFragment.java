package com.applikable.Schools.Bus;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.applikable.Schools.Classes.Bus;
import com.applikable.Schools.R;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alhusban on 15/11/2015.
 */
public class LocationFragment extends Fragment implements OnMapReadyCallback {

    private static View view;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */


    private static Double latitude, longitude;
    private ListView lv;
    LatLng latLng;

    private SupportMapFragment mSupportMapFragment;
    public String Response = "";
    String bnID;
    GoogleMap mMap;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*------------------------------------Inflating---------------------------------------*/
        try {

            bnID = Tools.getShared(getActivity(), "bnID","0");
        } catch (Exception e) {
            e.printStackTrace();
        }

        view = inflater.inflate(R.layout.activitybus, container);
        AsyncTask<Void, Void, Void> at = new AsyncTask<Void, Void, Void>() {
            public ProgressDialog mProgressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog = new ProgressDialog(LocationFragment.this.getActivity(), ProgressDialog.THEME_HOLO_LIGHT);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

            }

            String data = "";

            @Override
            protected Void doInBackground(Void... voids) {
                data = SchoolParser.BusLogin();
                latLng = SchoolParser.getLocationOfBus(bnID, data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);
                mProgressDialog.dismiss();
                Response = data;


                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .alpha(0.7f));
                mMap.setMyLocationEnabled(true);

                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                mMap.moveCamera(cameraUpdate);

            }
        };
        at.execute();



        /*----------------------------------Map Fragment-----------------------------------------*/
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(this);
        }

        mMap=mSupportMapFragment.getMap();
        return view;
    }
        /*-----------------------------------Ready CallBack----------------------------------------*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (googleMap != null) {
            mMap=googleMap;
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            mMap.setMyLocationEnabled(true);

            CameraPosition cameraPosition;
            if (latLng!=null) {
                cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15.0f).build();
            } else {

                cameraPosition = new CameraPosition.Builder().target(new LatLng(15.0, 20.1)).zoom(15.0f).build();
            }
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.moveCamera(cameraUpdate);

//            googleMap.clear();
//
//            for (int i = 0; i < 5; i++) {
//                googleMap.addMarker(new MarkerOptions()
//                        .position(new LatLng(latitude[i], longitude[i]))
//                        .title("Bus "+i)
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//                        .alpha(0.7f));
//            }

        }
    }
}