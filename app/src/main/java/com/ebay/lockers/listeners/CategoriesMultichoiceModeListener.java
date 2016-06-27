package com.ebay.lockers.listeners;

import android.app.Activity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ebay.lockers.R;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sudavid on 6/27/2016.
 */
public class CategoriesMultichoiceModeListener implements AbsListView.MultiChoiceModeListener {

    private static final String TAG = "CategoriesMultichoiceModeListener";

    Activity context;
    ActionMode activeMode;
    ListView listView;
    ArrayAdapter<String> adapter;
    List<String> categories;

    public CategoriesMultichoiceModeListener(Activity context, ListView listView, ArrayAdapter<String> adapter, List<String> categories) {
        this.context = context;
        this.listView = listView;
        this.adapter = adapter;
        this.categories = categories;
    }
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = context.getMenuInflater();
        inflater.inflate(R.menu.search_categories_context, menu);

        mode.setTitle(context.getString(R.string.delete_subscriptions));
        activeMode = mode;
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch(item.getItemId()) {
            case R.id.deleteSubscription:
                SparseBooleanArray checked = listView.getCheckedItemPositions();
                final ArrayList<Integer> positions = new ArrayList<>();

                for(int i = 0; i < checked.size(); i++) {
                    positions.add(checked.keyAt(i));
                }
                Collections.sort(positions, Collections.reverseOrder());
                Log.d(TAG, "positions.size() = " + positions.size());
                Log.d(TAG, "categories.size() = " + categories.size());
                for(int position : positions) {
                    adapter.remove(categories.get(position));
                }
                Log.d(TAG, "positions.size() = " + positions.size());
                Log.d(TAG, "categories.size() = " + categories.size());
                new Thread() {
                    @Override
                    public void run() {
                        for(int position : positions) {
                            FirebaseMessaging.getInstance().unsubscribeFromTopic(categories.get(position));
                            categories.remove(position);
                        }
                    }
                }.start();
                listView.clearChoices();
                return true;

        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        activeMode = null;
    }
}
