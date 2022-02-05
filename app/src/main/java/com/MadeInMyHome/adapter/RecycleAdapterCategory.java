package com.MadeInMyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.productsByCategory.ProductByCategoryActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class RecycleAdapterCategory extends RecyclerView.Adapter<RecycleAdapterCategory.viewitem> {


    ArrayList<Category> items;
    Context context;
    String name;

    public RecycleAdapterCategory(Context c, ArrayList<Category> item,String n) {
        items = item;
        context = c;
        name=n;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_category, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(viewitem holder,  int position) {
        holder.name.setText(items.get(position).getName());

        new GlideImage(context, constants.BASE_HOST +constants.IMAGE_CATEGORY+ items.get(position).getImage(), holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductByCategoryActivity.class);
                i.putExtra("category", name);
                i.putExtra("category_id", String.valueOf(items.get(holder.getAdapterPosition()).getId()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
        }
    }
}
