package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecycleAdapterOrder extends RecyclerView.Adapter<RecycleAdapterOrder.viewitem> {


    OrderViewModel orderViewModel;
    ArrayList<order> items;
    Context context;


    public RecycleAdapterOrder(Context c, ArrayList<order> item, OrderViewModel o) {

        items = item;
        context = c;
        orderViewModel = o;


    }

    //The View Item part responsible for connecting the row.xml with
    // each item in the RecyclerView
    //make declare and initalize
    class viewitem extends RecyclerView.ViewHolder {

        //Declare
        TextView name, counter;
        ImageButton add, delete, remove;


        //initialize
        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.mealname);
            counter = itemView.findViewById(R.id.counter);
            add = itemView.findViewById(R.id.add);
            delete = itemView.findViewById(R.id.delete);
            remove = itemView.findViewById(R.id.remove);
        }
    }


    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.showmealorder, parent, false);

        //the itemView now is the row
        //We will add 2 onClicks
        return new viewitem(itemView);
    }


    //to fill each item with data from the array depending on position
    @Override
    public void onBindViewHolder(final viewitem holder, final int position) {
        final String name = items.get(position).getMealname();
        final String[] counter = {items.get(position).getCounter()};
        holder.name.setText(name);
        holder.counter.setText(counter[0]);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(counter[0]) < 100) {
                    orderViewModel.updatecounter(context,
                            getSharedPreference(context, "email"),
                            name,
                            (Integer.parseInt(counter[0]) + 1) + "").observeForever(new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if (aBoolean) {
                                holder.counter.setText((Integer.parseInt(counter[0]) + 1) + "");
                                counter[0] = (Integer.parseInt(counter[0]) + 1) + "";
                            }
                        }
                    });
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(counter[0]) > 1) {
                    orderViewModel.updatecounter(context,
                            getSharedPreference(context, "email"),
                            name,
                            (Integer.parseInt(counter[0]) - 1) + "").observeForever(new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if (aBoolean) {
                                holder.counter.setText((Integer.parseInt(counter[0]) - 1) + "");
                                counter[0] = (Integer.parseInt(counter[0]) - 1) + "";
                            }
                        }
                    });
                }
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderViewModel.deleteorder(context,
                        getSharedPreference(context, "email"),
                        name).observeForever(new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if (aBoolean) {
                            items.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,items.size());
                        }
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    // private final View.OnClickListener mOnClickListener = new MyOnClickListener();


//    @Override
//    public void onClick(final View view) {
//        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//        String item = mList.get(itemPosition);
//        Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
//    }


}
