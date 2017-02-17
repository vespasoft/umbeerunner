package com.umbeerunner.android.umbeerunner.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by luisvespa on 11/16/16.
 */
public class SessionPreferences {

    // LogCat tag
    private static String TAG = SessionPreferences.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "toolvendor_pref";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_UID = "uidLoggedIn";
    private static final String KEY_IS_UNAME = "unameLoggedIn";
    private static final String KEY_IS_UADMIN = "uadminLoggedIn";

    public SessionPreferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String uid, String uname, String isAdmin) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString(KEY_IS_UID, uid);
        editor.putString(KEY_IS_UNAME, uname);
        editor.putString(KEY_IS_UADMIN, isAdmin);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }



    public void closeSession() {

        editor.putBoolean(KEY_IS_LOGGEDIN, false);
        editor.putString(KEY_IS_UID, "");
        editor.putString(KEY_IS_UNAME, "");
        editor.putString(KEY_IS_UADMIN, "");

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getUID(){
        return pref.getString(KEY_IS_UID, "0");
    }

    public String getUNAME(){
        return pref.getString(KEY_IS_UNAME, "");
    }

    public String getAdmin(){
        return pref.getString(KEY_IS_UADMIN, "");
    }

}
