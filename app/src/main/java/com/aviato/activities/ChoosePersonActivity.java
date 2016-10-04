package com.aviato.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aviato.R;
import com.aviato.adapters.ChoosePersonAdapter;
import com.aviato.dialogs.ConfirmBookingDialog;
import com.aviato.interfaces.OnConfirmBooking;
import com.aviato.interfaces.OnPersonChosen;
import com.aviato.model.ChooseData;
import com.aviato.model.FlightData;
import com.aviato.model.NotificationData;
import com.aviato.rest.BookingRequest;
import com.aviato.rest.RestService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChoosePersonActivity extends AppCompatActivity implements OnPersonChosen, OnConfirmBooking {


    @BindView(R.id.choose_recyclerview)
    RecyclerView chooseRecyclerView;
    private ChoosePersonAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<ChooseData> chosenCandidates;
    FlightData flightData;
    private ChooseData chosenCandidate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_person);
        ButterKnife.bind(this);
        chooseRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        chosenCandidates = new ArrayList<>();
//        try {
//            chosenCandidates = RestService.getService().getUser(flightData.getDestination()).execute().body();
//        } catch (IOException exception) {
//
//        }
        flightData = Parcels.unwrap(getIntent().getParcelableExtra(ChooseFlightActivity.EXTRA_FLIGHT_DATA));
        chosenCandidates.add(new ChooseData(32, "Fonzie", "https://nickshell1983.files.wordpress.com/2010/10/fonzie.jpg"));
        chosenCandidates.add(new ChooseData(23, "Prashant", "http://previews.123rf.com/images/prashantzi/prashantzi0905/prashantzi090500025/4927147-Nice-cool-person-relax-over-white-background-Stock-Photo.jpg"));
        chosenCandidates.add(new ChooseData(28, "Amal", "https://pbs.twimg.com/media/Ci5jkglXEAA6hNF.jpg"));
        chosenCandidates.add(new ChooseData(56, "Sarah", "http://blog.thecurrent.org/files/2016/06/FullSizeRender.jpg"));
        chosenCandidates.add(new ChooseData(8, "Jared", "http://media.gettyimages.com/photos/cool-boy-with-sunglasses-picture-id103929757?k=6&m=103929757&s=170667a&w=0&h=3kEzXJ55z3xKfDKAjrB8S4XrvuiBo4g0Pf3doYlkHDs="));
        adapter = new ChoosePersonAdapter(chosenCandidates, this);
        chooseRecyclerView.setLayoutManager(layoutManager);
        chooseRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onPersonChosen(int position) {
        ConfirmBookingDialog confirmBookingDialog = new ConfirmBookingDialog();
        confirmBookingDialog.show(getFragmentManager(), ConfirmBookingDialog.TAG);
        chosenCandidate = chosenCandidates.get(position);
    }

    @Override
    public void onConfirmBooking() {
//        RestService.getService().createBooking(new BookingRequest("asdf", chosenCandidate.getfId(), flightData));
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("NEW_THING", Parcels.wrap(new NotificationData("Amal", false, true, "Boston", "Atlanta", "https://pbs.twimg.com/media/Ci5jkglXEAA6hNF.jpg")));
        startActivity(intent);
        finish();
    }
}
