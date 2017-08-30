package com.example.glen.squidcache.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glen.squidcache.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by glen on 8/30/17.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    //region Instance Variables

    private LayoutInflater mLayoutInflater;

    private List<String> mUrls;

    private ItemClickListener mClickListener;

    //endregion

    public ImageAdapter(Context ctxt, List<String> urls) {
        mLayoutInflater = LayoutInflater.from(ctxt);
        mUrls = urls;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        String url = mUrls.get(position);

        holder.image.setImageURI(url);

        holder.image.setOnClickListener(view -> {

            if (mClickListener != null) {
                mClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUrls.size();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image) SimpleDraweeView image;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

