package fmuv.client.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import fmuv.client.R;
import fmuv.client.databinding.ActivityLoginBinding;
import fmuv.client.view_model.LoginViewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding loginBinding;
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        LoginViewModel loginViewModel = new LoginViewModel();

        loginBinding.setLogin(loginViewModel);
        loginBinding.setHandler(this);

    }

    public void onClickLogin(View view, LoginViewModel login) {

        Log.d("ddd", login.getUsername());
    }

    public void onClickForgotPassword(View view) {

        Log.d("ddd", "FORGOT -- ");
    }

    public void onClickSignUp(View view, LoginViewModel login) {

    }
}
