package com.ebay.lockers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebay.lockers.R;
import com.ebay.lockers.utils.AsyncDrawable;
import com.ebay.lockers.utils.BitmapUtils;
import com.ebay.lockers.utils.BitmapWorkerTask;

import java.io.File;
import java.util.List;

/**
 * Created by Sunil on 6/17/2016.
 */
public class ImagesHorizontalListAdapter extends RecyclerView.Adapter<ImagesHorizontalListAdapter.ImagesViewHolder> {

    private Context context;
    private List<File> imageFiles;

    public ImagesHorizontalListAdapter(Context context, List<File> imageFiles) {
        this.context = context;
        this.imageFiles = imageFiles;
    }

    @Override
    public ImagesHorizontalListAdapter.ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.simple_image_view, parent, false);
        ImagesViewHolder viewHolder = new ImagesViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ImagesHorizontalListAdapter.ImagesViewHolder holder, final int position) {
        int availableWidth = context.getResources().getDisplayMetrics().widthPixels;
        int imageWidth = availableWidth/4; // Number of images to be shown by default
        int imageHeight = imageWidth*4/3;
        final int minDimenForScaling = Math.min(imageWidth, imageHeight);

        holder.image.post(new Runnable() {
            @Override
            public void run() {
                loadBitmap(imageFiles.get(position), holder.image, minDimenForScaling, minDimenForScaling);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageFiles.size();
    }

    public void loadBitmap(File file, ImageView imageView, int reqWidth, int reqHeight) {
        if(BitmapUtils.cancelPotentialWork(file, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView, reqWidth, reqHeight);
            // The second Bitmap parameter is a placeholder image
            // Should consider animation; TO DO --
            final AsyncDrawable asyncDrawable = new AsyncDrawable(context.getResources(), null, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(file);
        }
    }

    public static class ImagesViewHolder extends RecyclerView.ViewHolder {
        // each data item is an image
        ImageView image;

        public ImagesViewHolder(View layout) {
            super(layout);
            this.image = (ImageView) layout.findViewById(R.id.image);
        }
    }
}
