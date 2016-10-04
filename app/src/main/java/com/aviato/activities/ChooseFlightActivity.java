package com.aviato.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aviato.R;
import com.aviato.adapters.ChooseFlightAdapter;
import com.aviato.model.FlightData;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChooseFlightActivity extends AppCompatActivity {

    static final String EXTRA_FLIGHT_DATA = "flight_data";

    @BindView(R.id.flight_list)
    ListView listView;

    private List<FlightData> flightDataList;
    private ChooseFlightAdapter chooseFlightAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight);
        ButterKnife.bind(this);
        flightDataList = new ArrayList<>();
        flightDataList.add(new FlightData("Boston", "New York", "482.20"));
        flightDataList.add(new FlightData("Boston", "New York", "586.18"));
        flightDataList.add(new FlightData("Boston", "Chicago", "586.18"));
        flightDataList.add(new FlightData("Boston", "Chicago", "516.18"));
        flightDataList.add(new FlightData("Boston", "Los Angeles", "246.18"));
        flightDataList.add(new FlightData("Boston", "Atlanta", "1201.20"));
        flightDataList.add(new FlightData("Boston", "Atlanta", "3475.18"));
        flightDataList.add(new FlightData("Boston", "Miami", "375.18"));
        flightDataList.add(new FlightData("Boston", "Miami", "345.18"));
        chooseFlightAdapter = new ChooseFlightAdapter(flightDataList, getApplicationContext());
        listView.setAdapter(chooseFlightAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FlightData flightData = flightDataList.get(i);
                Intent intent = new Intent(ChooseFlightActivity.this, ChoosePersonActivity.class);
                intent.putExtra(EXTRA_FLIGHT_DATA, Parcels.wrap(flightData));
                startActivity(intent);
            }
        });
    }
}
