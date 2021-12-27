package com.MadeInMyHome.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.productsByCategory.ProductByCategoryActivity;
import com.MadeInMyHome.activity.show_product.ProductActivity;
import com.MadeInMyHome.activity.video.VideoActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class RecycleAdapterProduct extends RecyclerView.Adapter<RecycleAdapterProduct.viewitem> {


    //    OrderViewModel orderViewModel;
    ArrayList<Product> items;
    Context context;


    public RecycleAdapterProduct(Context c, ArrayList<Product> item/*, OrderViewModel */) {

        items = item;
        context = c;
        //orderViewModel = o;
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
        holder.price.setText(String.valueOf(items.get(position).getPrice()));
        holder.discount_date.setText(items.get(position).getDiscount_date());
        holder.category.setText(items.get(position).getCategory());

//        holder.discount.setText(items.get(position).getDiscount());
//        if(items.get(position).getImage().equals("images_product/defult_product.png"))
        new GlideImage(context, constants.BASE_HOST + constants.IMAGE_PRODUCT + items.get(position).getImage(), holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = items.get(holder.getAdapterPosition()).getId();
                Intent i = new Intent(context, ProductActivity.class);
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
        TextView name, price, discount, discount_date, category;
        ImageButton favorite;
        ImageView image;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);
            discount = itemView.findViewById(R.id.discount);
            discount_date = itemView.findViewById(R.id.discount_date);
            category = itemView.findViewById(R.id.category);
            //favorite = itemView.findViewById(R.id.favorite);
        }
    }
}
