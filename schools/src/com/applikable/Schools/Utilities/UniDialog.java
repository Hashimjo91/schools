package com.applikable.Schools.Utilities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.applikable.Schools.R;

@SuppressLint("ValidFragment")
public class UniDialog extends DialogFragment {
    String message;
    Button yes;
    TextView textView;

    //UniDIALOG USED TO SHOW ERROR AND MESSAGE
    public UniDialog(String message) {
        this.message = message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_uni, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(
                R.drawable.rounded_dialog);
        textView = (TextView) view.findViewById(R.id.text);
        textView.setText(message);
        yes = (Button) view.findViewById(R.id.btnyes);
        yes.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                getDialog().dismiss();
            }
        });
        return view;
    }
}
