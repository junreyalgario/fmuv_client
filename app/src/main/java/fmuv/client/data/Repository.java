package fmuv.client.data;

import fmuv.client.App;
import fmuv.client.data.preferences.Session;
import fmuv.client.data.remote.retrofit.RetrofitClient;
import fmuv.client.data.preferences.SharedPref;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.3
 */

public class Repository {

    private static Repository instance;
    private RemoteApi remoteRepo;

    private Repository(){
        // Not publicly instantiable
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }







    /**
     * Rest api call
     */
    public static RetrofitClient httpService() {
        return RetrofitClient.getInstance();
    }

    /**
     * New Shared pref instance
     */
    public static SharedPref getPrefService() {
        return new SharedPref(App.getInstance(), App.NAME);
    }

    /**
     * Session service
     */
    public static Session session() {
        return new SharedPref(App.getInstance(), App.NAME).session();
    }
}
