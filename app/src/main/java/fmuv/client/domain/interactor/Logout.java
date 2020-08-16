package fmuv.client.domain.interactor;

import androidx.lifecycle.MutableLiveData;
import fmuv.client.domain.repository.SessionRepository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Logout {

    private SessionRepository sessionRepository;

    public Logout(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void execute(MutableLiveData<Boolean> status) {
        sessionRepository.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        if (integer == 200) {
                            sessionRepository.setLogoutStatus(true);
                            status.postValue(true);
                        }else{
                            sessionRepository.setLogoutStatus(false);
                            status.postValue(false);
                        }
                        clearSession();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        sessionRepository.setLogoutStatus(false);
                        clearSession();
                        status.postValue(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void clearSession() {
        sessionRepository.clearAuthSession();
        sessionRepository.clearData();
    }

}
