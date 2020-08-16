package fmuv.client.ui.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public abstract class BaseViewModel extends ViewModel {

    // response status live data properties
    protected MutableLiveData<Integer> responseReady = new MutableLiveData<>();
    protected MutableLiveData<Throwable> requestFailed = new MutableLiveData<>();


    // response status live data getters
    public MutableLiveData<Integer> onResponseReady() { return responseReady; }
    public MutableLiveData<Throwable> onRequestFailed() { return requestFailed; }

}
