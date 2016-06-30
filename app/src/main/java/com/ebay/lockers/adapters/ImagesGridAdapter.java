package com.ebay.lockers.adapters;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebay.lockers.R;
import com.ebay.lockers.models.ItemObject;
import com.ebay.lockers.utils.BitmapUtils;
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
        View layout = LayoutInflater.from(context).inflate(R.layout.cardview_images, null);
        ImagesViewHolder viewHolder = new ImagesViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ImagesViewHolder holder, final int position) {
        // Since this is inside a viewpager and the viewpager loads this view
        // before it comes to it, do the sampling inside the post method
        // else the view sizes resolve to zero and provide very poor sampling
        // The "post" method waits to run on the UI thread which only happens if
        // the view is loaded
        holder.image.post(new Runnable() {
            @Override
            public void run() {
                new BitmapWorkerTask(holder.image).execute(items.get(position).getImageFile());
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ImagesViewHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener {

        public SquareImageView image;

        public ImagesViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);

            image = (SquareImageView) item.findViewById(R.id.item);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "ImageViewWidth = " + image.getWidth());
            Log.d(TAG, "ImageViewHeight = " + image.getHeight());
        }
    }

    public class BitmapWorkerTask extends AsyncTask<File, Void, Bitmap> {
        private final WeakReference<SquareImageView> imageViewWeakReference;
        private int imageWidth;
        private int imageHeight;

        public BitmapWorkerTask(SquareImageView imageView) {
            this.imageViewWeakReference = new WeakReference<SquareImageView>(imageView);
        }

        @Override
        protected void onPreExecute() {
            imageWidth = imageViewWeakReference.get().getWidth();
            imageHeight = imageViewWeakReference.get().getHeight();
        }

        // Load image in the background
        @Override
        protected Bitmap doInBackground(File... params) {
            if(imageViewWeakReference == null) {
                return null;
            }
            File input = params[0];
            Bitmap image = BitmapUtils.decodeSampledBitmap(input, imageWidth, imageHeight);

            // resize bitmap if necessary - based on requirements
            // Bitmap resizedBitmap = BitmapUtils.resizeBitmap(image, imageWidth, imageHeight);
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(imageViewWeakReference != null && bitmap != null) {
                final SquareImageView imageView = imageViewWeakReference.get();
                if(imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
