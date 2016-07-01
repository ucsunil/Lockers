package com.ebay.lockers.utils;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by Sunil on 6/30/2016.
 */
public class AsyncDrawable extends BitmapDrawable {

    private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskWeakReference;

    public AsyncDrawable(Resources res, File file, BitmapWorkerTask bitmapWorkerTask) {
        super(res, "");
        bitmapWorkerTaskWeakReference = new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
    }

    public BitmapWorkerTask getBitmapWorkerTask() {
        return bitmapWorkerTaskWeakReference.get();
    }
}
