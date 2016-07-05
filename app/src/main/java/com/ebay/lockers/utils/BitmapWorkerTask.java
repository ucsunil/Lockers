package com.ebay.lockers.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Sunil on 6/30/2016.
 */
public class BitmapWorkerTask extends AsyncTask<File, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewWeakReference;
    private File input;
    private int imageWidth;
    private int imageHeight;

    public BitmapWorkerTask(ImageView imageView, int reqWidth, int reqHeight) {
        this.imageViewWeakReference = new WeakReference<ImageView>(imageView);
        this.imageWidth = reqWidth;
        this.imageHeight = reqHeight;
    }

    @Override
    protected void onPreExecute() {
    }

    // Load image in the background
    @Override
    protected Bitmap doInBackground(File... params) {
        if(imageViewWeakReference == null) {
            return null;
        }
        input = params[0];
        Bitmap image = BitmapUtils.decodeSampledBitmap(input, imageWidth/2, imageHeight/2);

        // resize bitmap if necessary - based on requirements as we need to fill the card view
        Bitmap scaledBitmap = BitmapUtils.resizeBitmap(image, imageWidth, (imageWidth*4)/3);
        // Bitmap correctFitBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight());
        return scaledBitmap;
        //return scaledBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(isCancelled()) {
            bitmap = null;
        }

        if(imageViewWeakReference != null && bitmap != null) {
            final ImageView imageView = imageViewWeakReference.get();
            final BitmapWorkerTask bitmapWorkerTask = BitmapUtils.getBitmapWorkerTask(imageView);
            if(this == bitmapWorkerTask && imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public File getImageFile() {
        return this.input;
    }
}