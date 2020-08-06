package fmuv.client.ui;

import androidx.appcompat.app.AppCompatActivity;
import fmuv.client.R;
import fmuv.client.data.Repository;
import fmuv.client.ui.auth.AuthActivity;
import fmuv.client.ui.home.HomeActivity;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.Map;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class SplashActivity extends AppCompatActivity {

    private Handler loginHandler = new Handler();
    private Runnable loginRunnable;

    CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // splash();

        rxJava();
    }

    private void rxJava() {



        Observable<Task> taskObservable = Observable.fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Throwable {

                        Log.d("ddd", "Task: "+ task.getName() + " Thread: "+ Thread.currentThread().getName());

                        Thread.sleep(1000);

                        return task.isDone();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("ddd", "onSubscribe: "+ d.toString());
                disposables.add(d);
            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d("ddd", "onNext: "+ task.getName() + " Thread: "+ Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("ddd", "onError called: "+ e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("ddd", "onComplete called");
            }
        });

    }

    private void splash() {
        if (!Repository.session().getCookie().trim().equals("")) {
            loginRunnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
        }else{
            loginRunnable = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(intent);
                    finish();
                }
            };
        }
        loginHandler.postDelayed(loginRunnable, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //loginHandler.removeCallbacks(loginRunnable);
        disposables.dispose();
    }
}
