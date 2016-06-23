package com.ebay.lockers.models;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by Sunil on 6/20/2016.
 */
public class ItemObject {

    private String itemName;
    private File image;

    public ItemObject(String itemName, File image) {
        this.itemName = itemName;
        this.image = image;
    }

    public String getItemName() {
        return this.itemName;
    }

    public File getImageFile() {
        return this.image;
    }

}
