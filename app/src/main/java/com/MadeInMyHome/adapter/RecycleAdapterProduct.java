package com.MadeInMyHome.adapter;

import static android.view.View.GONE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.add_update_product.AddUpdateProductActivity;
import com.MadeInMyHome.activity.show_product.ProductActivity;
import com.MadeInMyHome.activity.user.showProfileToUser.showProfileToUserActivity;
import com.MadeInMyHome.activity.user.userProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RecycleAdapterProduct extends RecyclerView.Adapter<RecycleAdapterProduct.viewitem> {

    ArrayList<Product> items;
    ShowUserProfileViewModel showUserProfileViewModel;
    Context context;
    String product;

    public RecycleAdapterProduct(Context c, ArrayList<Product> item, String name, ShowUserProfileViewModel s) {
        items = item;
        showUserProfileViewModel =s;
        context = c;
        product = name;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_product, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.price.setText(String.valueOf(items.get(position).getPrice()) + "jd");
        holder.date.setText(items.get(position).getProduct_date());
        if(!product.equals("product")) {
            if (items.get(position).getDeleted_at() != null) {
                if (items.get(position).getDeleted_at().equals("0")) {
                    holder.imageStatus.setImageResource(android.R.drawable.presence_online);
                } else {
                    holder.imageStatus.setImageResource(android.R.drawable.ic_delete);
                }
            } else {
                holder.imageStatus.setImageResource(R.drawable.ic_baseline_sync_24);
            }
        }else{
            holder.imageStatus.setVisibility(GONE);
        }
        try {
            if (items.get(position).getDiscount_date() != null) {
                if (!items.get(position).getDiscount_date().equals("")) {
                    if (new SimpleDateFormat("yyyy-MM-dd")
                            .parse(items.get(position).getDiscount_date()).after(new Date())) {
                        holder.discount.setText(items.get(position).getDiscount());
                    }else{
                        holder.discount.setVisibility(GONE);
                    }
                }
            } else {
                holder.discount.setVisibility(GONE);

            }
        } catch (ParseException e) {
            holder.discount.setVisibility(GONE);
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }

        showUserProfileViewModel.getUserProfile(context,items.get(position).getId_user())
                .observeForever(new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        holder.nameUser.setText(user.getF_name()+""+ user.getL_name());
                        holder.nameUser.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            Intent i=new Intent(context, showProfileToUserActivity.class);
                            i.putExtra("id", items.get(holder.getBindingAdapterPosition()).getId_user());
                            context.startActivity(i);
                            }
                        });
                    }
                });

        new GlideImage(context, constants.BASE_HOST + constants.IMAGE_PRODUCT + items.get(position).getImage(), holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = items.get(holder.getAdapterPosition()).getId();
                Intent i;
                if (product.equals("product")) {
                    i = new Intent(context, ProductActivity.class);
                }

                else {
                    i = new Intent(context, AddUpdateProductActivity.class);
                    i.putExtra("name", "update");
                }
                i.putExtra("id_product", id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        TextView name,nameUser, price, discount, date;
        ImageView image,imageStatus;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nameUser = itemView.findViewById(R.id.nameUser);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            imageStatus = itemView.findViewById(R.id.imageStatus);
            discount = itemView.findViewById(R.id.discount);
            date = itemView.findViewById(R.id.create_date);
        }
    }
}
