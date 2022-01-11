package com.MadeInMyHome.activity.ui.category_product;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterCategory;
import com.MadeInMyHome.databinding.FragmentCategoryProductHorizontalBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.utilities.EqualSpacingItemDecoration;
import com.MadeInMyHome.utilities.Utility;

import java.util.ArrayList;


public class category_product_horizontal extends Fragment {

    CategoryProductViewModel categoryProductsViewModel;

    private FragmentCategoryProductHorizontalBinding binding;
    RecycleAdapterCategory CategoryProductsRecycleAdapter;
    int next = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        categoryProductsViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        binding = FragmentCategoryProductHorizontalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        int spacing = 10;
        EqualSpacingItemDecoration equalSpacing = new EqualSpacingItemDecoration(spacing);
        binding.recycle.addItemDecoration(equalSpacing);




         binding.recycle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));





        setAdapter();


        return root;


    }

    public void setAdapter(){
        categoryProductsViewModel.showCategoryProduct(getActivity()).observe(getActivity(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                CategoryProductsRecycleAdapter = new RecycleAdapterCategory(getActivity(), categories,"product");
                binding.recycle.setAdapter(CategoryProductsRecycleAdapter);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}