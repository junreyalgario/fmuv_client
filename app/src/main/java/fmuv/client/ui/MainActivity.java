package fmuv.client.ui;

import androidx.appcompat.app.AppCompatActivity;
import fmuv.client.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Handler loginHandler = new Handler();
    private Runnable loginRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginRunnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        loginHandler.postDelayed(loginRunnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginHandler.removeCallbacks(loginRunnable);
    }
}
