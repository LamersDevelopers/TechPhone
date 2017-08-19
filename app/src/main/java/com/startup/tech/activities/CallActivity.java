package com.startup.tech.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.startup.tech.Object.CallObject;
import com.startup.tech.R;

public class CallActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.call_activity);
        EditText numberCall = (EditText) findViewById(R.id.numberCall);
        EditText dateCall = (EditText) findViewById(R.id.dateCall);
        EditText techCall = (EditText) findViewById(R.id.techCall);

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





    }
}
