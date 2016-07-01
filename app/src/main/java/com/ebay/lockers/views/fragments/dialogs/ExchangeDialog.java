package com.ebay.lockers.views.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ebay.lockers.R;
import com.ebay.lockers.utils.ConstantUtils;

/**
 * Created by Sunil on 6/27/2016.
 */
public class ExchangeDialog extends DialogFragment implements View.OnClickListener {

    private Button send;
    private Spinner lockerItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exchange_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        send = (Button) view.findViewById(R.id.send);
        send.setOnClickListener(this);
        lockerItems = (Spinner) view.findViewById(R.id.lockerItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ConstantUtils.MOCK_ITEMS_ARRAY);
        lockerItems.setAdapter(adapter);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if(dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.send:
                this.dismiss();
                break;
        }
    }
}
