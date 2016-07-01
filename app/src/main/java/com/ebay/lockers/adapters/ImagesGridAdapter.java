package com.ebay.lockers.adapters;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebay.lockers.R;
import com.ebay.lockers.models.ItemObject;
import com.ebay.lockers.utils.AsyncDrawable;
import com.ebay.lockers.utils.BitmapUtils;
import com.ebay.lockers.utils.BitmapWorkerTask;
import com.ebay.lockers.views.custom.SquareImageView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Sunil on 6/20/2016.
 */
public class ImagesGridAdapter extends RecyclerView.Adapter<ImagesGridAdapter.ImagesViewHolder> {

    private static final String TAG = "ImagesGridAdapter";

    private List<ItemObject> items;
    private Context context;

    public ImagesGridAdapter(Context context, List<ItemObject> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.cardview_images, parent, false);
        ImagesViewHolder viewHolder = new ImagesViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ImagesViewHolder holder, final int position) {
        int layoutWidth = 0, layoutHeight = 0;

        // Since this is inside a viewpager and the viewpager loads this view
        // before it comes to it, do the sampling inside the post method
        // else the view sizes resolve to zero and provide very poor sampling
        // The "post" method waits to run on the UI thread which only happens if
        // the view is loaded
        holder.image.post(new Runnable() {
            @Override
            public void run() {
                // You have to forcefully set the size for it to be maintained
                // when the RecyclerView recycles the layout
                // else the view tries to expand according to the layout
                int reqWidth = holder.image.getMeasuredWidth();
                int reqHeight = (reqWidth*4)/3; // aspect ratio of 4:3 is the best for mobile devices
                int minDimen = reqWidth < reqHeight ? reqWidth : reqHeight;
                // calculate minDimen for optimal scaling
                // this likely can be reduced even further
                loadBitmap(items.get(position).getImageFile(), holder.image, minDimen, minDimen);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
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

    public static class ImagesViewHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener {

        public ImageView image;
        public CardView imageHolder;

        public ImagesViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);

            image = (ImageView) item.findViewById(R.id.item);
            imageHolder = (CardView) item.findViewById(R.id.card_view);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "ImageViewWidth = " + image.getWidth());
            Log.d(TAG, "ImageViewHeight = " + image.getHeight());
        }
    }
}
