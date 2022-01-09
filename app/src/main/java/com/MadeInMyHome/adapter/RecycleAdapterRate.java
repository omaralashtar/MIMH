package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Rate;

import java.util.ArrayList;


public class RecycleAdapterRate extends RecyclerView.Adapter<RecycleAdapterRate.viewitem> {


    //    OrderViewModel orderViewModel;
    ArrayList<Rate> items;
    Context context;

    public RecycleAdapterRate(Context c, ArrayList<Rate> item) {
        items = item;
        context = c;
//        orderViewModel = o;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_comments, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        holder.name.setText(items.get(position).getF_name()+items.get(position).getL_name());
        holder.comment.setText(items.get(position).getComment());
        holder.rate.setText(items.get(position).getRating()+"");
        holder.ratingBar.setRating(items.get(position).getRating());
        //holder.rate.setText(items.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView name, rate, comment;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.nameUser);
            rate = itemView.findViewById(R.id.txt_rate);
            comment = itemView.findViewById(R.id.commentName);
           // remove = itemView.findViewById(R.id.remove);
            ratingBar=itemView.findViewById(R.id.ratingBar);



            ratingBar.setRating(3);
        }
    }
}
