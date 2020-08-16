package fmuv.client.ui.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import fmuv.client.R;

public class SpinnerUtil {

    private Context context;
    private android.widget.Spinner spinner;
    private List<String> items = new ArrayList<>();
    private java.lang.String hint;

    private SpinnerUtil(Context context, android.widget.Spinner spinner) {
        this.spinner = spinner;
        this.context = context;
    }

    public static SpinnerUtil Builder(Context context, android.widget.Spinner spinner) {
        return new SpinnerUtil(context, spinner);
    }

    public SpinnerUtil setList(List<String> items) {
        this.items = items;
        return this;
    }

    public SpinnerUtil addItem(String item) {
        items.add(item);
        return this;
    }

    public SpinnerUtil setIntRange(Integer min, Integer max) {
        for(Integer i=min; i<=max; i++) {
            items.add(String.valueOf(i));
        }
        return this;
    }

    public SpinnerUtil setPrompt(java.lang.String promt) {
        spinner.setPrompt(promt);
        return this;
    }

    public SpinnerUtil setHint(java.lang.String hint) {
        this.hint = hint;
        return this;
    }

    public SpinnerUtil setItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        spinner.setOnItemSelectedListener(listener);
        return this;
    }

    public void build() {

        if (hint != null) {
            items.add(hint);
        }

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(context, android.R.layout.simple_spinner_item, items);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        if (hint != null) {
            spinner.setSelection(spinnerAdapter.getCount());
        }
    }

    private class SpinnerAdapter extends ArrayAdapter<String> {

        public SpinnerAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = super.getView(position, convertView, parent);
            ((TextView)view.findViewById(android.R.id.text1)).setTextColor(getContext().getResources().getColor(R.color.colorDark));
            view.setPadding(0, view.getPaddingTop(), 0, view.getPaddingBottom());

            if (position == getCount()) {
                ((TextView)view.findViewById(android.R.id.text1)).setTextColor(getContext().getResources().getColor(R.color.colorLightGray));
            }

            return view;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView textView = view.findViewById(android.R.id.text1);

            textView.setPadding(40, parent.getPaddingTop(), 40, parent.getPaddingBottom());
            textView.setTextColor(getContext().getResources().getColor(R.color.colorDark));
            return view;
        }

        @Override
        public int getCount() {
            return hint == null ? super.getCount() : super.getCount() - 1 ;
        }
    }

}