package com.startup.tech.Object;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by AlekseyG on 7/17/2017.
 */

public class Constants {

    public static SharedPreferences settings;
    public static SharedPreferences.Editor editor;

    public static SharedPreferences getPreference(Activity ctn) {
        if (settings == null) {
            settings = ctn.getPreferences(0);
            editor = settings.edit();
        }
        return settings;
    }

    String statusStr = "";

    public void getStatusCall(String stat) {

        switch (stat)

        {
            case "1":
                statusStr = "חדשה";
                break;
            case "2":
                statusStr = "ממתינה לבצוע";
                break;
            case "3":
                statusStr = "בביצוע";
                break;
            case "31":
                statusStr = "נקראה";
                break;
            case "32":
                statusStr = "בנסיעה";
                break;
            case "33":
                statusStr = "בעבודה";
                break;
            case "34":
                statusStr = "בדיווח";
                break;
            case "6":
                statusStr = "הסתיימה";
                break;
            case "8":
                statusStr = "בהקפאה";
                break;
            case "9":
                statusStr = "מבוטלת";
                break;
            default:
                break;
        }
    }
}
