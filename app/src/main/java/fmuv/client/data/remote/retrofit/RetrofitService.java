package fmuv.client.data.remote.retrofit;

import java.util.concurrent.TimeUnit;

import fmuv.client.data.Constant;
import fmuv.client.data.remote.retrofit.api.AuthApi;
import fmuv.client.data.remote.dto.HttpResponse;
import fmuv.client.data.remote.interceptor.Interceptor;
import fmuv.client.domain.model.UserModel;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.8
 */


public class RetrofitService {

    private static RetrofitService instance;
    private static Retrofit authenticatedRetrofit;

    public static RetrofitService create() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    // Http Client builder
    private OkHttpClient.Builder buildClient() {
        OkHttpClient.Builder httpClient =  new OkHttpClient.Builder()
                .connectTimeout(60 * 5, TimeUnit.SECONDS)
                .readTimeout(60 * 5, TimeUnit.SECONDS)
                .writeTimeout(60 * 5, TimeUnit.SECONDS);
        httpClient.interceptors().add(Interceptor.logging());

        return httpClient;
    }

    // Authenticated http client build
    private Retrofit authenticatedClient() {
        if (authenticatedRetrofit == null) {
            OkHttpClient.Builder httpClient = buildClient();
            httpClient.interceptors().add(Interceptor.authenticatedInterceptor());

            authenticatedRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return authenticatedRetrofit;
    }

    // Login
    public Observable<HttpResponse> basicAuth(String username, String password) {
        OkHttpClient.Builder httpClient = buildClient();
        httpClient.interceptors().add(Interceptor.basicAuth(username, password));
        httpClient.interceptors().add(Interceptor.receiveCookie());

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return mRetrofit.create(AuthApi.class).login();
    }

    // Create account
    public Observable<HttpResponse> registration(UserModel userModel) {
        OkHttpClient.Builder httpClient = buildClient();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("fname", userModel.getFName())
                .addFormDataPart("lname", userModel.getLName())
                .addFormDataPart("gender", userModel.getGender())
                .addFormDataPart("contact", userModel.getContact())
                .addFormDataPart("email", userModel.getEmail())
                .addFormDataPart("password", userModel.getPassword())
                .build();

        return mRetrofit.create(AuthApi.class).register(requestBody);
    }

    //
    public AuthApi authApi() {
        return authenticatedClient().create(AuthApi.class);
    }

}
