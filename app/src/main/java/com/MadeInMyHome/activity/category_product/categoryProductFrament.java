package com.MadeInMyHome.activity.category_product;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.MadeInMyHome.databinding.FramentProductCategoryBinding;
import com.MadeInMyHome.adapter.RecycleAdapterCategory;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

public class categoryProductFrament extends Fragment {
    CategoryProductViewModel categoryProductsViewModel;

    private FramentProductCategoryBinding binding;
    RecycleAdapterCategory CategoryProductsRecycleAdapter;
    int next = 0;

    public static categoryProductFrament newInstance() {
        return new categoryProductFrament();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        categoryProductsViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        binding = FramentProductCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.categoryProductRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        setAdapter();

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter();
            }
        });






        return root;


    }

    public void setAdapter(){
        categoryProductsViewModel.showCategoryProduct(getActivity()).observe(getActivity(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                CategoryProductsRecycleAdapter = new RecycleAdapterCategory(getActivity(), categories);
                binding.categoryProductRecycle.setAdapter(CategoryProductsRecycleAdapter);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}