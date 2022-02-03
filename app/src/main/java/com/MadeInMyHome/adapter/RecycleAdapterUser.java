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
import com.MadeInMyHome.activity.user.showProfileToUser.showProfileToUserActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class RecycleAdapterUser extends RecyclerView.Adapter<RecycleAdapterUser.viewitem> {


    ArrayList<User> items;
    Context context;

    public RecycleAdapterUser(Context c, ArrayList<User> item) {
        items = item;
        context = c;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        holder.name.setText(items.get(position).getF_name()+items.get(position).getL_name());
        holder.code.setText(items.get(position).getCode());
        holder.location.setText(items.get(position).getLocation());
        new GlideImage(context, constants.BASE_HOST+constants.IMAGE_USER  + items.get(position).getImage(), holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, showProfileToUserActivity.class);
                i.putExtra("id",  String.valueOf(items.get(holder.getAdapterPosition()).getId()));
                context.startActivity(i);


            }
        });


       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductByCategoryActivity.class);
                i.putExtra("category", name);
                i.putExtra("category_id", String.valueOf(items.get(holder.getAdapterPosition()).getId()));
                context.startActivity(i);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name, code,location;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            code = itemView.findViewById(R.id.code);
            location = itemView.findViewById(R.id.location);

        }
    }
}
