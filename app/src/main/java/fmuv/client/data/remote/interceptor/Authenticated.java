package fmuv.client.data.remote.interceptor;

import java.io.IOException;
import fmuv.client.domain.repository.SessionRepository;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.4
 */

public class Authenticated implements Interceptor {

    private SessionRepository sessionRepository;

    public Authenticated(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", sessionRepository.getCookie());
        builder.addHeader("Bearer", sessionRepository.getToken());

        return chain.proceed(builder.build());
    }
}
