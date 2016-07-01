package com.ebay.lockers.views.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ebay.lockers.R;
import com.ebay.lockers.utils.ConstantUtils;

public class CounterOfferDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner theirLockerItems;
    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_offer_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        close = (ImageView) findViewById(R.id.closeDialog);
        theirLockerItems = (Spinner) findViewById(R.id.theirLockerItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ConstantUtils.MOCK_ITEMS_ARRAY);
        theirLockerItems.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.closeDialog:
                this.finish();
                break;

        }
    }
}
