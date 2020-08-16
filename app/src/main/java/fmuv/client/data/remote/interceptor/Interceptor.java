package fmuv.client.data.remote.interceptor;

import fmuv.client.data.repository.Repository;
import okhttp3.logging.HttpLoggingInterceptor;

public class Interceptor {

    public static HttpLoggingInterceptor logging() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static BasicAuth basicAuth(String username, String password) {
        return new BasicAuth(username, password, Repository.session().getCookie());
    }

    public static ReceiveCookie receiveCookie() {
        return new ReceiveCookie(Repository.session());
    }

    public static Authenticated authenticatedInterceptor() {
        return new Authenticated(Repository.session());
    }

}
