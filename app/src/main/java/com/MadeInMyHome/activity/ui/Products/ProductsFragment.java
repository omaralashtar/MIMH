package com.MadeInMyHome.activity.ui.Products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentProductsBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class ProductsFragment extends Fragment {

    RecycleAdapterProduct recycleAdapterProduct;
    ProductsViewModel productsViewModel;
    private FragmentProductsBinding binding;

    int next=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        productsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.productsRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

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
        productsViewModel.getProducts(getActivity(),String.valueOf(next)).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                recycleAdapterProduct =new RecycleAdapterProduct(getActivity(),products);
                binding.productsRecycle.setAdapter(recycleAdapterProduct);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}