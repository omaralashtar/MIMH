package com.MadeInMyHome.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.MadeInMyHome.R;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;


public class ArrayAdapterCategory extends ArrayAdapter<Category> {

    ArrayList<Category> categories;
    Context context;

    public ArrayAdapterCategory(Context context,int res, ArrayList<Category> categories) {
        super(context, res, categories);
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ((TextView)super.getView(position, convertView, parent)).setText(categories.get(position).getName());
        return super.getView(position, convertView, parent);
    }

    public String getIdView(String item) {
        for (Category category : categories) {
            if (category.getName().equals(item))
                return category.getId();
        }
        return "0";
    }
}
