package com.ebay.lockers.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ebay.lockers.R;
import com.ebay.lockers.adapters.ImagesGridAdapter;

/**
 * Created by Sunil on 6/15/2016.
 */
public class LockersFragment extends Fragment {

    private static LockersFragment lockersFragment;

    public static LockersFragment getInstance() {
        if(lockersFragment == null) {
            lockersFragment = new LockersFragment();
        }
        return lockersFragment;
    }

    private RecyclerView recycler;
    private GridLayoutManager layoutManager;
    ImagesGridAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lockers_fragment, root, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recycler = (RecyclerView) view.findViewById(R.id.images);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recycler.setLayoutManager(layoutManager);

        adapter = new ImagesGridAdapter(getActivity(), null);
        recycler.setAdapter(adapter);
    }
}
