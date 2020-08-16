package fmuv.client.ui.home;

import androidx.lifecycle.MutableLiveData;
import fmuv.client.data.repository.Repository;
import fmuv.client.domain.interactor.Logout;
import fmuv.client.ui.base.BaseViewModel;

public class HomeViewModel extends BaseViewModel {

    private Logout logout;

    private MutableLiveData<Boolean> isLogoutDone = new MutableLiveData<>();

    HomeViewModel() {
        logout = new Logout(Repository.session());
    }

    public MutableLiveData<Boolean> getIsLogoutDone() {
        return isLogoutDone;
    }

    // Logout
    public void logout() {
        logout.execute(isLogoutDone);
    }
}
