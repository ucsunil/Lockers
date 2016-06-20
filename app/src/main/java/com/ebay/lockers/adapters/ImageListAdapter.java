package com.ebay.lockers.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Sunil on 6/17/2016.
 */
public class ImageListAdapter {

    private List<Bitmap> bitmaps;

    public ImageListAdapter(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public void addBitmap(Bitmap bitmap) {
        this.bitmaps.add(bitmap);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is an image
        ImageView image;

        public ViewHolder(ImageView image) {
            super(image);
            this.image = image;
        }
    }
}
