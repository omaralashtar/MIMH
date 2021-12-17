package com.MadeInMyHome.activity.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.R;
import com.MadeInMyHome.ViewModel.CategoryProduct;
import com.MadeInMyHome.adapter.RecycleAdapterCategory;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.utilities.EqualSpacingItemDecoration;

import java.util.ArrayList;

public class CategoryProductFragment extends Fragment {

    RecyclerView recyclerView;
    RecycleAdapterCategory recycleAdapterCategory;
    CategoryProduct categoryProduct;

    public static CategoryProductFragment newInstance() {
        return new CategoryProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.categoryProductRecycle);

        int spacing = 10;
        EqualSpacingItemDecoration equalSpacing = new EqualSpacingItemDecoration(spacing);
        recyclerView.addItemDecoration(equalSpacing);

        //int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true));
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),mNoOfColumns, LinearLayoutManager.HORIZONTAL, true));

        categoryProduct = ViewModelProviders.of(this).get(CategoryProduct.class);
        categoryProduct.showCategoryProduct(getActivity()).observe(getActivity(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                recycleAdapterCategory = new RecycleAdapterCategory(getActivity(), categories);
                recyclerView.setAdapter(recycleAdapterCategory);
            }
        });

        //recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 0);

        return view;
    }
}