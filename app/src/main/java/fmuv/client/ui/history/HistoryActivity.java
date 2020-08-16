package fmuv.client.ui.history;

import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.ui.base.BaseActivity;

import android.os.Bundle;

public class HistoryActivity extends BaseActivity<HistoryViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        attachToolbar("History");
    }

    @Override
    protected HistoryViewModel createViewModel() {
        return ViewModelProviders.of(this).get(HistoryViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_enter, R.anim.right_leave);
    }

}
