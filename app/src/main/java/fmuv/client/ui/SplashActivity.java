package fmuv.client.ui;

import androidx.appcompat.app.AppCompatActivity;
import fmuv.client.R;
import fmuv.client.data.repository.Repository;
import fmuv.client.ui.auth.AuthActivity;
import fmuv.client.ui.home.HomeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class SplashActivity extends AppCompatActivity {

    private Handler loginHandler = new Handler();
    private Runnable loginRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Repository.session().getToken().equals("")) {
            loginRunnable = () -> {
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            };
        }else{
            loginRunnable = () -> {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            };
        }
        loginHandler.postDelayed(loginRunnable, 500);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginHandler.removeCallbacks(loginRunnable);
    }
}
