package fmuv.client.ui.account;

import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.ui.base.BaseActivity;

import android.os.Bundle;

public class AccountActivity extends BaseActivity<AccountViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        attachToolbar("Account");
    }

    @Override
    protected AccountViewModel createViewModel() {
        return ViewModelProviders.of(this).get(AccountViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_enter, R.anim.right_leave);
    }
}
