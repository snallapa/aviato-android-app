package com.aviato.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.aviato.R;
import com.aviato.interfaces.OnDateTimePicked;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTimeDialog extends DialogFragment {

    public static final String TAG = "date_time_dialog";

    private static final String CURRENT_DATE = "current_date";

    private DatePicker flightDatePicker;

    public static DateTimeDialog getInstance(Date date) {
        DateTimeDialog dateTimeDialog = new DateTimeDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CURRENT_DATE, date);
        dateTimeDialog.setArguments(bundle);
        return dateTimeDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.set_date_time);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                commit();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.date_time_picker_dialog, null);
        builder.setView(view);
        flightDatePicker = ButterKnife.findById(view, R.id.flight_date_picker);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) getArguments().getSerializable(CURRENT_DATE));
        flightDatePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        return builder.create();
    }

    private void commit() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(flightDatePicker.getYear(),
                flightDatePicker.getMonth(),
                flightDatePicker.getDayOfMonth());
        if (getActivity() instanceof OnDateTimePicked) {
            ((OnDateTimePicked) getActivity()).onDateTimePicked(calendar.getTime());
        } else {
            throw new RuntimeException("Activity should implement OnDateTimePicked Listener");
        }
    }
}
