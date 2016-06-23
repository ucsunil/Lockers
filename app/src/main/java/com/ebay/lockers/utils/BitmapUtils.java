package com.ebay.lockers.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.File;

/**
 * Created by sudavid on 6/22/2016.
 */
public class BitmapUtils {

    public static Bitmap decodeSampledBitmap(File file, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        // Calculate sampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap
        options.inJustDecodeBounds = false;

        // Bitmap scaledBitmap =  BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        if(options.outHeight < options.outWidth) {
            // rotate bitmap by 90
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap scaledBitmap =  BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            Log.d("Utils:", "ImageHeight = " + scaledBitmap.getHeight());
            Log.d("Utils:", "ImageWidth = " + scaledBitmap.getWidth());
            Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
            scaledBitmap.recycle();
            return rotatedBitmap;
        } else {
            return  BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        }

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap resizeBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
