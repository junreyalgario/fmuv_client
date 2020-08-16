package fmuv.client.ui.mybooking;

import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.ui.base.BaseActivity;

import android.os.Bundle;

public class MyBookingActivity extends BaseActivity<MyBookingViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        attachToolbar("My Bookings");
    }

    @Override
    protected MyBookingViewModel createViewModel() {
        return ViewModelProviders.of(this).get(MyBookingViewModel.class);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_enter, R.anim.right_leave);
    }
}
