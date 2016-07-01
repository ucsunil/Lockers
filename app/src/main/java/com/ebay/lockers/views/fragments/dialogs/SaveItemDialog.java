package com.ebay.lockers.views.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ebay.lockers.R;
import com.ebay.lockers.views.fragments.ItemsFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Sunil on 6/19/2016.
 */
public class SaveItemDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "SaveItemDialog";

    private Button save;
    private Button cancel;
    private EditText name;
    private EditText description;
    private EditText tags;

    private SaveItemListener saveListener;

    public static SaveItemDialog newInstance(Bundle bundle) {
        SaveItemDialog saveItemDialog = new SaveItemDialog();
        saveItemDialog.setArguments(bundle);
        return saveItemDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.save_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(this);
        cancel = (Button) view.findViewById(R.id.save);
        name = (EditText) view.findViewById(R.id.name);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        saveListener = (ItemsFragment) getTargetFragment();
        Dialog dialog = getDialog();
        if(dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.save:
                Bundle details = getArguments();
                File tempFolderName = new File(details.getString("tempimagespath"));

                // create new folder to save item
                File itemSaveFolder = new File(getContext().getFilesDir() + "/images/items/" + name.getText().toString() + "/");
                if(!itemSaveFolder.exists()) {
                    itemSaveFolder.mkdirs();
                }
                // simply rename temp to create new item folder
                tempFolderName.renameTo(itemSaveFolder);
                saveListener.onItemSaved();
                this.dismiss();
                break;
        }
    }

    public interface SaveItemListener {
        void onItemSaved();
    }

}
