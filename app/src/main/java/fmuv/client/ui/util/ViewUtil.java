package fmuv.client.ui.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fmuv.client.R;

public class ViewUtil {

    private Context context;

    public ViewUtil(Context context) {
        this.context = context;
    }

    // Enabled/disabled view group
    public void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);

        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableView(group.getChildAt(idx), enabled);
            }
        }
    }

    // Show/hide button spinner
    public void btnLoading(View view, String text , int show) {
        view.findViewById(R.id.btnProgressBar).setVisibility(show);
        ((TextView)view.findViewById(R.id.btnLoadingText)).setText(text);
    }

    // Message dialog helper
    public void showMessage(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppAlertDialogTheme);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", listener);
        AlertDialog alert = builder.create();
        alert.show();
    }

}
