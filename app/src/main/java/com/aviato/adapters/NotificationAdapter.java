package com.aviato.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aviato.R;
import com.aviato.model.NotificationData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NotificationAdapter extends BaseAdapter {

    private List<NotificationData> notifications;

    public NotificationAdapter(List<NotificationData> items) {
        this.notifications = new ArrayList<>(items);
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int i) {
        return notifications.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_row, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NotificationData currentNotification = notifications.get(i);
        String url = TextUtils.isEmpty(currentNotification.getProfileURl()) ? "https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/12744226_998474320230809_8128984783770111082_n.jpg?oh=fb9bea148d9bccd29ab1c7d4cc61750a&oe=5876EC7E" : currentNotification.getProfileURl();
        Picasso.with(view.getContext()).load(url)
                .resize(300,200).centerCrop().into(viewHolder.profilePicture);
        viewHolder.userName.setText(currentNotification.getUserName());
        viewHolder.status.setText(currentNotification.isPending() ? R.string.pending : currentNotification.isSuccess() ? R.string.accepted : R.string.denied);
        viewHolder.flightInformation.setText(view.getContext().getString(R.string.flight_information, currentNotification.getSource(), currentNotification.getDestination()));
        return view;
    }

    public void setData(List<NotificationData> notifications) {
        this.notifications = new ArrayList<>(notifications);
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.notification_profile)
        ImageView profilePicture;
        @BindView(R.id.notification_name)
        TextView userName;
        @BindView(R.id.notification_flight)
        TextView flightInformation;
        @BindView(R.id.notification_status)
        TextView status;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
