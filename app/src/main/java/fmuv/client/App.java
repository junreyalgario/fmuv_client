package fmuv.client;

import android.app.Application;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class App extends Application {

    private static App appInstance;
    public static final String BASE_URL = "http://192.168.42.96/api/";
    public static final String NAME = "fmuv_client";

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static App getInstance() {
        return appInstance;
    }

}
