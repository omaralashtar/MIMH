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
import com.MadeInMyHome.activity.video.VideoActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class RecycleAdapterCourse extends RecyclerView.Adapter<RecycleAdapterCourse.viewitem> {


    ArrayList<Course> items;
    Context context;
    String course;
    Intent i;
    String id;
    GlideImage glideImage;

    public RecycleAdapterCourse(Context c, ArrayList<Course> item, String typeCourse) {
        items = item;
        context = c;
        course = typeCourse;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_course, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(viewitem holder, int position) {
        id = items.get(position).getId();
        holder.name.setText(items.get(position).getName());
        holder.presenter.setText(items.get(position).getPresenter());
        holder.category.setText(items.get(position).getCategory());


        new GlideImage(context, constants.BASE_HOST + items.get(position).getImage(), holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (course.equals("myCourse")) {
                    i = new Intent(context, VideoActivity.class);
                } else {
                    i = new Intent(context, ProductByCategoryActivity.class);
                }
                i.putExtra("id", id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name, category, presenter;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            presenter = itemView.findViewById(R.id.presenter);
            category = itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.image);
        }
    }
}
