package fmuv.client.data.repository;

import android.util.Log;

import fmuv.client.data.mapper.DTOModel;
import fmuv.client.data.remote.retrofit.RetrofitService;
import fmuv.client.data.remote.dto.HttpResponse;
import fmuv.client.data.remote.dto.Meta;
import fmuv.client.domain.model.UserModel;
import fmuv.client.domain.repository.AuthRepository;
import fmuv.client.domain.repository.SessionRepository;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.8
 */

public class AuthRepositoryImpl implements AuthRepository {

    private RetrofitService retrofitService;
    private SessionRepository sessionRepository;
    private DTOModel dtoModel;

    public AuthRepositoryImpl(RetrofitService retrofitService, SessionRepository sessionRepository, DTOModel dtoModel) {
        this.retrofitService = retrofitService;
        this.sessionRepository = sessionRepository;
        this.dtoModel = dtoModel;
    }

    @Override
    public Observable<Integer> login(String username, String password) {
        return retrofitService.basicAuth(username, password)
                .map(new Function<HttpResponse, Integer>() {
                    @Override
                    public Integer apply(HttpResponse httpResponse) throws Throwable {
                        if (httpResponse.getMeta().getCode() == 200) {
                            sessionRepository.setUser(
                                    dtoModel.getUserDtoMapper()
                                            .transform(httpResponse.getData().getUser())
                            );
                            sessionRepository.setToken(httpResponse.getData().getUser().getToken());
                            sessionRepository.setLogoutStatus(false);
                        }
                        return httpResponse.getMeta().getCode();
                    }
                });
    }

    @Override
    public Observable<Integer> register(UserModel userModel) {
        return retrofitService.registration(userModel)
                .map(new Function<HttpResponse, Integer>() {
                    @Override
                    public Integer apply(HttpResponse httpResponse) throws Throwable {
                        Log.d("ddd", "error: "+ httpResponse.getMeta().getErrorMessages().get(0));
                        return httpResponse.getMeta().getCode();
                    }
                });
    }

    @Override
    public Observable<Integer> verifyEmail() {
        return null;
    }

    @Override
    public Observable<Integer> forgotPassword() {
        return retrofitService.authApi()
                .forgotPassword()
                .map(new Function<Meta, Integer>() {
                    @Override
                    public Integer apply(Meta meta) throws Throwable {
                        return meta.getCode();
                    }
                });
    }

}
