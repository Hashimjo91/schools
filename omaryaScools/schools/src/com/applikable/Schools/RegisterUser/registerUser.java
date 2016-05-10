package com.applikable.Schools.RegisterUser;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.applikable.Schools.R;
import com.applikable.Schools.Results.results;
import com.applikable.Schools.Utilities.SchoolParser;
import com.applikable.Schools.Utilities.Tools;
import com.applikable.Schools.Utilities.WakeLocker;
import com.applikable.Schools.Utilities.childList;
import com.google.android.gcm.GCMRegistrar;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import static com.applikable.Schools.Utilities.CommonUtilities.*;

/**
 * Created by Alhusban on 18/11/2015.
 */
public class registerUser extends Activity {


    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);
    int startDay = c.get(Calendar.DAY_OF_MONTH);
    public void setActionbar() {
        getActionBar().setIcon(R.mipmap.icon);
        ActionBar actionBar = getActionBar();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setTitle(getString(R.string.Register));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#5E6F13")));
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionbar();
        setContentView(R.layout.register_activity);
        String possibleEmail;
        (findViewById(R.id.ET_BD)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new StartDatePicker();
                dialogFragment.show(getFragmentManager(), "start_date_picker");
            }
        });
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(registerUser.this).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;
                ((EditText) findViewById(R.id.ET_Email)).setText(possibleEmail);
            }
        }
        final UserChildAdapter<childList> mAdapter = new UserChildAdapter<childList>() {
            @Override
            public View GetView(final int position, View view, ViewGroup viewGroup) {
                View v = getLayoutInflater().inflate(R.layout.child_list_item, null, false);

                ((EditText) v.findViewById(R.id.stName)).setText(getItem(position).getName());
                ((Spinner) v.findViewById(R.id.Sclass)).setSelection(getItem(position).getSClass());
                ((Spinner) v.findViewById(R.id.Sclass)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        getItem(position).setSClass(i);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                ((TextView) v.findViewById(R.id.del)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RemoveItemAt(position);
                    }
                });


                return v;
            }
        };
        ((ListView) findViewById(R.id.LVChild)).setAdapter(mAdapter);
        (findViewById(R.id.addChild)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(registerUser.this);
                builder.setTitle(getResources().getString(R.string.addChildTitle));

// Set up the input
                final View v = getLayoutInflater().inflate(R.layout.child_list_item, null, false);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                v.findViewById(R.id.del).setVisibility(View.GONE);
                builder.setView(v);

// Set up the buttons
                builder.setPositiveButton(getResources().getString(R.string.addChildAccept_btn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.AddItem(new childList(((EditText) v.findViewById(R.id.stName)).getText().toString(), ((Spinner) v.findViewById(R.id.Sclass)).getSelectedItemPosition()));
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.addChildCancel_btn), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        (findViewById(R.id.Save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fName = ((EditText) findViewById(R.id.ET_FirstName)).getText().toString();
                final String lName = ((EditText) findViewById(R.id.ET_LastName)).getText().toString();
                final String Emain = ((EditText) findViewById(R.id.ET_Email)).getText().toString();
                final String Bday = ((TextView) findViewById(R.id.ET_BD)).getText().toString();
                final String Gender = ((Spinner) findViewById(R.id.ET_Gender)).getSelectedItemPosition() + "";
                final String Operator = ((Spinner) findViewById(R.id.SOperator)).getSelectedItem().toString();
                final String PhoneNumber = ((EditText) findViewById(R.id.ET_PhoneNumber)).getText().toString();
                final List<childList> list = mAdapter.getChildList();

                AsyncTask<Void, Void, Void> mAsync = new AsyncTask<Void, Void, Void>() {
                    final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
                        @Override
                        public void onReceive(Context context, Intent intent) {
                            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
                            // Waking up mobile if it is sleeping
                            WakeLocker.acquire(getApplicationContext());

                            /**
                             * Take appropriate action on this message
                             * depending upon your app requirement
                             * For now i am just displaying it on the screen
                             * */
//                            StringBuilder lblMessage = new StringBuilder();
                            // Showing received message
//                            lblMessage.append(newMessage + "\n");
//                            Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
//                            Log.e("NewMessage:",newMessage);
                            // Releasing wake lock
                            WakeLocker.release();
                        }
                    };
                    private ProgressDialog mProgressDialog;
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        mProgressDialog = new ProgressDialog(registerUser.this, ProgressDialog.THEME_HOLO_LIGHT);
                        mProgressDialog.setMessage("Loading...");
                        mProgressDialog.setIndeterminate(false);
                        mProgressDialog.setCancelable(false);
                        mProgressDialog.show();
                    }
                    String regId;

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            GCMRegistrar.checkDevice(registerUser.this);

                            // Make sure the manifest was properly set - comment out this line
                            // while developing the app, then uncomment it when it's ready.
                            GCMRegistrar.checkManifest(registerUser.this);
                            registerReceiver(mHandleMessageReceiver, new IntentFilter(
                                    DISPLAY_MESSAGE_ACTION));
                            regId = GCMRegistrar.getRegistrationId(registerUser.this);

                            // Check if regid already presents
                            if (regId.equals("")) {
                                // Registration is not present, register now with GCM
                                GCMRegistrar.register(registerUser.this, SENDER_ID);
                            }
                            String possibleEmail = "";
                            regId = GCMRegistrar.getRegistrationId(registerUser.this);
                            Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
                            Account[] accounts = AccountManager.get(registerUser.this).getAccounts();
                            for (Account account : accounts) {
                                if (emailPattern.matcher(account.name).matches()) {
                                    possibleEmail = account.name;

                                }
                            }

                            Log.e("response", SchoolParser.registerUser(PhoneNumber, fName, lName, Emain, Bday, Gender, Operator, regId, list));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {

                        super.onPostExecute(aVoid);
                        mProgressDialog.dismiss();
                        Tools.setShared(registerUser.this, "purchased","ACTIVE");
                        Tools.makeToast(registerUser.this, getString(R.string.activation), R.mipmap.icon);
                        registerUser.this.finish();
                    }
                };
                mAsync.execute();


            }
        });


    }

    @SuppressLint("ValidFragment")
    class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            // Use the current date as the default date in the picker
            DatePickerDialog dialog = new DatePickerDialog(registerUser.this, this, startYear, startMonth, startDay);
            return dialog;

        }

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            // Do something with the date chosen by the user
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;

            ((TextView) findViewById(R.id.ET_BD)).setText(startDay + "/" + startMonth + "/" + startYear);

        }
    }
}