package fmuv.client.ui.schedule;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import fmuv.client.R;
import fmuv.client.databinding.ActivityTripSchedulesBinding;
import fmuv.client.ui.base.BaseActivity;
import fmuv.client.ui.util.DatePickerDialog;
import fmuv.client.ui.util.SpinnerUtil;
import fmuv.client.ui.util.ViewUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class TripScheduleActivity extends BaseActivity<TripScheduleViewModel> {

    private ActivityTripSchedulesBinding dataBinding;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_trip_schedules);

        attachToolbar("Trip Schedules");
        setOriginSpinner();
        setDestinationSpinner();
        setNoOfPassengerSpinner();

        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnSelectDateListener(new DatePickerDialog.OnSelectDateListener() {
            @Override
            public void onSelect(int year, int month, int day) {

            }
        });
    }

    @Override
    protected TripScheduleViewModel createViewModel() {
        return ViewModelProviders.of(this).get(TripScheduleViewModel.class);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_enter, R.anim.right_leave);
    }

    public void onClickSearch(View view) {
        viewUtil.btnLoading(dataBinding.btnSearchTrip, "Searching ...", View.VISIBLE);
    }

    public void onClickDatePicker(View view) {
        datePickerDialog.show();
    }

    // Spinners

    private void setOriginSpinner() {
        List<String> origins = new ArrayList<>();
        origins.add("Tacloban City");
        origins.add("Guiuan E. Samar");
        origins.add("Borongan");

        SpinnerUtil.Builder(this, dataBinding.spinnerOrigin)
                .setList(origins)
                .setHint("Select Origin")
                .setPrompt("Select Origin")
                .setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }).build();
    }

    private void setDestinationSpinner() {
        List<String> destinations = new ArrayList<>();
        destinations.add("Tacloban City");
        destinations.add("Guiuan E. Samar");
        destinations.add("Borongan");

        SpinnerUtil.Builder(this, dataBinding.spinnerDestination)
                .setList(destinations)
                .setHint("Select Origin")
                .setPrompt("Select Origin")
                .setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }).build();
    }

    private void setNoOfPassengerSpinner() {
        SpinnerUtil.Builder(this, dataBinding.spinnerNoPassenger)
                .setIntRange(1, 14)
                .setItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }).build();
    }

}
