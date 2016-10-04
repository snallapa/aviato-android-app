package com.aviato.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aviato.R;
import com.aviato.model.FlightData;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sahith on 10/1/16.
 */

public class ChooseFlightAdapter extends BaseAdapter {

    List<FlightData> flightData;
    Context context;

    public ChooseFlightAdapter(List<FlightData> flightData, Context context) {
        this.flightData = new ArrayList<>(flightData);
        this.context = context;
    }

    @Override
    public int getCount() {
        return flightData.size();
    }

    @Override
    public FlightData getItem(int i) {
        return flightData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.choose_flight_row, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        FlightData currentFlight = getItem(i);
        viewHolder.destination.setText(context.getString(R.string.destination, currentFlight.getDestination()));
        viewHolder.source.setText(context.getString(R.string.source, currentFlight.getSource()));
        viewHolder.price.setText(context.getString(R.string.price, currentFlight.getPrice()));
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.flight_destination)
        TextView destination;
        @BindView(R.id.flight_source)
        TextView source;
        @BindView(R.id.flight_price)
        TextView price;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
