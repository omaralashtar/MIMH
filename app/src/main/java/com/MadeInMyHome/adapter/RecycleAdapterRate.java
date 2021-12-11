package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Rate;

import java.util.ArrayList;


public class RecycleAdapterRate extends RecyclerView.Adapter<RecycleAdapterRate.viewitem> {


    //    OrderViewModel orderViewModel;
    ArrayList<Rate> items;
    Context context;

    public RecycleAdapterRate(Context c, ArrayList<Rate> item/*, OrderViewModel */) {
        items = item;
        context = c;
//        orderViewModel = o;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_category, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        //holder.name.setText(items.get(position).getName());
        holder.comment.setText(items.get(position).getComment());
        holder.rate.setText(items.get(position).getRating());
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {

        TextView name, rate, comment;
        ImageButton update, remove;


        public viewitem(View itemView) {
            super(itemView);
//            name = itemView.findViewById(R.id.name);
//            rate = itemView.findViewById(R.id.rate);
//            comment = itemView.findViewById(R.id.comment);
//            update = itemView.findViewById(R.id.update);
//            remove = itemView.findViewById(R.id.remove);
        }
    }
}
