package fmuv.client.data.remote.interceptor;

import okhttp3.logging.HttpLoggingInterceptor;

public class Http {

    public static HttpLoggingInterceptor loggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static BasicAuth basicAuthInterceptor(String username, String password) {
        return new BasicAuth(username, password);
    }

    public static ReceiveCookie receiveCookieInterceptor() {
        return new ReceiveCookie();
    }

    public static AuthenticatedUser authenticatedInterceptor() {
        return new AuthenticatedUser();
    }

}
