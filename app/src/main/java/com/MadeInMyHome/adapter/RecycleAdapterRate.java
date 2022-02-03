package com.MadeInMyHome.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_product.ProductViewModel;
import com.MadeInMyHome.model.Rate;

import java.util.ArrayList;


public class RecycleAdapterRate extends RecyclerView.Adapter<RecycleAdapterRate.viewitem> {

    ProductViewModel productViewModel;
    ArrayList<Rate> items;
    String id_product;
    Context context;

    public RecycleAdapterRate(Context c, ArrayList<Rate> item, ProductViewModel viewModel, String Id_product) {
        productViewModel = viewModel;
        items = item;
        id_product = Id_product;
        context = c;
    }

    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_comments, parent, false);

        return new viewitem(itemView);
    }

    @Override
    public void onBindViewHolder(final viewitem holder, int position) {
        holder.name.setText(items.get(position).getF_name() + items.get(position).getL_name());
        holder.comment.setText(items.get(position).getComment());
        holder.ratingBar.setRating(items.get(position).getRating());
        if (productViewModel != null) {
            holder.deleteRate.setVisibility(View.VISIBLE);
            holder.deleteRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder a = new AlertDialog.Builder(context);
                    a.setNeutralButton(context.getResources().getString(R.string.dialog_cancel), null);
                    a.setCancelable(false);
                    a.setIcon(R.drawable.profile);
                    a.setNegativeButton(context.getResources().getString(R.string.dialog_No), null);
                    a.setPositiveButton(context.getResources().getString(R.string.dialog_yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            productViewModel.DeleteRate(context, items.get(holder.getBindingAdapterPosition()).getId_user(), id_product)
                                    .observeForever(new Observer<String>() {
                                        @Override
                                        public void onChanged(String s) {
                                            items.remove(0);
                                            notifyItemRemoved(0);
                                            notifyItemRangeRemoved(0, 1);
                                        }
                                    });
                        }
                    });
                    a.setTitle(R.string.dialog_Title);
                    a.setMessage(context.getResources().getString(R.string.dialog_msg));
                    a.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class viewitem extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView name, comment;
        ImageView image;
        ImageButton deleteRate;

        public viewitem(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.nameUser);
            comment = itemView.findViewById(R.id.commentName);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            deleteRate = itemView.findViewById(R.id.deleteRate);

        }
    }
}
