package fmuv.client.ui.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import fmuv.client.R;

public class DatePickerDialog {

    private OnSelectDateListener onSelectDateListener;
    private AlertDialog datePickerDialog;

    public DatePickerDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppAlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.picker_date, null);
        DatePicker datePicker = view.findViewById(R.id.picker_date);

        builder.setTitle("Select Date")
                .setView(view)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setDate(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                    }
                });

        datePickerDialog = builder.create();
    }

    public void setOnSelectDateListener(OnSelectDateListener OnSelectDateListener) {
        this.onSelectDateListener = onSelectDateListener;
    }

    public void show() {
        datePickerDialog.show();
    }

    private void setDate(int year, int month, int day) {
        if (onSelectDateListener != null) {
            onSelectDateListener.onSelect(year, month, day);
        }
    }

    public interface OnSelectDateListener {
        void onSelect(int year, int month, int day);
    }

}
