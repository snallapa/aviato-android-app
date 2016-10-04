package com.aviato.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.aviato.R;
import com.aviato.interfaces.OnConfirmBooking;


public class ConfirmBookingDialog extends DialogFragment {

    public static final String TAG = "confirm_booking";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirm_request).setMessage(R.string.confirm_request_message);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(R.string.confirm_book, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (getActivity() instanceof OnConfirmBooking) {
                    ((OnConfirmBooking) getActivity()).onConfirmBooking();
                } else {
                    throw new RuntimeException("Activity does not implement OnConfirmBooking Listener");
                }
            }
        });
        return builder.create();
    }
}
