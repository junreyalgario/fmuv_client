package fmuv.client.data.remote.retrofit.api;

import fmuv.client.data.remote.dto.HttpResponse;
import fmuv.client.data.remote.dto.Meta;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("passenger/auth")
    Observable<HttpResponse> login();

    @POST("logout")
    Observable<HttpResponse> logout();

    @POST("passenger/register")
    Observable<HttpResponse> register(@Body RequestBody body);

    @POST("passenger/forgot-password")
    Observable<Meta> forgotPassword();

}
