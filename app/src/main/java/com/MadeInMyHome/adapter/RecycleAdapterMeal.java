package com.MadeInMyHome.adapter;


import static com.love.speed.fullproject.ui.utilities.constants.BASE_HOST;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecycleAdapterMeal extends RecyclerView.Adapter<RecycleAdapterMeal.viewitem> {



    ArrayList<Meal> items;
    Context context;
    GlideImage glideImage;

    public RecycleAdapterMeal(Context c, ArrayList<Meal> item)
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
        TextView name,price;
        ImageView image;


        //initialize
        public viewitem(View itemView) {
            super(itemView);
            name=  itemView.findViewById(R.id.mealname);
            price=  itemView.findViewById(R.id.counter);
            image=  itemView.findViewById(R.id.mealimage);
        }
    }



    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.showmeal, parent, false);

        //the itemView now is the row
        //We will add 2 onClicks
        return new viewitem(itemView);
    }


    //to fill each item with data from the array depending on position
    @Override
    public void onBindViewHolder(viewitem holder, final int position) {

        holder.name.setText(items.get(position).getMealName());
        holder.price.setText(items.get(position).getMealPrice()+" "+context.getResources().getString(R.string.jd));

        if (  !TextUtils.isEmpty(items.get(position).getMealimage())) {
            glideImage=new GlideImage(context,constants.BASE_HOST+items.get(position).getMealimage(),holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(context, DetailsActivity.class);
                Meal meal=items.get(position);
                i.putExtra("Meal",meal);
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
