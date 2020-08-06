package fmuv.client.ui.home;

import androidx.lifecycle.MutableLiveData;
import fmuv.client.data.Repository;
import fmuv.client.data.remote.dto.Meta;
import fmuv.client.ui.base.BaseViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isLogoutSuccess = new MutableLiveData<>();

    // Logout
    public void logout() {
        Repository.httpService()
                .logout()
                .enqueue(new Callback<Meta>() {
                    @Override
                    public void onResponse(Call<Meta> call, Response<Meta> response) {
                        if (!response.isSuccessful()) {
                            // Do something
                        }
                        Repository.session().clear();
                        isLogoutSuccess.setValue(true);
                    }

                    @Override
                    public void onFailure(Call<Meta> call, Throwable t) {
                        requestFailed.setValue(true);
                    }
                });
    }

    public MutableLiveData<Boolean> getIsLogoutSuccess() {
        return isLogoutSuccess;
    }
}
