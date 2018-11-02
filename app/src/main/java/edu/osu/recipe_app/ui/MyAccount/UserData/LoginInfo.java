package edu.osu.recipe_app.ui.MyAccount.UserData;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class LoginInfo extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";

    public String getPrefName() {
        return PREF_NAME;
    }
    public String getRememberKeyPref() {
        return PREF_NAME;
    }
    public String getEmailPref() {
        return PREF_NAME;
    }
    public String getPasswordPref() {
        return PREF_NAME;
    }

}