package com.ebay.lockers.views.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebay.lockers.R;

public class ConfirmAcceptDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_accept_dialog);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        close = (ImageView) findViewById(R.id.closeDialog);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.closeDialog:
                this.finish();
                break;
        }
    }
}
