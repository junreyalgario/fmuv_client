package fmuv.client.ui.traveling;

import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.ui.base.BaseActivity;

import android.os.Bundle;

public class TravelingUvActivity extends BaseActivity<TravelingUvViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traveling_uv);

        attachToolbar("Traveling UV's");
    }

    @Override
    protected TravelingUvViewModel createViewModel() {
        return ViewModelProviders.of(this).get(TravelingUvViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_enter, R.anim.right_leave);
    }
}
