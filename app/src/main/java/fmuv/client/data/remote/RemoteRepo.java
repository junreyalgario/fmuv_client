package fmuv.client.data.remote;

import androidx.lifecycle.MutableLiveData;
import fmuv.client.data.RemoteApi;
import fmuv.client.data.remote.dto.ApiResponse;
import fmuv.client.data.remote.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepo implements RemoteApi {

    RetrofitClient retrofitClient;

    private MutableLiveData<Boolean> isAuthSuccess = new MutableLiveData<>();

    RemoteRepo(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    @Override
    public MutableLiveData<Boolean> auth(String username, String password) {

        retrofitClient.authentication(username, password)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {

                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                    }
                });

        return isAuthSuccess;
    }
}
