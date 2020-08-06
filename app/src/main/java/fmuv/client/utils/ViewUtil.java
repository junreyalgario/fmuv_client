package fmuv.client.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fmuv.client.R;

public class ViewUtil {

    /**
     * Enabled/disabled view group
     */
    public static void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);

        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableView(group.getChildAt(idx), enabled);
            }
        }
    }

    /**
     * Show/hide button spinner
     */
    public static void btnSpinner(View view, String text , int show) {
        view.findViewById(R.id.btnSpinnerProgressBar).setVisibility(show);
        ((TextView)view.findViewById(R.id.btnSpinnerText)).setText(text);
    }

    /**
     * Message dialog helper
     */
    public static void showMessage(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppAlertDialogTheme);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

}
