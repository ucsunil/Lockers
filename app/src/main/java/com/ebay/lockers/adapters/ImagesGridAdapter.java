package com.ebay.lockers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebay.lockers.R;
import com.ebay.lockers.models.ItemObject;

import java.util.List;

/**
 * Created by Sunil on 6/20/2016.
 */
public class ImagesGridAdapter extends RecyclerView.Adapter<ImagesGridAdapter.ImagesViewHolder> {

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
    public void onBindViewHolder(ImagesViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ImagesViewHolder extends RecyclerView.ViewHolder
                                implements View.OnClickListener {

        public ImageView image;

        public ImagesViewHolder(View item) {
            super(item);
            item.setOnClickListener(this);

            image = (ImageView) item.findViewById(R.id.item);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
