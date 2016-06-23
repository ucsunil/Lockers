package com.ebay.lockers.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ebay.lockers.R;
import com.ebay.lockers.adapters.NotificationsListAdapter;

/**
 * Created by Sunil on 6/15/2016.
 */
public class NotificationsFragment extends Fragment {

    private static NotificationsFragment notificationsFragment = null;

    private RecyclerView notifications;
    private NotificationsListAdapter notificationsAdapter;
    private LinearLayoutManager layoutManager;

    public static NotificationsFragment getInstance() {
        if(notificationsFragment == null) {
            notificationsFragment = new NotificationsFragment();
        }
        return notificationsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notifications_fragment, root, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        notifications = (RecyclerView) view.findViewById(R.id.notifications);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        notifications.setLayoutManager(layoutManager);
        notificationsAdapter = new NotificationsListAdapter(getActivity(), null);
        notifications.setAdapter(notificationsAdapter);
    }
}
