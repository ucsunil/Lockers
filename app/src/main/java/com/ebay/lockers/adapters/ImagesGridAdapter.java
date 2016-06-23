package com.ebay.lockers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
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

        holder.image.post(new Runnable() {
            @Override
            public void run() {
                int imageWidth = holder.image.getWidth();
                int imageHeight = holder.image.getHeight();

                Bitmap image = BitmapUtils.decodeSampledBitmap(items.get(position).getImageFile(),
                        imageWidth, imageHeight);
                Log.d(TAG, "ImageViewWidth = " + imageWidth);
                Log.d(TAG, "ImageViewHeight = " + imageHeight);
                Log.d(TAG, "ImageWidth = " + image.getWidth());
                Log.d(TAG, "ImageHeight = " + image.getHeight());
                Bitmap resizedBitmap = BitmapUtils.resizeBitmap(image, imageWidth, imageHeight);
                holder.image.setImageBitmap(resizedBitmap);
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
}
