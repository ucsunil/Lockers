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
public class SearchFragment extends Fragment {

    private static SearchFragment searchFragment = null;

    public static SearchFragment getInstance() {
        if(searchFragment == null) {
            searchFragment = new SearchFragment();
        }
        return searchFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, root, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
