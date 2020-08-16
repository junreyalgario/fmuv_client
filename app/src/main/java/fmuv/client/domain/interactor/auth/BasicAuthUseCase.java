package fmuv.client.domain.interactor.auth;

import fmuv.client.domain.repository.AuthRepository;
import fmuv.client.domain.interactor.base.UseCase;
import io.reactivex.rxjava3.core.Observable;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.9
 */

public class BasicAuthUseCase extends UseCase<Integer, Void> {

    private AuthRepository authRepository;
    private String username;
    private String password;

    public BasicAuthUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable(Void aVoid) {
        return authRepository.login(username, password);
    }


    public BasicAuthUseCase login(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }

}
