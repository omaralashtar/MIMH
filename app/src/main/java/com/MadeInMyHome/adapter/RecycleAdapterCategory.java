package com.MadeInMyHome.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;


public class RecycleAdapterCategory extends RecyclerView.Adapter<RecycleAdapterCategory.viewitem> {


    ArrayList<Category> items;
    Context context;

    public RecycleAdapterCategory(Context c, ArrayList<Category> item) {
        items = item;
        context = c;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_category, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(viewitem holder, int position) {
        holder.name.setText(items.get(position).getName());

//      glideImage = new GlideImage(context, constants.BASE_HOST + items.get(position).getMealimage(), holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(context, MainActivity.class);
//                Category category = items.get(position);
//                i.putExtra("category", category.getName());
//                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name, category;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            category = itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.icon);
        }
    }

    // private final View.OnClickListener mOnClickListener = new MyOnClickListener();


//    @Override
//    public void onClick(final View view) {
//        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//        String item = mList.get(itemPosition);
//        Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
//    }


}
