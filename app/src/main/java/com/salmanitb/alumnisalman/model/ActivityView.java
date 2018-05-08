package com.salmanitb.alumnisalman.model;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.salmanitb.alumnisalman.R;

/**
 * Created by Fadhil Imam Kurnia on 14/04/2018.
 */

public class ActivityView extends LinearLayout {

    private CheckBox checkBox;
    private EditText txtStartYear;
    private EditText txtEndYear;

    public ActivityView(Context context, String title) {
        super(context);
        inflateLayout(context);
        setTitle(title);
    }

    public ActivityView(Context context, String title, boolean check, boolean start, boolean end) {
        super(context);
        inflateLayout(context);
        setTitle(title);
        checkBox.setChecked(check);
        txtStartYear.setEnabled(start);
        txtEndYear.setEnabled(end);
    }

    public ActivityView(Context context) {
        super(context);
        inflateLayout(context);
    }

    public ActivityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public String getTitle() {
        return checkBox.getText().toString();
    }

    public void setTitle(String title) {
        checkBox.setText(title);
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public String getStartYear() {
        return txtStartYear.getText().toString();
    }

    public void setStartYear(int startYear) {
        txtStartYear.setText(String.valueOf(startYear));
    }

    public void setEndYear(int endYear) {
        txtEndYear.setText(String.valueOf(endYear));
    }

    public String getEndYear() {
        return txtEndYear.getText().toString();
    }


    public void inflateLayout(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_registration_row, this);
        this.checkBox = view.findViewById(R.id.activity_checkbox);
        this.txtStartYear = view.findViewById(R.id.activity_start_year);
        this.txtEndYear = view.findViewById(R.id.activity_end_year);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtStartYear.setEnabled(true);
                    txtEndYear.setEnabled(true);
                    txtStartYear.setBackground(context.getResources().getDrawable(R.drawable.edit_text_background));
                    txtEndYear.setBackground(context.getResources().getDrawable(R.drawable.edit_text_background));
                } else {
                    txtStartYear.setText("");
                    txtEndYear.setText("");
                    txtStartYear.setEnabled(false);
                    txtEndYear.setEnabled(false);
                    txtStartYear.setBackground(context.getResources().getDrawable(R.drawable.edit_text_background_disabled));
                    txtEndYear.setBackground(context.getResources().getDrawable(R.drawable.edit_text_background_disabled));
                }
            }
        });

    }

}
