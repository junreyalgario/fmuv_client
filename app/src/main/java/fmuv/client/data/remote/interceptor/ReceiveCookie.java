package fmuv.client.data.remote.interceptor;

import java.io.IOException;

import fmuv.client.data.Repository;
import okhttp3.Response;

public class ReceiveCookie implements okhttp3.Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()) {
            Repository.session()
                    .setCookie(response.header("Set-Cookie"));
        }
        return response;
    }
}
