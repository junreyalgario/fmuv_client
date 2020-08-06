package fmuv.client.ui.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import fmuv.client.data.Repository;
import fmuv.client.data.remote.dto.User;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public abstract class BaseViewModel extends ViewModel {

    // LiveData properties
    protected MutableLiveData<Integer> responseReady = new MutableLiveData<>();
    protected MutableLiveData<Boolean> requestFailed = new MutableLiveData<>();

    /**
     * LiveData getters
     */

    public MutableLiveData<Integer> onResponseReady() { return responseReady; }
    public MutableLiveData<Boolean> onRequestFailed() { return requestFailed; }

    public User getUser() {
        return Repository.session().getUserdata();
    }

}
