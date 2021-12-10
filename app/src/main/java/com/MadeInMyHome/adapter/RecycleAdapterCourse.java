package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.Course;

import java.util.ArrayList;


public class RecycleAdapterCourse extends RecyclerView.Adapter<RecycleAdapterCourse.viewitem> {


    ArrayList<Course> items;
    Context context;
    GlideImage glideImage;

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_view, parent, false);

        return new viewitem(itemView);
    }

    public RecycleAdapterCourse(Context c, ArrayList<Course> item) {
        items = item;
        context = c;

    }

    @Override
    public void onBindViewHolder(viewitem holder,int position) {
//
//        holder.name.setText(items.get(position).getMealName());
//        holder.price.setText(items.get(position).getMealPrice() + " " + context.getResources().getString(R.string.jd));
//
//        if (!TextUtils.isEmpty(items.get(position).getMealimage())) {
//            glideImage = new GlideImage(context, constants.BASE_HOST + items.get(position).getMealimage(), holder.image);
//        }
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, DetailsActivity.class);
//                Meal meal = items.get(position);
//                i.putExtra("Meal", meal);
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name, presenter, category;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            presenter = itemView.findViewById(R.id.price);
            category = itemView.findViewById(R.id.discount);
            image = itemView.findViewById(R.id.image);
        }
    }

}
