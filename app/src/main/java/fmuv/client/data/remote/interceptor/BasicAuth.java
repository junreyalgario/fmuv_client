package fmuv.client.data.remote.interceptor;

import java.io.IOException;

import fmuv.client.data.Repository;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuth implements Interceptor {

    private String credentials;

    public BasicAuth(String username, String password) {
        credentials = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        String cookie = Repository.session().getCookie();

        if (cookie != null) {
            builder.addHeader("Cookie", Repository.session().getCookie());
        }
        builder.addHeader("Authorization", credentials);

        return chain.proceed(builder.build());
    }
}
