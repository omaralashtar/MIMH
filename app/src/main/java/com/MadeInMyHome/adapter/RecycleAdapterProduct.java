package com.MadeInMyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.add_update_product.AddUpdateProductActivity;
import com.MadeInMyHome.activity.show_product.ProductActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.utilities.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RecycleAdapterProduct extends RecyclerView.Adapter<RecycleAdapterProduct.viewitem> {

    ArrayList<Product> items;
    Context context;
    String product;

    public RecycleAdapterProduct(Context c, ArrayList<Product> item, String name) {
        items = item;
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
        try {
            if (items.get(position).getDiscount_date() != null) {
                if (!items.get(position).getDiscount_date().equals("")) {
                    if (new SimpleDateFormat("yyyy-MM-dd")
                            .parse(items.get(position).getDiscount_date()).after(new Date())) {
                        holder.discount.setText(items.get(position).getDiscount());
                    }else{
                        holder.discount.setVisibility(View.GONE);
                    }
                }
            } else {
                holder.discount.setVisibility(View.GONE);

            }
        } catch (ParseException e) {
            holder.discount.setVisibility(View.GONE);
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
        }

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
        TextView name, price, discount, date;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            discount = itemView.findViewById(R.id.discount);
            date = itemView.findViewById(R.id.create_date);
        }
    }
}
