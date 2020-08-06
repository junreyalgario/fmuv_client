package fmuv.client.data.remote.retrofit;

import fmuv.client.data.remote.dto.ApiResponse;
import fmuv.client.data.remote.dto.Data;
import fmuv.client.data.remote.dto.Meta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    // Login
    @POST("passenger/auth")
    Call<ApiResponse> auth();

    // Logout
    @POST("logout")
    Call<Meta> logout();

    @GET("passenger")
    Call<Data> index();

}