package fmuv.client.ui.auth;

import android.util.Log;
import fmuv.client.data.repository.Repository;
import fmuv.client.domain.interactor.auth.BasicAuthUseCase;
import fmuv.client.ui.base.BaseViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.8.8
 */

public class AuthViewModel extends BaseViewModel {

    private BasicAuthUseCase basicAuth;

    public AuthViewModel() {
        basicAuth = new BasicAuthUseCase(Repository.auth());
    }

    public void authenticate(String username, String password) {
        basicAuth.login(username, password).execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(@NonNull  Integer integer) {
                responseReady.setValue(integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                requestFailed.setValue(e);
            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        basicAuth.dispose();
    }
}

