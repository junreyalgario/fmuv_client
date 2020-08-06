package fmuv.client.data.remote.interceptor;

import java.io.IOException;
import fmuv.client.data.Repository;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.4
 */

public class AuthenticatedUser implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", Repository.session().getCookie());
        builder.addHeader("Bearer", Repository.session().getToken());

        return chain.proceed(builder.build());
    }
}
