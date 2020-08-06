package fmuv.client.ui.auth;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.databinding.ActivityRegisterBinding;
import fmuv.client.ui.auth.AuthViewModel;
import fmuv.client.ui.base.BaseActivity;

import android.os.Bundle;

/**
 * @author Junrey Algario algario.devs@gmail.com 2020.7.1
 */

public class RegisterActivity extends BaseActivity<AuthViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding registerBinding;
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);


    }

    @Override
    public AuthViewModel createViewModel() {
        return ViewModelProviders.of(this).get(AuthViewModel.class);
    }
}
