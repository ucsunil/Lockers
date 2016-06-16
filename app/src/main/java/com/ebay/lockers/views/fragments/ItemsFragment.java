package com.ebay.lockers.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ebay.lockers.R;

/**
 * Created by Sunil on 6/15/2016.
 */
public class ItemsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.items_fragment, root, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "In items fragment", Toast.LENGTH_SHORT).show();
    }
}
