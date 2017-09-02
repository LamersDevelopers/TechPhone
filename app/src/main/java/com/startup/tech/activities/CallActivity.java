package com.startup.tech.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.startup.tech.Object.CallObject;
import com.startup.tech.R;

import java.util.Calendar;

public class CallActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.call_activity);
        EditText numberCall = (EditText) findViewById(R.id.numberCall);
        EditText dateCall = (EditText) findViewById(R.id.dateCall);
        EditText techCall = (EditText) findViewById(R.id.techCall);

        TextView timeWork = (TextView) findViewById(R.id.timeWorkTitle);

        RadioGroup radioGroupIsContinued = (RadioGroup) findViewById(R.id.radioGroupIsContinued);
        RadioButton conYesRadioButton = (RadioButton) findViewById(R.id.conYesRadioButton);
        RadioButton conNoRadioButton = (RadioButton) findViewById(R.id.conNoRadioButton);

        EditText clientCall = (EditText) findViewById(R.id.clientCall);

        Intent intent = getIntent();
        CallObject callObject = intent.getParcelableExtra("call");
        Log.d("test ",callObject.toString());

        numberCall.setText(callObject.getCode());
        numberCall.setEnabled(false);
        numberCall.setTextColor(Color.BLACK);

        dateCall.setText(callObject.getCallDateTime());
        dateCall.setEnabled(false);
        dateCall.setTextColor(Color.BLACK);

        techCall.setText(callObject.getUserCode());
        techCall.setEnabled(false);
        techCall.setTextColor(Color.BLACK);

        radioGroupIsContinued.setClickable(false);
        conNoRadioButton.setClickable(false);
        conYesRadioButton.setClickable(false);
        if(callObject.getIsContinue().equals("0")){
            conNoRadioButton.setChecked(true);
        }else{
            conYesRadioButton.setChecked(true);
        }

        clientCall.setText((callObject.getCustName()));
        clientCall.setEnabled(false);
        clientCall.setTextColor(Color.BLACK);

        final Dialog dialog = new Dialog(CallActivity.this);
        dialog.setContentView(R.layout.custom_time);
        dialog.setTitle("Time Work");

        final TimePicker startWork, stopWork, timeTrip;
        startWork = (TimePicker)dialog.findViewById(R.id.timePickerStart);
        stopWork = (TimePicker)dialog.findViewById(R.id.timePickerStop);
        timeTrip = (TimePicker)dialog.findViewById(R.id.timePickerTrip);

        Button okTime = (Button)dialog.findViewById(R.id.ok_time);
        Button cancelTime = (Button)dialog.findViewById(R.id.cancel_time);

        okTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "start "+((Calendar)startWork.getTag()).getTime().toString()+
                        "stop "+((Calendar)stopWork.getTag()).getTime().toString()+
                        "trip"+((Calendar)timeTrip.getTag()).getTime().toString());
            }
        });

        startWork.setIs24HourView(true);
        stopWork.setIs24HourView(true);
        timeTrip.setIs24HourView(true);


        TimePicker.OnTimeChangedListener timeChangeListener  = new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar now = Calendar.getInstance();
                   now.set(Calendar.MINUTE, minute);
                    now.set(Calendar.HOUR, hourOfDay);
                view.setTag(now);
            }
        };

        startWork.setOnTimeChangedListener(timeChangeListener);
        stopWork.setOnTimeChangedListener(timeChangeListener);
        timeTrip.setOnTimeChangedListener(timeChangeListener);

        timeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }
}
