package fmuv.client.ui.auth;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.databinding.ActivityAuthBinding;
import fmuv.client.ui.auth.binding.Auth;
import fmuv.client.ui.base.BaseActivity;
import fmuv.client.ui.home.HomeActivity;
import fmuv.client.utils.ViewUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class AuthActivity extends BaseActivity<AuthViewModel> {

    private Auth auth = new Auth();
    private ActivityAuthBinding authBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        authBinding.setLifecycleOwner(this);
        authBinding.setAuth(auth);

        initializeBaseActivity();
        attachKeyboardListeners();
    }

    @Override
    public AuthViewModel createViewModel() {
        return ViewModelProviders.of(this).get(AuthViewModel.class);
    }

    // View click events callbacks

    public void onClickLogin(View view) {
        viewModel.authenticate(auth.username, auth.password);
        ViewUtil.btnSpinner(authBinding.btnLogin, "Logging in ...", View.VISIBLE);
        auth.setEnabledLogin(false);
    }

    public void onClickForgotPassword(View view) {
        viewModel.sample();
    }

    public void onClickCreateAccount(View view) {

    }

    // Http response listener

    @Override
    protected void onResponseOk() {
        super.onResponseOk();
        startActivity(new Intent(AuthActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    protected void onForbidden() {
        super.onForbidden();
        auth.setErrAuth(true);
        auth.setEnabledLogin(false);
    }

    @Override
    protected void doCommonOnRequestDone() {
        super.doCommonOnRequestDone();
        ViewUtil.btnSpinner(authBinding.btnLogin, "Login", View.GONE);
        auth.setEnabledLogin(true);
    }

    // Keyboard popup listener

    @Override
    protected void onShowKeyboard() {
        super.onShowKeyboard();
        authBinding.loginLogo.setVisibility(View.GONE);
        authBinding.btnCreateAccount.setVisibility(View.GONE);
    }

    @Override
    protected void onHideKeyboard() {
        super.onHideKeyboard();
        authBinding.loginLogo.setVisibility(View.VISIBLE);
        authBinding.btnCreateAccount.setVisibility(View.VISIBLE);
    }

}