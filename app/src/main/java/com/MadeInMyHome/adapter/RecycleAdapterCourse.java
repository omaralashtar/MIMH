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
import com.MadeInMyHome.databinding.ViewCourseBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class RecycleAdapterCourse extends RecyclerView.Adapter<RecycleAdapterCourse.viewitem> {


    ArrayList<Course> items;
    Context context;
    GlideImage glideImage;

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_course, parent, false);

        return new viewitem(itemView);
    }

    public RecycleAdapterCourse(Context c, ArrayList<Course> item) {
        items = item;
        context = c;

    }

    @Override
    public void onBindViewHolder(viewitem holder, int position) {

        holder.name.setText(items.get(position).getName());
        holder.presenter.setText(items.get(position).getPresenter());
        holder.category.setText(items.get(position).getCategory());


        new GlideImage(context, constants.BASE_HOST + items.get(position).getImage(), holder.image);

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
        TextView name, category, presenter;
        ImageView image;
        ViewCourseBinding binding;

        public viewitem(View itemView) {
            super(itemView);
            name = binding.name;
            presenter = binding.presenter;
            category = binding.category;
            image = binding.image;
        }
    }
}
