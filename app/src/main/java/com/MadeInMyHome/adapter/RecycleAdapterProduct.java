package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Product;

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

    class viewitem extends RecyclerView.ViewHolder {
        TextView name, price, discount, discount_date, category;
        ImageButton favorite,add, delete, remove;

        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            discount = itemView.findViewById(R.id.discount);
            discount_date = itemView.findViewById(R.id.discount_date);
            category = itemView.findViewById(R.id.category);
        }
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, final int position) {
//        final String name = items.get(position).getMealname();
//        final String[] counter = {items.get(position).getCounter()};
//        holder.name.setText(name);
//        holder.counter.setText(counter[0]);
//        holder.add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Integer.parseInt(counter[0]) < 100) {
//                    orderViewModel.updatecounter(context,
//                            getSharedPreference(context, "email"),
//                            name,
//                            (Integer.parseInt(counter[0]) + 1) + "").observeForever(new Observer<Boolean>() {
//                        @Override
//                        public void onChanged(Boolean aBoolean) {
//                            if (aBoolean) {
//                                holder.counter.setText((Integer.parseInt(counter[0]) + 1) + "");
//                                counter[0] = (Integer.parseInt(counter[0]) + 1) + "";
//                            }
//                        }
//                    });
//                }
//            }
//        });
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Integer.parseInt(counter[0]) > 1) {
//                    orderViewModel.updatecounter(context,
//                            getSharedPreference(context, "email"),
//                            name,
//                            (Integer.parseInt(counter[0]) - 1) + "").observeForever(new Observer<Boolean>() {
//                        @Override
//                        public void onChanged(Boolean aBoolean) {
//                            if (aBoolean) {
//                                holder.counter.setText((Integer.parseInt(counter[0]) - 1) + "");
//                                counter[0] = (Integer.parseInt(counter[0]) - 1) + "";
//                            }
//                        }
//                    });
//                }
//            }
//        });
//        holder.remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                orderViewModel.deleteorder(context,
//                        getSharedPreference(context, "email"),
//                        name).observeForever(new Observer<Boolean>() {
//                    @Override
//                    public void onChanged(Boolean aBoolean) {
//                        if (aBoolean) {
//                            items.remove(position);
//                            notifyItemRemoved(position);
//                            notifyItemRangeChanged(position,items.size());
//                        }
//                    }
//                });
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
