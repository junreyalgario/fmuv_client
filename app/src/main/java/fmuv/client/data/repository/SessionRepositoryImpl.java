package fmuv.client.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import fmuv.client.data.Constant;
import fmuv.client.data.remote.dto.HttpResponse;
import fmuv.client.data.remote.retrofit.RetrofitService;
import fmuv.client.domain.model.UserModel;
import fmuv.client.domain.repository.SessionRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.3
 */

public class SessionRepositoryImpl implements SessionRepository {

    private SharedPreferences authPreferences;
    private SharedPreferences dataPreferences;
    private RetrofitService retrofitService;

    public SessionRepositoryImpl(Context context, RetrofitService retrofitService) {
        authPreferences = context.getSharedPreferences(Constant.AUTH_SESSION, 0);
        dataPreferences = context.getSharedPreferences(Constant.SESSION_DATA, 0);
        this.retrofitService = retrofitService;
    }

    // AUTH PREFERENCES

    @Override
    public Boolean setCookie(String cookie) {
        return authPreferences.edit()
                .putString("cookie", cookie)
                .commit();
    }

    @Override
    public String getCookie() {
        return authPreferences.getString("cookie", "");
    }

    @Override
    public Boolean setToken(String token) {
        return authPreferences.edit()
                .putString("token", token)
                .commit();
    }

    @Override
    public String getToken() {
        return authPreferences.getString("token", "");
    }

    @Override
    public Boolean clearAuthSession() {
        return setToken("");
    }

    // DATA PREFERENCES

    @Override
    public Boolean setUser(UserModel user) {
        return dataPreferences.edit().putString("user", new Gson().toJson(user)).commit();
    }

    @Override
    public UserModel getUser() {
        return new Gson().fromJson(dataPreferences.getString("user", ""), UserModel.class);
    }

    @Override
    public Boolean setData(String key, String value) {
        return dataPreferences.edit()
                .putString(key, value)
                .commit();
    }

    @Override
    public Boolean clearData() {
        return dataPreferences.edit().clear().commit();
    }

    @Override
    public Boolean setLogoutStatus(boolean status) {
        return authPreferences.edit().putBoolean("logout", status).commit();
    }

    @Override
    public Boolean getLogoutStatus(boolean status) {
        return authPreferences.getBoolean("logout", false);
    }

    @Override
    public Observable<Integer> logout() {
        return retrofitService.authApi().logout().map(new Function<HttpResponse, Integer>() {
            @Override
            public Integer apply(HttpResponse httpResponse) throws Throwable {
                return httpResponse.getMeta().getCode();
            }
        });
    }
}
