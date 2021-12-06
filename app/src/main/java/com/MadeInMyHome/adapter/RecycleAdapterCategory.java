package com.MadeInMyHome.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecycleAdapterCategory extends    RecyclerView.Adapter<RecycleAdapterCategory.viewitem> {



    ArrayList<Category> items;
    Context context;

    public RecycleAdapterCategory(Context c, ArrayList<Category> item)
    {
        items=item;
        context=c;

    }

    //The View Item part responsible for connecting the row.xml with
    // each item in the RecyclerView
    //make declare and initalize
    class  viewitem extends RecyclerView.ViewHolder
    {

        //Declare
        TextView name;


        //initialize
        public viewitem(View itemView) {
            super(itemView);
            name=  itemView.findViewById(R.id.mealname);
            }
    }



    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.showcategory, parent, false);

        //the itemView now is the row
        //We will add 2 onClicks
        return new viewitem(itemView);
    }


    //to fill each item with data from the array depending on position
    @Override
    public void onBindViewHolder(viewitem holder, final int position) {
        holder.name.setText(items.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(context, MainActivity.class);
                Category category=items.get(position);
                i.putExtra("category",category.getName());
                context.startActivity(i);
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
