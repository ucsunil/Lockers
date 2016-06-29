package com.ebay.lockers.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.ebay.lockers.R;
import com.ebay.lockers.adapters.HomePageItemsAdapter;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Sunil on 6/15/2016.
 */
public class HomeFragment extends Fragment {

    private static HomeFragment homeFragment = null;

    public static HomeFragment getInstance() {
        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    private RecyclerView homePageItems;
    private HomePageItemsAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, root, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("token", token);
        homePageItems = (RecyclerView) view.findViewById(R.id.homepageItems);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homePageItems.setLayoutManager(layoutManager);

        adapter = new HomePageItemsAdapter(getActivity(), null);
        homePageItems.setAdapter(adapter);
        homePageItems.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(homePageItems.getChildCount() > 0) {
                    switch (e.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            homePageItems.findViewById(R.id.commentsList).getParent().requestDisallowInterceptTouchEvent(true);
                            break;
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
