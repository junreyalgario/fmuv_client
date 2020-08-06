package fmuv.client.data;

import androidx.lifecycle.MutableLiveData;

public interface RemoteApi {

    // User login
    MutableLiveData<Boolean> auth(String username, String password);



}
