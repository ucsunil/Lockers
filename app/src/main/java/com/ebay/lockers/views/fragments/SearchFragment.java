package com.ebay.lockers.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ebay.lockers.R;
import com.ebay.lockers.listeners.CategoriesMultichoiceModeListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunil on 6/15/2016.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private static SearchFragment searchFragment = null;

    public static SearchFragment getInstance() {
        if(searchFragment == null) {
            searchFragment = new SearchFragment();
        }
        return searchFragment;
    }

    private EditText category;
    private Button save;
    private List<String> categories;
    private ListView categoriesList;
    private ArrayAdapter<String> categoriesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_categories, root, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        category = (EditText) view.findViewById(R.id.category);
        save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(this);
        categoriesList = (ListView) view.findViewById(R.id.categoriesList);
        save.setEnabled(false);

        category.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length() != 0) {
                    save.setEnabled(true);
                } else {
                    save.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        categories = new ArrayList<>();
        categoriesAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, categories);
        categoriesList.setAdapter(categoriesAdapter);
        categoriesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        categoriesList.setMultiChoiceModeListener(new CategoriesMultichoiceModeListener(getActivity(), categoriesList, categoriesAdapter, categories));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.save:
                if(categories.contains(category.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "This category is already saved", Toast.LENGTH_SHORT).show();
                    return;
                }
                categories.add(category.getText().toString().trim());
                categoriesAdapter.notifyDataSetChanged();
                // FirebaseMessaging.getInstance().subscribeToTopic(category.getText().toString().trim());
                category.setText("");
                break;
        }
    }
}
