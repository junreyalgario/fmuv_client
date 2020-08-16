package fmuv.client.domain.interactor.auth;

import fmuv.client.domain.interactor.base.UseCase;
import fmuv.client.domain.model.UserModel;
import fmuv.client.domain.repository.AuthRepository;
import io.reactivex.rxjava3.core.Observable;

public class RegisterUseCase extends UseCase<Integer, UserModel> {

    private AuthRepository authRepository;

    public RegisterUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable(UserModel userModel) {
        return authRepository.register(userModel);
    }
}
