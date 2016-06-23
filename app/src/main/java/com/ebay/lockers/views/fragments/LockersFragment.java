package com.ebay.lockers.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ebay.lockers.R;
import com.ebay.lockers.adapters.ImagesGridAdapter;
import com.ebay.lockers.models.ItemObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunil on 6/15/2016.
 */
public class LockersFragment extends Fragment {

    private static final String TAG = "LockersFragment";

    private static LockersFragment lockersFragment;

    List<ItemObject> objects;

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

        objects = new ArrayList<>();
        loadAllImagesForUser();
    }

    @Override
    public void setUserVisibleHint(boolean isVisible) {
        super.setUserVisibleHint(isVisible);

        if(isVisible) {
            adapter = new ImagesGridAdapter(getActivity(), objects);
            recycler.setAdapter(adapter);
        }
    }

    private void loadAllImagesForUser() {
        File file = new File(getContext().getFilesDir() + "/images/items/");
        String[] names = file.list();

        if (names != null) {
            for(String name : names) {
                File itemFolder = new File(file.getAbsolutePath() + "/" + name + "/");
                String[] itemPics = itemFolder.list();
                for(String itemPic : itemPics) {
                    File pic = new File(itemFolder.getAbsolutePath(), itemPic);
                    ItemObject object = new ItemObject(name, pic);
                    objects.add(object);
                }
            }
        }

    }
}
