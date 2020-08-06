package fmuv.client.data.preferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import fmuv.client.data.remote.dto.User;

public class Session {

    private SharedPreferences pref;

    Session(SharedPreferences pref) {
        this.pref = pref;
    }


    public Session setToken(String token) {
        pref.edit().putString("token", token).apply();
        return this;
    }

    public Session setUserdata(User user) {
        pref.edit().putString("user", new Gson().toJson(user)).apply();
        return this;
    }

    public String getToken() {
        return pref.getString("token", "");
    }

    public User getUserdata() {
        return new Gson().fromJson(pref.getString("user", ""), User.class);
    }

    public boolean setCookie(String cookie) {
        return pref.edit()
                .putString("cookie", cookie)
                .commit();
    }

    public String getCookie() {
        return pref.getString("cookie", "");
    }

    public boolean clear() {
        return pref.edit().clear().commit();
    }
}
