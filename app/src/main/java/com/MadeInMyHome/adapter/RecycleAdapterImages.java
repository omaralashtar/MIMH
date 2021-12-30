package com.MadeInMyHome.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Video;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class RecycleAdapterImages extends RecyclerView.Adapter<RecycleAdapterImages.viewitem> {


    ArrayList<Bitmap> items;
    Context context;


    public RecycleAdapterImages(Context c, ArrayList<Bitmap> item) {
        items = item;
        context = c;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_image, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        holder.image.setImageBitmap(items.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
