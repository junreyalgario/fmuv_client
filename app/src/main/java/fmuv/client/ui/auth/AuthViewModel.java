package fmuv.client.ui.auth;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import fmuv.client.data.Repository;
import fmuv.client.data.remote.dto.ApiResponse;
import fmuv.client.data.remote.dto.Data;
import fmuv.client.ui.base.BaseViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class AuthViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> isAuthSuccess = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsAuthSuccess() {
        return isAuthSuccess;
    }

    public void authenticate(String username, String password) {
        Repository.httpService()
                .authentication(username, password)
                .enqueue(new AuthCallback());
    }

    private class AuthCallback implements Callback<ApiResponse> {

        @Override
        public void onResponse(Call<ApiResponse> call, retrofit2.Response<ApiResponse> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    Log.d("ddd", "SETTING TOKEN: "+ response.body().getData().getUser().getToken());
                    Repository.session()
                            .setToken(response.body().getData().getUser().getToken())
                            .setUserdata(response.body().getData().getUser());
                }
            }
            responseReady.setValue(response.code());
        }

        @Override
        public void onFailure(Call<ApiResponse> call, Throwable t) {
            requestFailed.setValue(true);
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public void sample() {
        Repository.httpService()
                .getRestApi()
                .index()
                .enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {

                        if(response.isSuccessful()) {

                            Log.d("ddd", "DATA: "+ response.body().getUser().getEmail());

                        }

                        responseReady.setValue(response.code());
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        requestFailed.setValue(true);
                    }
                });

    }

}

