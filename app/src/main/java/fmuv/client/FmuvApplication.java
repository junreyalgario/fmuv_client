package fmuv.client;

import android.app.Application;
import android.util.Log;

public class FmuvApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("ddd", "Application started");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
