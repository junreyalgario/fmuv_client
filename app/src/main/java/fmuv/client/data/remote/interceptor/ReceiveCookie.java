package fmuv.client.data.remote.interceptor;

import java.io.IOException;
import fmuv.client.domain.repository.SessionRepository;
import okhttp3.Response;

public class ReceiveCookie implements okhttp3.Interceptor {

    private SessionRepository sessionRepository;

    public ReceiveCookie(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (!response.headers("Set-Cookie").isEmpty()) {
            sessionRepository.setCookie(response.header("Set-Cookie"));
        }
        return response;
    }
}
