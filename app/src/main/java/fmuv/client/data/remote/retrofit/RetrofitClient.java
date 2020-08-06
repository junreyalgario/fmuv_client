package fmuv.client.data.remote.retrofit;

import java.util.concurrent.TimeUnit;

import fmuv.client.App;
import fmuv.client.data.remote.interceptor.Http;
import fmuv.client.data.remote.dto.ApiResponse;
import fmuv.client.data.remote.dto.Meta;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.4
 */

public class RetrofitClient {

    private static RetrofitClient instance;
    private static Retrofit retrofit;

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    // User login
    public Call<ApiResponse> authentication(String username, String password) {
        OkHttpClient.Builder httpClient = createClient();

        httpClient.interceptors().add(Http.basicAuthInterceptor(username, password));
        httpClient.interceptors().add(Http.receiveCookieInterceptor());

        Retrofit authClient = new Retrofit.Builder()
                .baseUrl(App.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return authClient.create(RestApi.class).auth();
    }

    // User logout
    public Call<Meta> logout() {
       return getRestApi().logout();
    }

    // Rest calls for authenticated user
    public RestApi getRestApi() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = createClient();
            httpClient.interceptors().add(Http.authenticatedInterceptor());

            retrofit = new Retrofit.Builder()
                    .baseUrl(App.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit.create(RestApi.class);
    }



    private OkHttpClient.Builder createClient() {
        OkHttpClient.Builder client =  new OkHttpClient.Builder()
                .connectTimeout(60 * 5, TimeUnit.SECONDS)
                .readTimeout(60 * 5, TimeUnit.SECONDS)
                .writeTimeout(60 * 5, TimeUnit.SECONDS);
        client.interceptors().add(Http.loggingInterceptor());

        return client;
    }

}
