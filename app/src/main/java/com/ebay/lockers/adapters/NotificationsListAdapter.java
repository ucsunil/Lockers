package com.ebay.lockers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebay.lockers.R;
import com.ebay.lockers.models.NotificationObject;

import java.util.List;

/**
 * Created by Sunil on 6/22/2016.
 */
public class NotificationsListAdapter extends RecyclerView.Adapter<NotificationsListAdapter.NotificationsViewHolder> {

    private Context context;
    private List<NotificationObject> notifications;
    public NotificationsListAdapter(Context context, List<NotificationObject> notifications) {
        this.context = context;
        this.notifications = notifications;
    }

    @Override
    public NotificationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_view, parent, false);
        NotificationsViewHolder viewHolder = new NotificationsViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationsViewHolder holder, int position) {
        holder.notificationText.setText("You have a notification");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class NotificationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView notificationText;

        public NotificationsViewHolder(View itemView) {
            super(itemView);

            notificationText = (TextView) itemView.findViewById(R.id.notify);
            notificationText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
