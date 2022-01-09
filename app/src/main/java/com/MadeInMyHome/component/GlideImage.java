package com.MadeInMyHome.component;

import android.content.Context;
import android.widget.ImageView;

import com.MadeInMyHome.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


public class GlideImage {
    public GlideImage(Context context , String urlimage, ImageView image){
        Glide.with(context).load(urlimage)
                .apply(new RequestOptions()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .placeholder(R.drawable.defult_product))
                .into(image);
    }
}
