package sk.stuba.fei.ikt.iktclient.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

/**
 * Storage manager is responsible for saving user token and other properties in shared memory.
 */
public class StorageManager {

    private static final String TOKEN = "TOKEN";
    private static final String PREFS = "PREFS";
    private static StorageManager instance;

    private StorageManager() {

    }

    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    public void setToken(Activity activity, String token) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    @Nullable
    public String getToken(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sharedPref.getString(TOKEN, null);
    }
}
