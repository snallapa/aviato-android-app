package com.aviato.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.aviato.R;
import com.aviato.adapters.NotificationAdapter;
import com.aviato.interfaces.OnSideChosen;
import com.aviato.model.NotificationData;
import com.aviato.model.enums.Side;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnSideChosen {

    static final String EXTRA_SIDE = "extra_side";

    @BindView(R.id.notifications)
    ListView notifications;

    NotificationAdapter notificationAdapter;

    public static List<NotificationData> notificationList;
    private Side side;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        notificationList = new ArrayList<>();
        notificationList.add(new NotificationData("Sahith", false, false, "Chicago", "New York"));
        notificationList.add(new NotificationData("Karl", true, false, "Boston", "New York"));
        notificationList.add(new NotificationData("Jarl", false, true, "New York", "New York"));

        notificationAdapter = new NotificationAdapter(notificationList);
        notifications.setAdapter(notificationAdapter);
        int side = getIntent().getIntExtra(EXTRA_SIDE, -1);
        this.side = side == 0 ? Side.ATTRACTIVE : Side.DESPERATE;
    }

    @Override
    protected void onStart() {
        super.onStart();
        NotificationData new_thing = Parcels.unwrap(getIntent().getParcelableExtra("NEW_THING"));
        if (new_thing != null) {
            notificationList.add(new_thing);
        }
        notificationAdapter.setData(notificationList);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        NotificationData new_thing = Parcels.unwrap(intent.getParcelableExtra("NEW_THING"));
        if (new_thing != null) {
            notificationList.add(new_thing);
        }
        notificationAdapter.setData(notificationList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_booking:
                onSideChosen(side);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSideChosen(Side side) {
        switch (side) {
            case DESPERATE:
                startActivity(new Intent(this, MainDesperateActivity.class));
                finish();
                break;
            case ATTRACTIVE:
                startActivity(new Intent(this, MainAttractiveActivity.class));
                finish();
                break;
            default:
                throw new RuntimeException("What the actual fuck are you doing here? How did you even get here>");
        }
    }
}
