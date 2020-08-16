package fmuv.client.ui.auth;

import fmuv.client.data.repository.Repository;
import fmuv.client.domain.interactor.auth.RegisterUseCase;
import fmuv.client.ui.base.BaseViewModel;
import fmuv.client.ui.mapper.UserModelMapper;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class RegisterViewModel extends BaseViewModel {

    private RegisterUseCase register;
    private UserModelMapper userModelMapper;

    public RegisterViewModel() {
        register = new RegisterUseCase(Repository.auth());
        userModelMapper = new UserModelMapper();
    }

    public void register(RegistrationForm registrationForm) {

        register.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(@NonNull Integer integer) {
                responseReady.setValue(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                requestFailed.setValue(e);
            }

            @Override
            public void onComplete() {
            }
        }, userModelMapper.transform(registrationForm));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        register.dispose();
    }
}
