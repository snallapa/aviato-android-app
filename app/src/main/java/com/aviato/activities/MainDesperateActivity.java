package com.aviato.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.aviato.R;
import com.aviato.dialogs.DateTimeDialog;
import com.aviato.interfaces.OnDateTimePicked;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainDesperateActivity extends AppCompatActivity implements OnDateTimePicked, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int PERMISSION_REQUEST = 124;
    private static final String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    @BindView(R.id.main_flight_date)
    TextView flightDate;
    @BindView(R.id.current_location)
    EditText currentLocation;

    private Date currentDate;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_desperate);
        ButterKnife.bind(this);
        currentDate = Calendar.getInstance().getTime();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        initViews();
        checkPermissions();
    }

    @Override
    protected void onStart() {
        if (TextUtils.isEmpty(currentLocation.getText())) {
            googleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    private void initViews() {
        setTextDate(currentDate);
    }

    @OnClick(R.id.main_flight_date)
    public void onDateClicked() {
        DateTimeDialog dateTimePicker = DateTimeDialog.getInstance(currentDate);
        dateTimePicker.show(getFragmentManager(), DateTimeDialog.TAG);
    }

    @OnClick(R.id.search_flight_button)
    public void searchFlights() {
        startActivity(new Intent(this, ChooseFlightActivity.class));
    }

    @Override
    public void onDateTimePicked(Date date) {
        currentDate = date;
        setTextDate(date);
    }

    private void setTextDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.US);
        flightDate.setText(simpleDateFormat.format(date));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                googleApiClient);
        if (lastLocation != null) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
                List<Address> locations = geocoder.getFromLocation(lastLocation.getLatitude(), lastLocation.getLongitude(), 1);
                if (locations.size() > 0) {
                    currentLocation.append(
                            locations.get(0).getLocality());
                }
            } catch (IOException exception) {

            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void checkPermissions() {
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST);
    }
}
