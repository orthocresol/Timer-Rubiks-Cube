package org.timerrubikscube.aatimer.nonactivityclass;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SessionManager (Context context){
        sharedPreferences = context.getSharedPreferences("App Key", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setEnableInspection(Boolean sw){
        editor.putBoolean("ENABLE_INSPECTION", sw);
        editor.commit();
    }

    public Boolean getEnableInspection() {
        return sharedPreferences.getBoolean("ENABLE_INSPECTION", false);
    }

    public void setEnableAutoLogout(Boolean sw){
        editor.putBoolean("ENABLE_AUTO_LOGOUT", sw);
        editor.commit();
    }
    public Boolean getEnableAutoLogout(){
        return sharedPreferences.getBoolean("ENABLE_AUTO_LOGOUT", false);
    }

    public void setScrambleButton(Boolean sw){
        editor.putBoolean("SHOW_SCRAMBLE_BUTTON", sw);
        editor.commit();
    }

    public Boolean getScrambleButton(){
        return sharedPreferences.getBoolean("SHOW_SCRAMBLE_BUTTON", true);
    }
}
