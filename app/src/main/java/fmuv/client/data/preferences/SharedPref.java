package fmuv.client.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.5
 */

public class SharedPref {

    private SharedPreferences sharedPreferences;

    public SharedPref(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(name, 0);
    }

    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    public Session session() {
        return new Session(sharedPreferences);
    }

}
